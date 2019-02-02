package Schedulers;


import Processes.process;
import ReadyQueue.SJF_Q;
import java.util.List;

/**
 * Scheduler_SJF is a subclass of Scheduler_withoutTimeOut_withoutPreemption.
 * SJF is an algorithm that implements priority.
 * Shortest Job First algorithm is based on RunTime for each process in the simulation.
 * Processes with lowest RunTime are given priority.  Ties in priority are based on which process arrived first.
 */
public class Scheduler_SJF extends Scheduler_withoutTimeOut_withoutPreemption{

    /**
     * constructor for Scheduler_SRT
     * @param masterList    the work set of processes
     */
    public Scheduler_SJF(List<process> masterList){
        super(masterList);
        this.Type = "SJF";
        this.ReadyProcesses = new SJF_Q();
        this.populateReadyQ();
    }
}
