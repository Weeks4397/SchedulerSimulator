package Reports.GUI;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.awt.*;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

public class title_Controller {

    @FXML
    public void initialize() {
        //TODO ON CREATE
    }

    public void next_scene() throws IOException {

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("schedguler_view.fxml"));
        Parent parent = loader.load();
        Scene settingsScene = new Scene(parent);
        //settingsScene.getStylesheets().add("Custom.css");
        Stage window = new Stage();
        window.setScene(settingsScene);
        window.show();
    }

    public void link_pressed() throws URISyntaxException, IOException {
        Desktop.getDesktop().browse(new URI("https://github.com/Weeks4397/SchudulerSimulater"));
    }

}