package server;

import general.CellType;

import java.util.ArrayList;
import java.util.Arrays;

public class MainServer {
    ArrayList<ClientAtServer> allClients = new ArrayList<>();
    int numOfClients = 0;
    private final int gridSize = 10; // Размер поля 10x10
    private CellType[][] movesHistoryTable = new CellType[gridSize][gridSize];

    public CellType[][] getMovesHistoryTable() {
        return movesHistoryTable;
    }

    private void initMovesHistoryTable() {
        for (int i = 0; i < gridSize; i++)
            Arrays.fill(movesHistoryTable[i], CellType.NONE);
    }

    void broadCast(CellType[][] movesHistoryTable, ClientAtServer client) {
        // начисляем очки игрокам по кругу, если id равен кол-ву - круг с начала
        if (client.id == numOfClients - 1) allClients.get(0).movesCount = 3;
        else allClients.get(client.id + 1).movesCount = 3;

        // обновляем историю
        this.movesHistoryTable = movesHistoryTable;

        // рассылаем историю
        for (ClientAtServer allClients : allClients) {
            allClients.push(movesHistoryTable);
        }
    }

    private void SeverStart() {
        initMovesHistoryTable();
        allClients.add(new ClientAtServer(this, 8080, 0));
        // начислим очков первому игроку
        if (numOfClients == 0)
            allClients.get(0).movesCount = 3;
        numOfClients++;

        allClients.add(new ClientAtServer(this, 8081, 1));
        numOfClients++;
    }

    public static void main(String[] args) {
        new MainServer().SeverStart();
    }
}