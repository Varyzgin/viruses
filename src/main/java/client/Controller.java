package client;

import com.google.gson.Gson;
import general.Action;
import general.Cell;
import general.CellType;
import general.Message;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;

import java.io.*;
import java.net.InetAddress;
import java.net.Socket;

public class Controller {
    private Thread connectThread;
    private final int port = 3125;
    private InetAddress ip = null;
    private Socket ClientSocket;
    private InputStream is;
    private OutputStream os;
    private DataInputStream dis;
    private DataOutputStream dos;
    Gson gson = new Gson();
    private Canvas gridCanvas;
    private Canvas movesHistoryCanvas;
    private Canvas availableMovesCanvas;
    private GraphicsContext gc;
    private final int gridSize = 10; // Размер поля 10x10
    private final double cellSize;
    CellType[][] movesHistoryTable = new CellType[gridSize][gridSize];
    boolean[][] availableMovesTable = new boolean[gridSize][gridSize];

    public Controller(StackPane root, int scale) {
        cellSize = (double) scale / gridSize;

        Button connectButton = new Button("Connect");
        connectButton.setOnAction(event -> {
            // удалим кнопку
            if (event.getSource() instanceof Button clickedButton) {
                root.getChildren().remove(clickedButton);
            }
            // создадим слои с сетками
            gridCanvas = new Canvas(scale, scale);
            drawGrid();
            movesHistoryCanvas = new Canvas(scale, scale);
            availableMovesCanvas = new Canvas(scale, scale);

            root.setOnMouseClicked(this::handleMouseClick); // Обработка кликов мышкой по полю
            root.getChildren().addAll(gridCanvas, movesHistoryCanvas, availableMovesCanvas);

            // запускаем общение с сервером
            serverThread();
            send(new Message(Action.CONNECT));
        });
        root.getChildren().add(connectButton);
    }

    private void drawGrid() {
        gc = gridCanvas.getGraphicsContext2D();
        gc.setStroke(Color.GRAY);
        gc.setLineWidth(1);
        // Рисуем сетку с клетками
        for (int i = 0; i < gridSize; i++) {
            for (int j = 0; j < gridSize; j++) {
                double x = j * cellSize;
                double y = i * cellSize;
                gc.strokeRect(x, y, cellSize, cellSize); // Рисуем клетку
            }
        }
    }

    private void deleteMovesHistoryCanvas() {
        gc = movesHistoryCanvas.getGraphicsContext2D();
        gc.clearRect(0, 0,
                movesHistoryCanvas.getWidth(), movesHistoryCanvas.getHeight());
    }

    private void drawMovesHistoryCanvas() {
        gc = movesHistoryCanvas.getGraphicsContext2D();
        for (int i = 0; i < gridSize; i++) {
            for (int j = 0; j < gridSize; j++) {
                if (movesHistoryTable[i][j] != CellType.NONE) {
                    double x = j * cellSize;
                    double y = i * cellSize;

                    if (movesHistoryTable[i][j] == CellType.X) gc.setFill(Color.PINK);
                    else if (movesHistoryTable[i][j] == CellType.X_CONST) gc.setFill(Color.RED);
                    else if (movesHistoryTable[i][j] == CellType.O) gc.setFill(Color.LIGHTBLUE);
                    else if (movesHistoryTable[i][j] == CellType.O_CONST) gc.setFill(Color.BLUE);

                    gc.fillRect(x, y, cellSize, cellSize); // Рисуем внутренность

                    gc.setStroke(Color.GRAY);
                    gc.setLineWidth(1);
                    gc.strokeRect(x, y, cellSize, cellSize); // Рисуем обводку
                }
            }
        }
    }

    private void deleteAvailableMovesCanvas() {
        gc = availableMovesCanvas.getGraphicsContext2D();
        gc.clearRect(0, 0,
                availableMovesCanvas.getWidth(), availableMovesCanvas.getHeight());
    }

    private void drawAvailableMovesCanvas() {
        gc = availableMovesCanvas.getGraphicsContext2D();
        gc.clearRect(0, 0,
                availableMovesCanvas.getWidth(), availableMovesCanvas.getHeight());
        for (int i = 0; i < gridSize; i++) {
            for (int j = 0; j < gridSize; j++) {
                String text = "";
                if (availableMovesTable[i][j]) text = "+";
                double textX = j * cellSize + cellSize / 2 - 5;
                double textY = i * cellSize + cellSize / 2 + 5;

                gc.fillText(text, textX, textY);
            }
        }
    }

    public void handleMouseClick(MouseEvent event) {
        double mouseX = event.getX();
        double mouseY = event.getY();

        int row = (int) (mouseY / cellSize);
        int column = (int) (mouseX / cellSize);

        if (availableMovesTable[row][column]) {
            availableMovesTable[row][column] = false;
            send(new Message(Action.MOVE, new Cell(row, column)));
        }
    }

    // отправка сообщения на сервер
    private void send(Message msg) {
        try {
            System.out.println(msg);
            String s_msg = gson.toJson(msg);
            dos.writeUTF(s_msg);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void serverThread() {
        try {
            ip = InetAddress.getLocalHost();
            ClientSocket = new Socket(ip, port);
            System.out.println("connected");
            os = ClientSocket.getOutputStream();
            dos = new DataOutputStream(os);

            // старт потока, отвечающего за общение с сервером
            connectThread = new Thread(() -> {
                try {
                    is = ClientSocket.getInputStream();
                    dis = new DataInputStream(is);

                    // прием сообщений от сервера
                    while (true) {
                        String str = dis.readUTF();
                        Message msg = gson.fromJson(str, Message.class);

                        movesHistoryTable = msg.movesHistoryTable;
                        availableMovesTable = msg.availableMovesTable;
                        deleteAvailableMovesCanvas(); // Удаляем верхний слой
                        deleteMovesHistoryCanvas();
                        drawMovesHistoryCanvas();
                        drawAvailableMovesCanvas();

                    }
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            });
            connectThread.start();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
