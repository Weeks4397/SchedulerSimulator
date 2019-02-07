/**
 * This is the controller class for the scheduler fxml file
 * It handles all listeners and functionality for the scheduler
 */

package Reports.GUI;

import Generators.WorksetGenerator;
import Reports.Final_Report;
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
import java.util.ArrayList;
import java.util.List;

public class scheduler_controller {

    private static WorksetGenerator wsg = null;
    private List<Scheduler> listOfSchedulers;

    /**
     * RadioButton group that houses the scheduler options
     */
    @FXML
    ToggleGroup group1;

    /**
     * The Text Area where the console logs are printed to
     */
    @FXML
    TextArea logArea;

    /**
     * the run_button is the button that runs the simulation
     */
    @FXML
    Button run_buttom;

    /**
     * Same as onCreate (in Android). THis is called when the window is made
     * currently sets prpperties for the run_button and log area. It also adds
     * listener to group1 (RadioButtonGroup) and a Output and InputStream that monitors the cmd for input.
     * If there is input then it will add it to the logArea (TextArea).
     */
    @FXML
    private void initialize(){
        run_buttom.setDisable(true);
        logArea.setEditable(false);
        logArea.appendText("Please create a workset to get started");
        logArea.setStyle("-fx-font-family: monospace");
        OutputStream out = new OutputStream() {

            /**
             * This overrides the write method in the OutputStream so that it can add the text the logArea
             * @param b
             * @throws IOException
             */
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
    @FXML
    public void appendText(String str) {
        Platform.runLater(() -> logArea.appendText(str));
    }

    /**
     * Generates a WorkSetGenerator for the schedulers to use
     * Is called when the the Generate Workset button is pressed
     */
    @FXML
    private void generateWorkset() {
        logArea.clear();
        System.out.println("Creating Workset generator...");
        wsg = new WorksetGenerator();
        WorksetReport.ReportWorkSet(wsg);
        run_buttom.setDisable(false);
    }

    /**
     * This is called when the run button is pressed. It creates the scheduler obj's based on the algorithm inputted
     * and then generates the report and logs it to the logArea (textArea)
     */
    @FXML
    private void runProgram() {
        if(((RadioButton) group1.getSelectedToggle()).getId() == null){
            logArea.clear();
            logArea.appendText("Please select a Scheduler to run");
            return;
        }

        logArea.clear();
        System.out.println("Creating Scheduler Info...");
        String selectedButton =  ((RadioButton) group1.getSelectedToggle()).getId();
       //System.out.println(selectedButton);
       switch (selectedButton) {

           case "fifo": {
               Scheduler scheduler = new Scheduler_FIFO(wsg.Workset);
               scheduler.runAlgorithm();
               Final_Report.CreateReport(wsg,scheduler);
               break;
           }
           case "lwc": {
               Scheduler scheduler = new Scheduler_LWC(wsg.Workset);
               scheduler.runAlgorithm();
               Final_Report.CreateReport(wsg,scheduler);
               break;

           }
           case "sjf": {
               Scheduler scheduler = new Scheduler_SJF(wsg.Workset);
               scheduler.runAlgorithm();
               Final_Report.CreateReport(wsg,scheduler);
               break;

           }
           case "rr": {
               Scheduler scheduler = new Scheduler_RR(wsg.Workset);
               scheduler.runAlgorithm();
               Final_Report.CreateReport(wsg,scheduler);
               break;

           }

           case "all": {

               List<Scheduler> schedulerList = new ArrayList<>();

               // RR scheduler
               Scheduler scheduler = new Scheduler_RR(wsg.Workset);
               scheduler.runAlgorithm();
               schedulerList.add(scheduler);

               // SJF Scheduler
               Scheduler scheduler2 = new Scheduler_SJF(wsg.Workset);
               scheduler2.runAlgorithm();
               schedulerList.add(scheduler2);

               // FIFO Scheduler
               Scheduler scheduler3 = new Scheduler_FIFO(wsg.Workset);
               scheduler3.runAlgorithm();
               schedulerList.add(scheduler3);

               // LWC Scheduler
               Scheduler scheduler4 = new Scheduler_LWC(wsg.Workset);
               scheduler4.runAlgorithm();
               schedulerList.add(scheduler4);

               Final_Report.createAll(wsg, schedulerList);
               break;
           }
       }
    }



}
