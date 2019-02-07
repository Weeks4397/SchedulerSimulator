package Testing;

import Generators.WorksetGenerator;
import Processes.process;
import Reports.WorksetReport;
import Schedulers.Scheduler_LWC;

public class LWCtester {

    public static void main(String[] args) {

        WorksetGenerator Test = new WorksetGenerator();
        WorksetReport.ReportWorkSet(Test);
        Scheduler_LWC lwc = new Scheduler_LWC(Test.Workset);

        //Begin the algorithm

        //P is the init process to get time with CPU
        process P = lwc.ReadyProcesses.poll();
        lwc.updateActiveProcess(P);

        //update all of the initial events
        lwc.update_NextUnblock_and_Resource();
        lwc.updateNextSchedExit();
        lwc.updateNextArrival();
        lwc.updateNextBlock();
        lwc.updateNextTimeOut();

        //Get the next event out of these
        lwc.updateNextEvent();


        //begin handling the events until end of simulation
        //When there are no more events, all events will be equal to MAXVAL
        int I = 1;
        while (lwc.getNextEvent() != Integer.MAX_VALUE) {
            lwc.handleNextEvent();
        }
        // Final_Report.CreateReport(Test,lwc);


        System.out.println("time: " + lwc.getTime());
        System.out.println("Active time: " + lwc.getActiveTime());
        System.out.println("Idle time: " + lwc.getIdleTime());

        System.out.println("Finished processes: " + lwc.getFinishedQ().size());
        System.out.println();
        System.out.println(lwc.FinishedQ.poll().toString());
        System.out.println();
        System.out.println(lwc.FinishedQ.poll().toString());
        System.out.println();
        System.out.println(lwc.FinishedQ.poll().toString());
        System.out.println();
        System.out.println(lwc.FinishedQ.poll().toString());
        System.out.println();
        System.out.println(lwc.FinishedQ.poll().toString());
        System.out.println();
        System.out.println(lwc.FinishedQ.poll().toString());
        System.out.println();
        System.out.println(lwc.FinishedQ.poll().toString());
        System.out.println();
        System.out.println(lwc.FinishedQ.poll().toString());
        System.out.println();
        System.out.println(lwc.FinishedQ.poll().toString());
        System.out.println();
        System.out.println(lwc.FinishedQ.poll().toString());
        System.out.println();
        System.out.println(lwc.FinishedQ.poll().toString());
        System.out.println();
        System.out.println(lwc.FinishedQ.poll().toString());

        System.out.println();
        System.out.println();
        System.out.println(Test.Workset.get(0));
        System.out.println();
        System.out.println(Test.Workset.get(1));
        System.out.println();
        System.out.println(Test.Workset.get(2));
        System.out.println();
        System.out.println(Test.Workset.get(3));
        System.out.println();
        System.out.println(Test.Workset.get(4));
        System.out.println();
        System.out.println(Test.Workset.get(5));
        System.out.println();
        System.out.println(Test.Workset.get(6));
        System.out.println();
        System.out.println(Test.Workset.get(7));
    }
}
