package Schedulers;


import Processes.process;
import java.util.List;

/**
 * Scheduler_with_TimeOut is a subclass of Scheduler.
 * This class extends Scheduler so that timing out is implemented as an additional event.
 * Timing Out:  When a process completes its given time slice and is placed back onto the ReadyQ
 */

public abstract class Scheduler_with_TimeOut extends Scheduler {

    /**
     *Constructor for scheduler with time out
     */
    public Scheduler_with_TimeOut(List<process> masterList){
        super(masterList);
    }
}

