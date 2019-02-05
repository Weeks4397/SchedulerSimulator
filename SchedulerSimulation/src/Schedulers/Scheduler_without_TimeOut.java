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
     *handleNextEvent does not include TimeOut as a potential event
     */
    public void handleNextEvent(){
        if (this.getNextEvent() == this.getNextUnblock()){
            this.handleNextUnblock();
        }
        else if (this.getNextEvent() == this.getNextArrival()){
            this.handleNextArrival();
        }
        else if (this.getNextEvent() == this.getNextSchedExit()){
            this.handleNextSchedExit();
        }
        //the event was a block
        else {
            this.handleNextBlock();
        }

        //update global time
        this.updateTime();

        //update overhead
        this.updateOverhead();

        //update what the next event will be
        this.updateNextEvent();
    }

    /**
     * arriveReadyQ is a helper method to help handle the events of a process unblocking or arriving from Master List
     * This method depends on whether the algorithm includes preemption
     */
    public abstract void arriveReadyQ(process P);

    /**
     *ExitCPU is a helper method to help handle the events of a process blocking or finishing
     * this method will not consider time out
     */
    public void ExitCPU(){
        if (this.ReadyProcesses.isEmpty()) {
            //if there are no processes ready to run, begin idol time
            this.updateStartIdolTime(this.getNextEvent() + this.getNextSCost());
            //there is no active process now
            this.ActiveProcess = null;

        }
        else {
            //update ActiveProcess to be the next ready process
            this.updateActiveProcess(this.ReadyProcesses.poll());

        }
        //update NextBlock and NextSchedExit because the ActiveProcess has changed
        this.updateNextBlock();
        this.updateNextSchedExit();
    }

    /**
     * runAlgorithm runs the algorithm without TimeOut
     */
    public void runAlgorithm() {
        //Begin the algorithm

        //P is the init process to get time with CPU
        process P = this.ReadyProcesses.poll();
        this.updateActiveProcess(P);

        //update all of the initial events
        this.update_NextUnblock_and_Resource();
        this.updateNextSchedExit();
        this.updateNextArrival();
        this.updateNextBlock();

        //Get the next event out of these
        this.updateNextEvent();


        //begin handling the events until end of simulation
        //When there are no more events, all events will be equal to MAXVAL
        while (this.getNextEvent() != Integer.MAX_VALUE) {
            this.handleNextEvent();
        }
    }


}
