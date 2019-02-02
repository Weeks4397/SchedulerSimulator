package Schedulers;

import Processes.process;
import ReadyQueue.RR_Q;
import java.util.List;

/**
 * Scheduler_RR is a subclass of Scheduler_withTimeOut_withoutPreemption
 * Round Robin is a first come first serve algorithm that incorporates time slice
 */
public class Scheduler_RR extends Scheduler_withTimeOut_withoutPreemption {

    /**
     * constructor for Scheduler_RR
     * @param masterList    the work set of processes
     */
    public Scheduler_RR(List<process> masterList){
        super(masterList);
        this.Type = "RR";
        this.ReadyProcesses = new RR_Q();
        this.populateReadyQ();
    }
}
