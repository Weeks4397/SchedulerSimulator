package Schedulers;

import Processes.process;

import java.util.List;

/**
 * Scheduler_withoutPreemption_withTimeout is a subclass of Scheduler_with_Timeout.
 * This class extends Scheduler_with_Timeout to not include preemption in the handling of events
 * Algorithms that are subclasses of this are RR.
 */
public abstract class Scheduler_withoutPreemption_withTimeOut extends Scheduler_with_TimeOut {

    /**
     *Constructor for scheduler without preemption and with out time out
     */
    public Scheduler_withoutPreemption_withTimeOut(List<process> masterList){
        super(masterList);
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
        else if(this.getNextEvent() == this.getNextTimeOut()){
            this.handleNextTimeOut();
        }
        //the event was a block
        else {
            this.handleNextBlock();
        }

        //update global time
        this.updateTime();

        //update what the next event will be
        this.updateNextEvent();
    };


    /**
     * methods to handle each individual event
     */

    /**
     * Handles the next unblock event without considering preemption
     */
    public void handleNextUnblock(){
        //P is the process that is unblocking
        process P = this.getNextUnblockResource().finishService();

        //P has finished service and now must arrive to readyQ
        this.arriveReadyQ(P);

        //update the next unblock event
        this.update_NextUnblock_and_Resource();

    }

    /**
     * Handles the next arrival event with out considering preemption
     */
    public void handleNextArrival(){
        //P is the process arriving from the MasterList
        process P = this.getMasterList().get(this.getCurrentIndex());

        //Handle P arriving to the readyQ
        this.arriveReadyQ(P);

        //update the current masterList index
        this.updateCurrentIndex();

        //update the next arrival event
        this.updateNextArrival();
    }

    /**
     * Handles the next finishing event with out considering preemption
     */
    public void handleNextSchedExit(){
        //The active process is finshed running, update its CPUTime, finished time and add it to the FinishedQ.
        this.ActiveProcess.updateCPU (this.getNextEvent() - this.getTime());
        this.ActiveProcess.updateFinishTime(this.getNextEvent());
        this.FinishedQ.add(this.getActiveProcess());

        //update Active time of CPU as well
        this.updateActiveTime(this.getNextEvent() - this.getTime());

        //The active process has exited CPU.
        //Bring in next process to run if there is one.
        this.ExitCPU();
    }


    /**
     * Handles the next blocking event with out considering preemption
     */
    public void handleNextBlock(){
        //update the active processes CPUTime
        this.ActiveProcess.updateCPU (this.getNextEvent() - this.getTime());

        //update Active time of CPU as well
        this.updateActiveTime(this.getNextEvent() - this.getTime());

        //Check to see what resource the process is blocking on and send it to that resource
        if (this.getActiveProcess().getNextBlockResource() == "A") {
            TheResources[0].arrivingProcess(this.ActiveProcess, this.getNextEvent());
        }
        else if (this.getActiveProcess().getNextBlockResource() == "B") {
            TheResources[1].arrivingProcess(this.ActiveProcess, this.getNextEvent());
        }
        else {
            TheResources[2].arrivingProcess(this.ActiveProcess, this.getNextEvent());
        }

        //Update NextUnblock because a process has blocked
        this.update_NextUnblock_and_Resource();

        //The active process has exited CPU.
        //Bring in next process to run if there is one.
        this.ExitCPU();

    }

    /**
     * Handles the next time out event with out considering preemption
     */
    public void handleNextTimeOut(){
        //update the active processes CPUTime
        this.ActiveProcess.updateCPU (this.getNextEvent() - this.getTime());

        //update Active time of CPU as well
        this.updateActiveTime(this.getNextEvent() - this.getTime());

        //place the running process back into readyQ
        this.ReadyProcesses.add(this.ActiveProcess);

        //The active process has exited CPU.
        //Bring in next process to run if there is one.
        this.ExitCPU();
    }



    /**
     * arriveReadyQ does not take into account preemption
     */
    public void arriveReadyQ(process P){
        if(this.getActiveProcess() != null){
            //if there is a running process, increment it's CPUTime
            //Also increment CPU ActiveTime
            this.ActiveProcess.updateCPU (this.getNextEvent()- this.getTime());
            this.updateActiveTime(this.getNextEvent() - this.getTime());

            //add P into the ReadyQ
            this.ReadyProcesses.add(P);
        }
        else {
            //if there is no running process, make P the new running process
            this.updateActiveProcess(P);

            //Update NextBlock, NextSchedExit, and NextTimeOut because the ActiveProcess has changed
            this.updateNextBlock();
            this.updateNextSchedExit();
            this.updateNextTimeOut();

            //Update idol time because the CPU is back to being active
            this.updateIdolTime(this.getNextEvent() - this.getStartIdleTime());
        }
        //Update Ps ready time
        P.NextReadyTime = this.getNextEvent();
    }

    /**
     *ExitCPU does not take into account preemption
     */
    public void ExitCPU(){
        if (this.ReadyProcesses.isEmpty()) {
            //if there are no processes ready to run, begin idol time
            this.updateStartIdolTime(this.getNextEvent());
            //there is no active process now
            this.ActiveProcess = null;

            //Update NextBlock, NextSchedExit, and NextTimeOut because the ActiveProcess has changed
            this.updateNextBlock();
            this.updateNextSchedExit();
            this.updateNextTimeOut();
        }
        else {
            //update ActiveProcess to be the next ready process
            this.updateActiveProcess(this.ReadyProcesses.poll());

            //Update NextBlock, NextSchedExit, and NextTimeOut because the ActiveProcess has changed
            this.updateNextBlock();
            this.updateNextSchedExit();
            this.updateNextTimeOut();
        }
    }
}
