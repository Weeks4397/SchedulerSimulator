package Testing;

import Generators.WorksetGenerator;
import Processes.process;
import Reports.WorksetReport;
import Schedulers.Scheduler_SJF;

public class SJFtester {

    public static void main(String[] args) {

        WorksetGenerator Test = new WorksetGenerator();
        WorksetReport.ReportWorkSet(Test);
        Scheduler_SJF sjf = new Scheduler_SJF(Test.Workset);

        //Begin the algorithm

        //P is the init process to get time with CPU
        process P = sjf.ReadyProcesses.poll();
        sjf.updateActiveProcess(P);

        //update all of the initial events
        sjf.update_NextUnblock_and_Resource();
        sjf.updateNextSchedExit();
        sjf.updateNextArrival();
        sjf.updateNextBlock();

        //Get the next event out of these
        sjf.updateNextEvent();


        //begin handling the events until end of simulation
        //When there are no more events, all events will be equal to MAXVAL
        int I = 1;
        while (sjf.getNextEvent() != Integer.MAX_VALUE) {
            sjf.handleNextEvent();
        }
        // Scheduler_Report.CreateReport(Test,sjf);


        System.out.println("time: " + sjf.getTime());
        System.out.println("Active time: " + sjf.getActiveTime());
        System.out.println("Idle time: " + sjf.getIdleTime());

        System.out.println("Finished processes: " + sjf.getFinishedQ().size());
        System.out.println();
        System.out.println(sjf.FinishedQ.poll().toString());
        System.out.println();
        System.out.println(sjf.FinishedQ.poll().toString());
        System.out.println();
        System.out.println(sjf.FinishedQ.poll().toString());
        System.out.println();
        System.out.println(sjf.FinishedQ.poll().toString());
        System.out.println();
        System.out.println(sjf.FinishedQ.poll().toString());
        System.out.println();
        System.out.println(sjf.FinishedQ.poll().toString());
        System.out.println();
        System.out.println(sjf.FinishedQ.poll().toString());
        System.out.println();
        System.out.println(sjf.FinishedQ.poll().toString());
        System.out.println();
        System.out.println(sjf.FinishedQ.poll().toString());
        System.out.println();
        System.out.println(sjf.FinishedQ.poll().toString());
        System.out.println();
        System.out.println(sjf.FinishedQ.poll().toString());
        System.out.println();
        System.out.println(sjf.FinishedQ.poll().toString());

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
