package Testing;

import Generators.WorksetGenerator;
import Reports.Scheduler_Report;
import Schedulers.Scheduler;
import Schedulers.Scheduler_FIFO;

public class Scheduler_Tester {
    public static void main(String args[]) {
        WorksetGenerator wsg = new WorksetGenerator();
        Scheduler scheduler = new Scheduler_FIFO(wsg.Workset);
        Scheduler_Report.CreateReport(wsg, scheduler);
    }
}
