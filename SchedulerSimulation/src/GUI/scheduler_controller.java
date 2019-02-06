package GUI;

import Generators.WorksetGenerator;
import Reports.WorksetReport;
import Schedulers.Scheduler;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;
import java.util.List;

public class scheduler_controller {

    private static WorksetGenerator wsg = null;
    private List<Scheduler> listOfSchedulers;

    @FXML
    ToggleGroup group1;
    @FXML
    TextArea logArea;

    @FXML
    private void initialize(){
        logArea.setStyle("-fx-font-family: monospace");
        generateWorkset();

        OutputStream out = new OutputStream() {
            @Override
            public void write(int b) throws IOException {
                appendText(String.valueOf((char) b));
            }
        };
        System.setOut(new PrintStream(out, true));


        /**
         * Creates a listener for the toggle group that makes it so only one radio button can be selected at a time
         */
        group1.selectedToggleProperty().addListener(new ChangeListener<Toggle>(){
            public void changed(ObservableValue<? extends Toggle> ov, Toggle old_toggle, Toggle new_toggle) {
            }
        });
    }

    public void appendText(String str) {
        Platform.runLater(() -> logArea.appendText(str));
    }

    @FXML
    private void generateWorkset() {
        System.out.println("Workset generator was created");
        wsg = new WorksetGenerator();
        WorksetReport.ReportWorkSet(wsg);

    }

    @FXML
    private void runProgram() {
       String selectedButton =  ((RadioButton) group1.getSelectedToggle()).getId();
       System.out.println(selectedButton);

       switch (selectedButton) {

           case "fifo": {

           }
           case "lwr": {

           }
           case "sjf": {

           }
           case "rr": {

           }

           case "all": {

           }
       }
    }



}
