package Testing;

import Generators.WorksetGenerator;
import Processes.process;
import Resources.Resource;
import Reports.WorksetReport;
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


        //begin handling the events until end of simulation
        //When there are no more events, all events will be equal to MAXVAL
        int I = 1;
        while (FIFO.getNextEvent() != Integer.MAX_VALUE){
            FIFO.handleNextEvent();
            System.out.println(I++);
        }

        System.out.println(FIFO.getTime());
        System.out.println(FIFO.getActiveTime());
        System.out.println(FIFO.getIdleTime());


    }
}
