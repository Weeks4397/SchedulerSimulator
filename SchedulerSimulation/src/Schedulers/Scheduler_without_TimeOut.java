package Schedulers;

import Processes.process;
import java.util.List;

/**
 * Scheduler_without_TimeOut is a subclass of Scheduler.
 * This class extends Scheduler with out including time out as a possible event.
 */
public abstract class Scheduler_without_TimeOut extends Scheduler {

    /**
     *Constructor for scheduler without time out
     */
    public Scheduler_without_TimeOut(List<process> masterList){
        super(masterList);
    }

    /**
     * arriveReadyQ is a helper method to help handle the events of a process unblocking or arriving from Master List
     */
    public abstract void arriveReadyQ(process P);

    /**
     *ExitCPU is a helper method to help handle the events of a process blocking or finishing
     */
    public abstract void ExitCPU();

    /**
     * updateNextEvent does not include time out as a possible event
     */
    public void updateNextEvent () {
        int[] PossibleEvents = {this.NextArrival, this.NextSchedExit, this.NextUnblock, this.NextBlock};
        int min = PossibleEvents[0];
        for (int x : PossibleEvents) {
            if (x < min) {
                min = x;
            }
        }
        this.NextEvent = min;
    }

    /**
     *handleNextEvent will include TimeOut as a potential event
     */
    public abstract void handleNextEvent();

    /**
     * methods to handle each individual event
     */
    public abstract void handleNextUnblock();
    public abstract void handleNextArrival();
    public abstract void handleNextSchedExit();
    public abstract void handleNextBlock();

}
