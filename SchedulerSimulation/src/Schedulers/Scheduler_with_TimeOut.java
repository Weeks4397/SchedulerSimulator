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
     */
    public Scheduler_with_TimeOut(List<process> masterList){
        super(masterList);
        this.NextTimeOut = Integer.MAX_VALUE;
    }


    /**
     * getters for scheduler with timeout
     */
    public int getNextTimeOut() {
        return this.NextTimeOut;
    }

    public int getTimeQuantum() {
        return this.TimeQuantum;
    }

    /**
     * arriveReadyQ is a helper method to help handle the events of a process unblocking or arriving from Master List
     */
    public abstract void arriveReadyQ(process P);

    /**
     *ExitCPU is a helper method to help handle the events of a process blocking, finishing, or timing out
     */
    public abstract void ExitCPU();

    /**
     * updateNextEvent now includes NextTimeout as a potential event
     */
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

    /**
     *handleNextEvent will include TimeOut as a potential event
     */
    public abstract void handleNextEvent();

    /**
     * methods to handle each individual event
     * handleNextTimeOut is included to handle the event of a process completing its time slice
     */
    public abstract void handleNextUnblock();
    public abstract void handleNextArrival();
    public abstract void handleNextSchedExit();
    public abstract void handleNextBlock();
    public abstract void handleNextTimeOut();


    /**
     * updateNextTimeOut mutates NextTimeOut to be the global time at which the
     * currently running process will complete its time slice.
     * If there is no active process, set NextTimeOut to MAXVAL.
     */
    public void updateNextTimeOut(){
        if (this.getActiveProcess() == null){
            this.NextTimeOut = Integer.MAX_VALUE;
        }
        else{
            this.NextSchedExit= this.getNextEvent() + this.getTimeQuantum();
        }
    }


}

