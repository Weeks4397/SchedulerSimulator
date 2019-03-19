package Reports.GUI;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.stage.Stage;

import java.io.IOException;

public class Driver extends Application {

    /**
     * This is called to start the start (onStart in Android) function
     * @param args array of strings
     */
    public static void main(String[] args) {
        launch(args);
    }

    /**
     * Starts the initializes the scene and stage for the GUI
     * @param primaryStage
     * @throws IOException
     */
    @Override
    public void start(Stage primaryStage) throws IOException {

        Parent root = FXMLLoader.load(getClass().getResource("View_Title.fxml"));
        Image img = new Image("Reports/GUI/Images/SHUTopLogo.png");
        primaryStage.getIcons().add(img);
        primaryStage.setTitle("Scheduler Simulator");
        primaryStage.setScene(new Scene(root, 600, 400));
        primaryStage.setResizable(false);
        primaryStage.show();
    }
}