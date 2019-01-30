package Testing;

import Generators.WorksetGenerator;
import Processes.process;
import Resources.Resource;
import Schedulers.Report;
import Schedulers.Scheduler_FIFO;

public class FIFOtester {

    public static void main(String[] args){

        WorksetGenerator Test = new WorksetGenerator();
        //Report.ReportWorkSet(Test);
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

        System.out.println("TIME  " + FIFO.Time);
        System.out.println("ACtiveTIme  " + FIFO.ActiveTime);
        System.out.println("IDLEtime  " + FIFO.IdleTime);
        System.out.println(FIFO.TheResources[0].getType() +"    " + FIFO.TheResources[0].numOfBlocks);

        System.out.println("Next arrival  " + FIFO.NextArrival);
        System.out.println("next sched exit  " + FIFO.NextSchedExit);
        System.out.println("next unblock  " + FIFO.NextUnblock);
        System.out.println("next block  " + FIFO.NextBlock);

        System.out.println("Next Event Time "+  FIFO.NextEvent);

        System.out.println("FinishedQ size  " + FIFO.FinishedQ.size());

        //System.out.println("init Q size"+ FIFO.ReadyProcesses.

        int NE = FIFO.NextEvent;
        //begin handling the events until end of simulation
        //When there are no more events, all events will be equal to MAXVAL
        int i = 0;
        while (i < 20){
            FIFO.handleNextEvent();
            i++;
        }

        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println("exit time  " +  FIFO.Time);
        System.out.println("exit active time  " + FIFO.ActiveTime);
        System.out.println("exit idle time  " + FIFO.IdleTime);
        System.out.println(FIFO.TheResources[0].getType() +"    " + FIFO.TheResources[0].numOfBlocks);
        System.out.println("Next event time   " + FIFO.NextEvent);
        System.out.println("size of the FinishedQ   "   + FIFO.FinishedQ.size());
        process P1 = FIFO.FinishedQ.poll();
        System.out.println("exit Next arrival  " + FIFO.NextArrival);
        System.out.println("exit next sched exit  " + FIFO.NextSchedExit);
        System.out.println("exit next unblock  " + FIFO.NextUnblock);
        System.out.println("exit next block  " + FIFO.NextBlock);

        System.out.println("Next Event Time "+  FIFO.NextEvent);


        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println(P1.getRunTime());
        System.out.println(P1.getCPUTime());
        System.out.println(FIFO.CurrentIndex);
        System.out.println(FIFO.getMasterList().get(FIFO.CurrentIndex).getArrivalTime());
        System.out.println(FIFO.getNextEvent());

        System.out.println(P1.toString());
        Resource A = FIFO.TheResources[0];
        System.out.println(A.toString());


    }
}
