package Schedulers;

import Processes.process;
import ReadyQueue.FIFO_Q;

import java.util.List;

public class Scheduler_FIFO extends Scheduler_withoutPreemption_NoTimeOut {

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
