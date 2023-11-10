package server;

import general.CellType;

import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MainServer {
     int port = 3125;
     InetAddress ip = null;
     ExecutorService service = Executors.newCachedThreadPool();
     ArrayList<ClientAtServer> allClients = new ArrayList<>();
    int numOfClients = 0;
    private final int gridSize = 10; // Размер поля 10x10
    private CellType[][] movesHistoryTable = new CellType[gridSize][gridSize];
    public CellType[][] getMovesHistoryTable(){
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

        ServerSocket ss;
        try {
            ip = InetAddress.getLocalHost();
            ss = new ServerSocket(port, 0, ip);
            System.out.println("Server started");

            while (numOfClients < 2) {
                Socket cs;
                cs = ss.accept();

                ClientAtServer client = new ClientAtServer(cs, this);
                allClients.add(client);

                // начислим очков первому игроку
                if(numOfClients == 0)
                    client.movesCount = 3;
                numOfClients++;

                System.out.println("Connected client " + numOfClients);
                service.submit(client);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) {
        new MainServer().SeverStart();
    }
}
