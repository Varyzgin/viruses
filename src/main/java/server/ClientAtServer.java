package server;

import general.Cell;
import general.CellType;
import general.Action;
import general.Message;
import com.google.gson.Gson;

import java.io.*;
import java.net.Socket;
import java.util.HashSet;
import java.util.Set;

public class ClientAtServer implements Runnable {
    private boolean firstStart = true;
    public int id;
    CellType base;
    CellType constant;
    CellType opponent;
    CellType op_const;
    public int movesCount;
    private Socket cs;
    MainServer ms;
    private InputStream is;
    private OutputStream os;
    private DataInputStream dis;
    private DataOutputStream dos;
    private Gson gson = new Gson();
    private final int gridSize = 10; // Размер поля 10x10
    boolean[][] availableMovesTable = new boolean[gridSize][gridSize];
    private CellType[][] movesHistoryTable = new CellType[gridSize][gridSize];

    void fromConstRecursive(int row, int column, Set<Cell> visitedCells) {
        for (int i = row - 1; i <= row + 1; i++)
            for (int j = column - 1; j <= column + 1; j++)
                try {
                    // просто красим всех вокруг, раз вызвалась эта рекурсивная функция
                    availableMovesTable[i][j] = true;
                    // если в радиусе оказался еще один constant - вызываем рекурсию от него
                    Cell cell = new Cell(i, j);
                    if (movesHistoryTable[i][j] == constant && !visitedCells.contains(cell) ) {
                        visitedCells.add(cell);
                        fromConstRecursive(i, j, visitedCells);
                    }
                } catch (Exception ignored) {
                }
    }

    private void calculate() {
        // сначала добавляем возможность хода в места без учета препятствий
        for (int i = 0; i < gridSize; i++)
            for (int j = 0; j < gridSize; j++)
                if (movesHistoryTable[i][j] == base) {
                    // если наш захват - помечаем территорию 3 на 3 как возможную для хода
                    for (int k = i - 1; k <= i + 1; k++)
                        for (int l = j - 1; l <= j + 1; l++)
                            try {
                                availableMovesTable[k][l] = true;
                            } catch (Exception ignored) {
                            }
                }
        // добавляем возможность хода от завоеванных территорий, к которым есть подключение
        Set<Cell> visitedCells = new HashSet<>();
        for (int i = 0; i < gridSize; i++)
            for (int j = 0; j < gridSize; j++)
                if (movesHistoryTable[i][j] == constant && availableMovesTable[i][j]) {
                    fromConstRecursive(i, j, visitedCells);
                }
        visitedCells.clear();
        // потом убираем возможность хода где уже ходили или где есть стены противника
        for (int i = 0; i < gridSize; i++)
            for (int j = 0; j < gridSize; j++)
                if (movesHistoryTable[i][j] == base || movesHistoryTable[i][j] == constant ||
                        movesHistoryTable[i][j] == op_const) availableMovesTable[i][j] = false;
    }

    // тут клиент только подключается
    public ClientAtServer(Socket cs, MainServer ms) {
        this.cs = cs;
        this.ms = ms;
        this.id = ms.numOfClients;
        if (id == 0) {
            base = CellType.X;
            constant = CellType.X_CONST;
            opponent = CellType.O;
            op_const = CellType.O_CONST;
        } else if (id == 1) {
            base = CellType.O;
            constant = CellType.O_CONST;
            opponent = CellType.X;
            op_const = CellType.X_CONST;
        }

        try {
            os = cs.getOutputStream();
            dos = new DataOutputStream(os);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void push(CellType[][] movesHistoryTable) {
        this.movesHistoryTable = movesHistoryTable;
        // Тут у нас если сервер начислил ходы - то вычисляем availableMovesTable, если нет -
        // обнуляем availableMovesTable, т.е. клиент не может ходить
        if (movesCount > 0) {
            if (firstStart && id == 1) availableMovesTable[9][9] = true;
            else calculate();
        } else availableMovesTable = new boolean[gridSize][gridSize];

        Message msg = new Message(Action.FROM_SERVER, movesHistoryTable, availableMovesTable);
        send(msg);
    }

    public void send(Message msg) {
        try {
            String s_msg = gson.toJson(msg);
            dos.writeUTF(s_msg);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    // подключение клиента
    @Override
    public void run() {
        try {
            is = cs.getInputStream();
            dis = new DataInputStream(is);
            System.out.println("Client thread started");

            // прием сообщений от клиента
            while (true) {
                String str = dis.readUTF();
                Message msg = gson.fromJson(str, Message.class);
                // тут содержатся варианты интерпретаций сообщений
                if (msg.action == Action.CONNECT) {
                    movesHistoryTable = ms.getMovesHistoryTable();

                    if (movesCount > 0 && id == 0) availableMovesTable[0][0] = true;
                    else availableMovesTable = new boolean[gridSize][gridSize];

                    send(new Message(Action.FROM_SERVER, movesHistoryTable, availableMovesTable));
                } else if (msg.action == Action.MOVE) {
                    if (movesCount > 0) {
                        firstStart = false;
                        movesCount--;
                        if (movesHistoryTable[msg.cell.row][msg.cell.column] != opponent)
                            movesHistoryTable[msg.cell.row][msg.cell.column] = base;
                        else movesHistoryTable[msg.cell.row][msg.cell.column] = constant;
                        // если ходов не осталось после захода в этот if, сообщаем серверу измененное поле,
                        // иначе используем оставшиеся ходы и меняем лишь availableMovesTable
                        if (movesCount > 0) {
                            calculate();
                            send(new Message(Action.FROM_SERVER, movesHistoryTable, availableMovesTable));
                        } else {
                            ms.broadCast(movesHistoryTable, this);
                        }
                    }
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
