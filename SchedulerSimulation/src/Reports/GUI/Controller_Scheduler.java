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
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.effect.DropShadow;
import javafx.scene.paint.Color;

import javax.naming.spi.ObjectFactory;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Controller_Scheduler {

    /**
     * A WorksetGenerator that will be used for creating reports
     */
    private static WorksetGenerator wsg = null;

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
     * The workset_Button is the button that generates a workset
     */
    @FXML
    Button workset_button;

    /**
     * Same as onCreate (in Android). This is called when the window is made
     * currently sets properties for the run_button and log area. It also adds
     * listener to group1 (RadioButtonGroup) and redirects the console to the textArea.
     */
    @FXML
    private void initialize(){
        //Disable run button until workset is generated
        run_buttom.setDisable(true);

        // Add a glow around the workset_button
        DropShadow boarderGlow = new DropShadow();
        boarderGlow.setColor(Color.RED);
        boarderGlow.setOffsetX(0f);
        boarderGlow.setOffsetY(0f);
        boarderGlow.setWidth(20);
        boarderGlow.setHeight(20);
        workset_button.setEffect(boarderGlow);


        // Format the text area so it looks like the console and make it uneditable
        logArea.setEditable(false);
        logArea.appendText("Please create a workset to get started");
        logArea.setStyle("-fx-font-family: monospace");

        // Redirect the console to the textArea
        ConsoleRedirect console = new ConsoleRedirect(logArea);
        console.start();


         // Creates a listener for the toggle group that makes it so only one radio button can be selected at a time
        group1.selectedToggleProperty().addListener(new ChangeListener<Toggle>(){
            public void changed(ObservableValue<? extends Toggle> ov, Toggle old_toggle, Toggle new_toggle) {
            }
        });

    }

    /**
     * Generates a WorkSetGenerator for the schedulers to use
     * Is called when the the Generate Workset button is pressed
     */
    @FXML
    private void generateWorkset() {
        workset_button.setEffect(null);
        logArea.clear();
        wsg = new WorksetGenerator();
        WorksetReport.ReportWorkSet(wsg);
        run_buttom.setDisable(false);
    }

    /**
     * Checks if a RadioButton in the radio is pressed
     * @param group a Toggle Group
     * @return true if one is pressed otherwise false
     */
    public static boolean isSelected(ToggleGroup group) {
        ObservableList<Toggle> o = group.getToggles();

        for (Toggle t : o){
            RadioButton rb = (RadioButton) t;
            if (rb.isSelected())
                return true;
        }
        return false;
    }

    /**
     * This is called when the run button is pressed. It creates the scheduler obj's based on the algorithm inputted
     * and then generates the report and logs it to the logArea (textArea)
     */
    @FXML
    private void runProgram() {
        if (isSelected(group1) == false){
            logArea.clear();
            logArea.appendText("Please select a button");
            return;
        }

        logArea.clear();
        String selectedButton =  ((RadioButton) group1.getSelectedToggle()).getId();
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
               Final_Report.CreateReport(wsg, scheduler);
               break;
           }

           case "srt": {
               Scheduler scheduler = new Scheduler_SRT(wsg.Workset);
               scheduler.runAlgorithm();
               Final_Report.CreateReport(wsg, scheduler);
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

               // SRT Scheduler
               Scheduler scheduler5 = new Scheduler_SRT(wsg.Workset);
               scheduler5.runAlgorithm();
               schedulerList.add(scheduler5);

               Final_Report.createAll(wsg, schedulerList);
               break;
           }
       }
    }
}
