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
     * Data members for scheduler_with_Timeout
     */

    /**
     * TimeQuantum is the length of time slice afforded to each process before they timeout
     */
    public int TimeQuantum;

    /**NextTimeout is potential event
     * NextTimeOut: The time at which a running process will complete its time slice and be placed back into ReadyQ
     */
    public int NextTimeOut;

    /**
     *Constructor for scheduler with time out
     * @param TQ    int  is the given time quantum
     */
    public Scheduler_with_TimeOut(List<process> masterList, int TQ){
        super(masterList);
        this.TimeQuantum = TQ;
        this.NextTimeOut = Integer.MAX_VALUE;
    }


    public void updateNextEvent () {
        int[] PossibleEvents = {this.NextArrival, this.NextSchedExit, this.NextTimeOut, this.NextUnblock, this.NextBlock};
        int min = PossibleEvents[0];
        for (int x : PossibleEvents) {
            if (x < min) {
                min = x;
            }
        }
        this.NextEvent = min;
    }

    public int getNextTimeOut() {
        return this.NextTimeOut;
    }

    /**
     * updateNextTimeOut mutates NextTimeOut to be the global time at which the
     * currently running process will complete its time slice.
     */
    public abstract void updateNextTimeOut();
}

