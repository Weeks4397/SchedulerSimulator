package Testing;

import Generators.WorksetGenerator;
import Processes.process;
import Reports.WorksetReport;
import Schedulers.Scheduler_RR;

public class RRtester {

    public static void main(String[] args) {

        WorksetGenerator Test = new WorksetGenerator();
        WorksetReport.ReportWorkSet(Test);
        Scheduler_RR rr = new Scheduler_RR(Test.Workset);

        //Begin the algorithm

        //P is the init process to get time with CPU
        process P = rr.ReadyProcesses.poll();
        rr.updateActiveProcess(P);

        //update all of the initial events
        rr.update_NextUnblock_and_Resource();
        rr.updateNextSchedExit();
        rr.updateNextArrival();
        rr.updateNextBlock();
        rr.updateNextTimeOut();

        //Get the next event out of these
        rr.updateNextEvent();


        //begin handling the events until end of simulation
        //When there are no more events, all events will be equal to MAXVAL
        int I = 1;
        while (rr.getNextEvent() != Integer.MAX_VALUE) {
            rr.handleNextEvent();
        }
        // Final_Report.CreateReport(Test,rr);


        System.out.println("time: " + rr.getTime());
        System.out.println("Active time: " + rr.getActiveTime());
        System.out.println("Idle time: " + rr.getIdleTime());

        System.out.println("Finished processes: " + rr.getFinishedQ().size());
        System.out.println();
        System.out.println(rr.FinishedQ.poll().toString());
        System.out.println();
        System.out.println(rr.FinishedQ.poll().toString());
        System.out.println();
        System.out.println(rr.FinishedQ.poll().toString());
        System.out.println();
        System.out.println(rr.FinishedQ.poll().toString());
        System.out.println();
        System.out.println(rr.FinishedQ.poll().toString());
        System.out.println();
        System.out.println(rr.FinishedQ.poll().toString());
        System.out.println();
        System.out.println(rr.FinishedQ.poll().toString());
        System.out.println();
        System.out.println(rr.FinishedQ.poll().toString());
        System.out.println();
        System.out.println(rr.FinishedQ.poll().toString());
        System.out.println();
        System.out.println(rr.FinishedQ.poll().toString());
        System.out.println();
        System.out.println(rr.FinishedQ.poll().toString());
        System.out.println();
        System.out.println(rr.FinishedQ.poll().toString());

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
