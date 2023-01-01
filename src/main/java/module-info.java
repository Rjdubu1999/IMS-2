module localhost.c482 {
    requires javafx.controls;
    requires javafx.fxml;


    opens localhost.c482 to javafx.fxml;
    exports localhost.c482;
    exports controller;
    opens controller to javafx.fxml;
}