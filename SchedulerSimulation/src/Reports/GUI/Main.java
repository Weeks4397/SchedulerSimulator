package Reports.GUI;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws IOException {

        Parent root = FXMLLoader.load(getClass().getResource("Views/title_view.fxml"));
        primaryStage.setTitle("Scheduler Simulator");
        primaryStage.setScene(new Scene(root, 600, 400));
        primaryStage.show();
    }
}
