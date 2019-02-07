/**
 * This is the controller class for the homepage of the GUI
 * It handles all listeners and homepage functionality
 */
package Reports.GUI;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import javafx.stage.Window;

import java.awt.*;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

public class title_controller {

    /**
     * The window that the title_view.fxml is
     */
   // @FXML
   // private Window currentWindow;

    /**
     * Button that transitions you to the next scene
     */
    @FXML
    Button next_scene_button;

    /**
     * Same as onCreate (in Android). THis is called when the window is made
     * currently all it does is get the current window and set it to currentWindow
     */
    @FXML
    public void initialize() {
        //currentWindow = next_scene_button.getScene().getWindow();
    }

    /**
     * Sends the program to the next scene and deleats the first scene
     * @throws IOException
     */
    @FXML
    public void next_scene() throws IOException {

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("scheduler_view.fxml"));
        Parent parent = loader.load();
        Scene settingsScene = new Scene(parent);
        //settingsScene.getStylesheets().add("Custom.css");
        Stage window = new Stage();
        window.setScene(settingsScene);
        window.show();

        // Close this window because we no longer need it
       // Stage stage = (Stage) currentWindow;
       // stage.close();
    }

    /**
     * Redirects the user to our github.
     * @throws URISyntaxException If the URI cannot be created
     * @throws IOException If the page is unreachable
     */
    @FXML
    public void link_pressed() throws URISyntaxException, IOException {
        Desktop.getDesktop().browse(new URI("https://github.com/Weeks4397/SchudulerSimulater"));
    }

}