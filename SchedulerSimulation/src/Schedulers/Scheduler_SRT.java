package Schedulers;

import Processes.process;
import ReadyQueue.Comparators;
import ReadyQueue.SRT_Q;
import java.util.List;

/**
 * Scheduler_SRT is a subclass of Scheduler_withTimeOut_withPreemption.
 * SRT is a preemptive variant of SJF.
 * SRT is an algorithm that implements priority, preemption, and time slice
 * Shortest Remaining Time algorithm is based on (RunTime - CPUTime) for each process in the simulation.
 * Processes with lowest time are given priority.  Ties in priority are based on which process arrived first.
 */
public class Scheduler_SRT extends Scheduler_withTimeOut_withPreemption{

    /**
     * constructor for Scheduler_SRT
     * @param masterList    the work set of processes
     */
    public Scheduler_SRT(List<process> masterList){
        super(masterList);
        this.Type = "SRT";
        this.ReadyProcesses = new SRT_Q();
        this.CompObj = Comparators.By_SRT;
        this.populateReadyQ();
    }
}
