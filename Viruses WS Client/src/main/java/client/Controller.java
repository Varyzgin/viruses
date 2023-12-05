package client;

import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import webservice.*;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class Controller {
    int port;
    String url;
    ClientAtServer service;
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

        TextField portChose = new TextField("8080");
        portChose.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.ENTER) {
                port = Integer.parseInt(portChose.getText());
                url = String.format("http://localhost:%d/ClientAtServer?wsdl", port);
                // удалим поле
                if (event.getSource() instanceof TextField) {
                    root.getChildren().remove(portChose);
                }
                // создадим слои с сетками
                gridCanvas = new Canvas(scale, scale);
                drawGrid();
                movesHistoryCanvas = new Canvas(scale, scale);
                availableMovesCanvas = new Canvas(scale, scale);

                root.setOnMouseClicked(this::handleMouseClick); // Обработка кликов мышкой по полю
                root.getChildren().addAll(gridCanvas, movesHistoryCanvas, availableMovesCanvas);

                // запускаем общение с сервером
                try {
                    service = new ClientAtServerService(new URL(url)).getClientAtServerPort();
                } catch (MalformedURLException e) {
                    throw new RuntimeException(e);
                }
                service.connect(); // удаленный вызов connect
                serverThread();
            }

        });
        root.getChildren().add(portChose);
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
            Cell cell = new Cell();
            cell.setRow(row);
            cell.setColumn(column);
            service.movesSender(cell);
        }
    }

    private final Timer timer = new Timer();

    public void serverThread() {
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                serverTask();
            }
        }, 0, 500); // Запуск каждые 500 миллисекунд
    }

    private void serverTask() {
        List<BooleanArray> tmp = service.availableMovesDownloader();
        boolean changes = false;
        for (int i = 0; i < gridSize; i++)
            for (int j = 0; j < gridSize; j++)
                if (availableMovesTable[i][j] != tmp.get(i).getItem().get(j)) {
                    changes = true;
                    break;
                }
        if (changes) {
            List<CellTypeArray> moves = service.movesHistoryDownloader();
            for (int k = 0; k < gridSize; k++)
                for (int l = 0; l < gridSize; l++) {
                    movesHistoryTable[k][l] = moves.get(k).getItem().get(l);
                    availableMovesTable[k][l] = tmp.get(k).getItem().get(l);
                }

            deleteAvailableMovesCanvas(); // Удаляем верхний слой
            deleteMovesHistoryCanvas();
            drawMovesHistoryCanvas();
            drawAvailableMovesCanvas();
        }
    }
}
