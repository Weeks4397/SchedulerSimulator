package Testing;

import Generators.WorksetGenerator;
import Processes.process;
import Reports.Scheduler_Report;
import Reports.WorksetReport;
import Resources.Resource;
import Schedulers.Scheduler_FIFO;

public class FIFOtester {

    public static void main(String[] args) {

        WorksetGenerator Test = new WorksetGenerator();
        WorksetReport.ReportWorkSet(Test);
        Scheduler_FIFO FIFO = new Scheduler_FIFO(Test.Workset);

        //Begin the algorithm

        //P is the init process to get time with CPU
        process P = FIFO.ReadyProcesses.poll();
        FIFO.updateActiveProcess(P);

        //update all of the initial events
        FIFO.update_NextUnblock_and_Resource();
        FIFO.updateNextSchedExit();
        FIFO.updateNextArrival();
        FIFO.updateNextBlock();

        //Get the next event out of these
        FIFO.updateNextEvent();


        //begin handling the events until end of simulation
        //When there are no more events, all events will be equal to MAXVAL
        while (FIFO.getNextEvent() != Integer.MAX_VALUE) {
            FIFO.handleNextEvent();
        }
       // Scheduler_Report.CreateReport(Test,FIFO);


        System.out.println("time: " + FIFO.getTime());
        System.out.println("Active time: " + FIFO.getActiveTime());
        System.out.println("Idle time: " + FIFO.getIdleTime());

        System.out.println("Finished processes: " + FIFO.getFinishedQ().size());
        System.out.println();
        System.out.println(FIFO.FinishedQ.poll().toString());
        System.out.println();
        System.out.println(FIFO.FinishedQ.poll().toString());
        System.out.println();
        System.out.println(FIFO.FinishedQ.poll().toString());
        System.out.println();
        System.out.println(FIFO.FinishedQ.poll().toString());
        System.out.println();
        System.out.println(FIFO.FinishedQ.poll().toString());
        System.out.println();
        System.out.println(FIFO.FinishedQ.poll().toString());
        System.out.println();
        System.out.println(FIFO.FinishedQ.poll().toString());
        System.out.println();
        System.out.println(FIFO.FinishedQ.poll().toString());
        System.out.println();
        System.out.println(FIFO.FinishedQ.poll().toString());
        System.out.println();
        System.out.println(FIFO.FinishedQ.poll().toString());
        System.out.println();
        System.out.println(FIFO.FinishedQ.poll().toString());
        System.out.println();
        System.out.println(FIFO.FinishedQ.poll().toString());

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

        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println();
        for (Resource R: FIFO.TheResources){
            System.out.println(R);
            System.out.println();
        }


    }
}
