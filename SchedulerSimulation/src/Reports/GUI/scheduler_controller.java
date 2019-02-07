package Reports.GUI;

import Generators.WorksetGenerator;
import Reports.Scheduler_Report;
import Reports.WorksetReport;
import Schedulers.*;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.control.*;

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
    Button run_buttom;

    @FXML
    private void initialize(){
        run_buttom.setDisable(true);
        logArea.setEditable(false);
        logArea.appendText("Please create a workset to get started");
        logArea.setStyle("-fx-font-family: monospace");
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

    /**
     * Appends the text from the console to the textArea
     * @param str The text that you would like to add to the TextArea
     */
    public void appendText(String str) {
        Platform.runLater(() -> logArea.appendText(str));
    }

    @FXML
    private void generateWorkset() {
        logArea.clear();
        System.out.println("Creating Workset generator...");
        wsg = new WorksetGenerator();
        WorksetReport.ReportWorkSet(wsg);
        run_buttom.setDisable(false);
    }

    @FXML
    private void runProgram() {
        logArea.clear();
        System.out.println("Creating Scheduler Info...");
        String selectedButton =  ((RadioButton) group1.getSelectedToggle()).getId();
       //System.out.println(selectedButton);
       switch (selectedButton) {

           case "fifo": {
               Scheduler scheduler = new Scheduler_FIFO(wsg.Workset);
               scheduler.runAlgorithm();
               Scheduler_Report.CreateReport(wsg,scheduler);
               break;
           }
           case "lwc": {
               Scheduler scheduler = new Scheduler_LWC(wsg.Workset);
               scheduler.runAlgorithm();
               Scheduler_Report.CreateReport(wsg,scheduler);
               break;

           }
           case "sjf": {
               Scheduler scheduler = new Scheduler_SJF(wsg.Workset);
               scheduler.runAlgorithm();
               Scheduler_Report.CreateReport(wsg,scheduler);
               break;

           }
           case "rr": {
               Scheduler scheduler = new Scheduler_RR(wsg.Workset);
               scheduler.runAlgorithm();
               Scheduler_Report.CreateReport(wsg,scheduler);
               break;

           }

           case "all": {

           }
       }
    }



}
