package Schedulers;

import Processes.process;

import java.util.Comparator;
import java.util.List;

/**
 * Scheduler_withPreemption_withTimeout is a subclass of Scheduler_with_Timeout.
 * This class extends Scheduler_with_Timeout to include preemption in the handling of events
 * Algorithms that are subclasses of this are SRT and LWC
 */
public abstract class Scheduler_withTimeOut_withPreemption extends Scheduler_with_TimeOut {

    /**
     * the comparator object being used in the priority queue for the algorithm
     */
    public Comparator<process> CompObj;

    /**
     *Constructor for scheduler without preemption and with out time out
     */
    public Scheduler_withTimeOut_withPreemption(List<process> masterList){
        super(masterList);
    }


    /**
     * arriveReadyQ does take into account preemption
     */
    public void arriveReadyQ(process P){
        //Determine scheduler cost for this event
        if(this.ReadyProcesses.isEmpty()){
            //There is no scheduler cost for this event.
            this.updateNextSCost(0);
        }
        else {
            this.updateNextSCost(SCostFull);
        }

        if(this.getActiveProcess() != null){
            //if there is a running process, increment it's CPUTime
            //Also increment CPU ActiveTime
            this.ActiveProcess.updateCPU (this.getNextEvent()- this.getTime());
            this.updateActiveTime(this.getNextEvent() - this.getTime());

            //add P into the ReadyQ
            this.ReadyProcesses.add(P);

            //Consider preempting the currently running process
            if(CompObj.compare(this.getReadyProcesses().peek(), this.getActiveProcess())  == 1){
                //if the process in the ReadyQ has priority over the running process, preempt

                //Add the running process back into the ReadyQ
                this.ReadyProcesses.add(this.ActiveProcess);

                //Pnext is the process with the higher priority
                process Pnext = this.ReadyProcesses.poll();

                //Make Pnext the active process
                this.updateActiveProcess(Pnext);

                //The ActiveProcess has changed, update NextBlock, NextSchedExit, and NextTimeOut
                this.updateNextBlock();
                this.updateNextSchedExit();
                this.updateNextTimeOut();
            }
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

}
