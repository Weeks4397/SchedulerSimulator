package Schedulers;

import Processes.process;

import java.util.Comparator;
import java.util.List;

/**
 * Scheduler_withPreemption_NoTimeout is a subclass of Scheduler_without_Timeout.
 * This class extends Scheduler_without_Timeout to include preemption in the handling of events
 * Algorithms that are subclasses of this are...currently none
 */
public class Scheduler_withoutTimeOut_withPreemption extends Scheduler_without_TimeOut {

    /**
     * the comparator object being used in the priority queue for the algorithm
     */
    public Comparator<process>  CompObj;

    /**
     *Constructor for scheduler without preemption and with out time out
     */
    public Scheduler_withoutTimeOut_withPreemption(List<process> masterList){
        super(masterList);
    }

    /**
     * arriveReadyQ does take into account preemption
     */
    public void arriveReadyQ(process P){
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

                //The ActiveProcess has changed, update NextBlock and NextSchedExit
                this.updateNextBlock();
                this.updateNextSchedExit();
            }
        }
        else {
            //if there is no running process, make P the new running process
            this.updateActiveProcess(P);

            //The ActiveProcess has changed, update NextBlock and NextSchedExit
            this.updateNextBlock();
            this.updateNextSchedExit();

            //Update idol time because the CPU is back to being active
            this.updateIdolTime(this.getNextEvent() - this.getStartIdleTime());
        }
        //Update Ps ready time
        P.NextReadyTime = this.getNextEvent();
    }


}
