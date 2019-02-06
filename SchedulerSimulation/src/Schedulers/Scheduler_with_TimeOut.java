package Schedulers;


import Processes.process;
import Resources.Resource;

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
    public final int TimeQuantum = 50;

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
        else if(this.getNextEvent() == this.getNextTimeOut()){
            this.handleNextTimeOut();
        }
        else if(this.getNextEvent() == this.getNextBlock()){
            this.handleNextBlock();
        }
        //the event was a timeout
        else {
            this.handleNextTimeOut();
        }

        //update global time
        this.updateTime();

        //update overhead
        this.updateOverhead();

        //update what the next event will be
        this.updateNextEvent();
    };

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
            this.NextTimeOut= this.getNextEvent() + this.getNextSCost() + this.getTimeQuantum();
        }
    }

    /**
     * Handles the next time out event
     */
    public void handleNextTimeOut(){
        //update the active processes CPUTime, SchedInstant_count, and Timeout_count
        this.ActiveProcess.updateCPU (this.getNextEvent() - this.getTime());
        this.ActiveProcess.SchedInstant_Count += 1;
        this.ActiveProcess.TimeOut_Count += 1;

        //update Active time of CPU as well
        this.updateActiveTime(this.getNextEvent() - this.getTime());


        //place the running process back into readyQ
        this.ReadyProcesses.add(this.ActiveProcess);

        //Determine scheduler cost for this event
        if(this.ReadyProcesses.size() == 1){
            this.updateNextSCost(SCostMin);
        }
        else if(this.ReadyProcesses.peek().getStringID() == this.ActiveProcess.getStringID()){
            this.updateNextSCost(SCostComp);
        }
        else{
            this.updateNextSCost(SCostFull);
        }

        this.TimeOut_Count += 1;

        //The active process has exited CPU.
        //Bring in next process to run if there is one.
        this.ExitCPU();


    }

    /**
     * arriveReadyQ is a helper method to help handle the events of a process unblocking or arriving from Master List
     */
    public abstract void arriveReadyQ(process P);

    /**
     *ExitCPU is a helper method to help handle the events of a process blocking, finishing, or timing out
     */
    public void ExitCPU(){
        if (this.ReadyProcesses.isEmpty()) {
            //if there are no processes ready to run, begin idol time
            this.updateStartIdolTime(this.getNextEvent() + this.getNextSCost());
            //there is no active process now
            this.ActiveProcess = null;

        }
        else {
            //p is the next ready process that will run with the cpu
            process p = this.ReadyProcesses.poll();
            //update ps total ready time and number of sched Instants
            p.TotalReadyTime = this.getNextEvent() + this.getNextSCost() - p.getStartReadyTime();
            p.SchedInstant_Count += 1;
            //update ActiveProcess to be the next ready process
            this.updateActiveProcess(p);


        }
        //Update NextBlock, NextSchedExit, and NextTimeOut because the ActiveProcess has changed
        this.updateNextBlock();
        this.updateNextSchedExit();
        this.updateNextTimeOut();
    }

    /**
     * runAlgorithm runs the algorithm with TimeOut
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
        this.updateNextTimeOut();

        //Get the next event out of these
        this.updateNextEvent();


        //begin handling the events until end of simulation
        //When there are no more events, all events will be equal to MAXVAL
        while (this.getNextEvent() != Integer.MAX_VALUE) {
            this.handleNextEvent();
        }
        for (Resource R: this.TheResources){
            R.IdleTime += this.Time - R.StartIdleTime;
        }
    }



}

