package localhost.c482;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {
    public TableView MainPartsTable;
    public TableColumn MainPartsIDColumn;
    public TableColumn MainPartsNameColumn;
    public TableColumn MainPartsInventoryColumn;
    public TableColumn MainPartsPriceColumn;
    public TableColumn MainProductIDColumn;
    public TableColumn MainProductNameColumn;
    public TableColumn MainProductInventoryColumn;
    public TableColumn MainProductPriceColumn;
    

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("Main.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1800, 800);
        stage.setTitle("Ryan Wilkinson C482 Performance Assessment");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }


}