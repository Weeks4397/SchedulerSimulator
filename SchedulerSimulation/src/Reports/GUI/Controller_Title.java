/**
 * This is the controller class for the homepage of the GUI
 * It handles all listeners and homepage functionality
 */
package Reports.GUI;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.awt.*;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.concurrent.atomic.AtomicReference;


public class Controller_Title {


    /**
     * The pane that the title_view's components are anchored to
     */
    @FXML
    Pane pane;

    /**
     * Button that transitions you to the next scene
     */
    @FXML
    Button next_scene_button;

    /**
     * The Image view where the SHU logo goes
     */
    @FXML
    ImageView SHU_Logo;


    /**
     * Initializes all necessary listeners
     */
    @FXML
    private void initialize() {
        easterEgg();
    }


    /**
     * Sends the program to the next scene and deleats the first scene
     *
     * @throws IOException
     */
    @FXML
    public void next_scene() throws IOException {

        Stage stage = (Stage) pane.getScene().getWindow();

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("View_Scheduler.fxml"));
        Parent parent = loader.load();
        Scene settingsScene = new Scene(parent);
        //settingsScene.getStylesheets().add("Custom.css");
        Stage window = new Stage();
        Image img = new Image("Reports/GUI/Images/SHUTopLogo.png");
        window.getIcons().add(img);
        window.setTitle("Scheduler Simulator");
        window.setScene(settingsScene);
        window.setResizable(false);
        window.show();
        stage.close();

        // Close this window because we no longer need it
        // Stage stage = (Stage) currentWindow;
        // stage.close();
    }

    /**
     * Redirects the user to our github.
     *
     * @throws URISyntaxException If the URI cannot be created
     * @throws IOException        If the page is unreachable
     */
    @FXML
    public void link_pressed() throws URISyntaxException, IOException {
        Desktop.getDesktop().browse(new URI("https://github.com/Weeks4397/SchudulerSimulater"));
    }

    /**
     * SHHHHHHH this is a easter egg ;)
     */
    private void easterEgg() {
        AtomicReference<Boolean> backgroundChange = new AtomicReference<>(false);
        Image img = new Image("Reports/GUI/Images/TheCow.jpg");
        SHU_Logo.setOnMouseClicked((MouseEvent event) -> {
            if (event.getClickCount() % 5 == 0) {
                if (!backgroundChange.get()) {
                    BackgroundImage backgroundImage = new BackgroundImage(img, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, new BackgroundSize(BackgroundSize.AUTO, BackgroundSize.AUTO, false, false, true, true));
                    pane.setBackground(new Background(backgroundImage));
                    backgroundChange.set(true);
                } else {
                    pane.setBackground(new Background(new BackgroundFill(Color.WHITE, CornerRadii.EMPTY, Insets.EMPTY)));
                    backgroundChange.set(false);
                }
            }
        });
    }
}