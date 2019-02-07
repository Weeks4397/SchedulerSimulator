package Testing;

import Generators.WorksetGenerator;
import Reports.Final_Report;
import Schedulers.*;

import java.util.ArrayList;
import java.util.List;

public class Scheduler_Tester {
    public static void main(String args[]) {
        WorksetGenerator wsg = new WorksetGenerator();
        List<Scheduler> schedulerList = new ArrayList();

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
    }
}
