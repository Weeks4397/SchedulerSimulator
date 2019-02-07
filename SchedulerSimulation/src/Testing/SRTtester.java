package Testing;

import Generators.WorksetGenerator;
import Processes.process;
import Reports.WorksetReport;
import Schedulers.Scheduler_SRT;

public class SRTtester {

    public static void main(String[] args) {

        WorksetGenerator Test = new WorksetGenerator();
        WorksetReport.ReportWorkSet(Test);
        Scheduler_SRT srt = new Scheduler_SRT(Test.Workset);

        //Begin the algorithm

        //P is the init process to get time with CPU
        process P = srt.ReadyProcesses.poll();
        srt.updateActiveProcess(P);

        //update all of the initial events
        srt.update_NextUnblock_and_Resource();
        srt.updateNextSchedExit();
        srt.updateNextArrival();
        srt.updateNextBlock();
        srt.updateNextTimeOut();

        //Get the next event out of these
        srt.updateNextEvent();


        //begin handling the events until end of simulation
        //When there are no more events, all events will be equal to MAXVAL
        int I = 1;
        while (srt.getNextEvent() != Integer.MAX_VALUE) {
            srt.handleNextEvent();
        }
        // Final_Report.CreateReport(Test,srt);


        System.out.println("time: " + srt.getTime());
        System.out.println("Active time: " + srt.getActiveTime());
        System.out.println("Idle time: " + srt.getIdleTime());

        System.out.println("Finished processes: " + srt.getFinishedQ().size());
        System.out.println();
        System.out.println(srt.FinishedQ.poll().toString());
        System.out.println();
        System.out.println(srt.FinishedQ.poll().toString());
        System.out.println();
        System.out.println(srt.FinishedQ.poll().toString());
        System.out.println();
        System.out.println(srt.FinishedQ.poll().toString());
        System.out.println();
        System.out.println(srt.FinishedQ.poll().toString());
        System.out.println();
        System.out.println(srt.FinishedQ.poll().toString());
        System.out.println();
        System.out.println(srt.FinishedQ.poll().toString());
        System.out.println();
        System.out.println(srt.FinishedQ.poll().toString());
        System.out.println();
        System.out.println(srt.FinishedQ.poll().toString());
        System.out.println();
        System.out.println(srt.FinishedQ.poll().toString());
        System.out.println();
        System.out.println(srt.FinishedQ.poll().toString());
        System.out.println();
        System.out.println(srt.FinishedQ.poll().toString());

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
