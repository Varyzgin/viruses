module viruses {
    requires javafx.controls;
    requires javafx.fxml;
    requires com.google.gson;


    opens client to javafx.fxml;
    exports client;
    exports general;
    opens general to javafx.fxml;
}