package pdf_reader;/**
 * Created by quinn on 7/12/17.
 */

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.stage.Stage;

public class Control_Banner extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {

    }

    @FXML
    protected void handleDropdownAction(ActionEvent event) {
        System.exit(0);
    }

    @FXML
    public void initialize(){}
}
