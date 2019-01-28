package Schedulers;

import Processes.process;
import java.util.List;
//TODO: Don't know if LWC and SRT include timeout, need to find out
/**
 * Scheduler_with_Preemption is a subclass of Scheduler_with_TimeOut.
 * This class extends Scheduler_with_TimeOut so that preemption is possible during each event.
 * Preemption:  A process in the ReadyQ has higher priority to run than the currently running process.
 *              The running process will be preempted and placed back on ReadyQ so the higher priority
 *              process can run.
 */
public abstract class Scheduler_with_Preemption extends Scheduler_with_TimeOut {

    /**
     *Constructor for scheduler with Preemption
     */
    public Scheduler_with_Preemption(List<process> masterList){
        super(masterList);
    }

}


