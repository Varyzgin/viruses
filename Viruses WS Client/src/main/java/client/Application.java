package client;

import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import java.net.MalformedURLException;

public class Application extends javafx.application.Application {
    private final int scale = 400;
    @Override
    public void start(Stage stage) throws MalformedURLException {
        stage.setTitle("Viruses War!");

        StackPane root = new StackPane();
        Scene scene = new Scene(root, scale, scale);

        Controller controller = new Controller(root, scale);

        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}