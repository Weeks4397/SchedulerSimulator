package Testing;

import Generators.WorksetGenerator;
import Processes.process;
import Reports.WorksetReport;
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
        System.out.println(FIFO.TheResources[0].getNumOfBlocks());
        System.out.println(FIFO.TheResources[1].getNumOfBlocks());
        System.out.println(FIFO.TheResources[2].getNumOfBlocks());
        System.out.println();
        System.out.println(FIFO.TheResources[0].getTotalBlockTime());
        System.out.println(FIFO.TheResources[1].getTotalBlockTime());
        System.out.println(FIFO.TheResources[2].getTotalBlockTime());
        System.out.println();
        System.out.println();
        System.out.println();


        Scheduler_FIFO FIFO1 = new Scheduler_FIFO(Test.Workset);

        //Begin the algorithm

        //P is the init process to get time with CPU
        process P1 = FIFO1.ReadyProcesses.poll();
        FIFO1.updateActiveProcess(P1);

        //update all of the initial events
        FIFO1.update_NextUnblock_and_Resource();
        FIFO1.updateNextSchedExit();
        FIFO1.updateNextArrival();
        FIFO1.updateNextBlock();

        //Get the next event out of these
        FIFO1.updateNextEvent();


        //begin handling the events until end of simulation
        //When there are no more events, all events will be equal to MAXVAL
        while (FIFO1.getNextEvent() != Integer.MAX_VALUE) {
            FIFO1.handleNextEvent();
        }

        System.out.println("time: " + FIFO1.getTime());
        System.out.println("Active time: " + FIFO1.getActiveTime());
        System.out.println("Idle time: " + FIFO1.getIdleTime());
    }
}
