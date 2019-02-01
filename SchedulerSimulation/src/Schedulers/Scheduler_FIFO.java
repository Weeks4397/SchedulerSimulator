package Schedulers;

import Processes.process;
import ReadyQueue.FIFO_Q;

import java.util.List;

/**
 * Scheduler_FIFO is a subclass of Scheduler_withoutTimeOut_withoutPreemption.
 * FIFO is a first come, first serve algorithm that does not include preemption or time slice.
 */
public class Scheduler_FIFO extends Scheduler_withoutTimeOut_withoutPreemption {

    /**
     * constructor for Scheduler_FIFO
     * @param masterList    the work set of processes
     */
    public Scheduler_FIFO(List<process> masterList){
        super(masterList);
        this.ReadyProcesses = new FIFO_Q();
        this.populateReadyQ();
    }



}
