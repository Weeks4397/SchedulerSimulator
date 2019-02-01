package Schedulers;

import Processes.process;
import ReadyQueue.Comparators;
import ReadyQueue.LWC_Q;
import java.util.List;


/**
 * Scheduler_LWC is a subclass of Scheduler_withTimeOut_withPreemption.
 * LWC is an algorithm that implements priority, preemption, and time slice
 * Least Work Completed algorithm is based on CPUTime for each process in the simulation.
 * Processes with lowest CPUTime are given priority.  Ties in priority are based on which process arrived first.
 */
public class Scheduler_LWC extends Scheduler_withTimeOut_withPreemption {

    /**
     * constructor for Scheduler_LWC
     * @param masterList    the work set of processes
     */
    public Scheduler_LWC(List<process> masterList){
        super(masterList);
        this.ReadyProcesses = new LWC_Q();
        this.CompObj = Comparators.By_LWC;
        this.populateReadyQ();
    }
}
