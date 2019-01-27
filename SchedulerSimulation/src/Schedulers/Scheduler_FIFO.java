package Schedulers;

import Processes.process;
import ReadyQueue.FIFO_Q;

import java.util.List;

public class Scheduler_FIFO extends Scheduler {

    /**
     * constructor for Scheduler_FIFO
     * @param masterList    the work set of processes
     */
    public Scheduler_FIFO(List<process> masterList){
        super(masterList);
        this.ReadyProcesses = new FIFO_Q();
        this.populateReadyQ();
    }

    public void  handleNextEvent() {
        if (this.getNextEvent() == this.getNextUnblock()) {
            //P is the process that is unblocking
            process P = this.getNextUnblockResource().finishService();

            if(this.getActiveProcess() != null){
                //if there is a running process, increment it's CPUTime
                //Also increment CPU ActiveTime
                this.ActiveProcess.updateCPU (this.getNextEvent()- this.getTime());
                this.updateActiveTime(this.getNextEvent() - this.getTime());

                //add the next process into the ReadyQ and increment CurrentIndex
                this.ReadyProcesses.add(P);
                this.updateCurrentIndex();
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

            //update global time
            this.updateTime();

            //update the next unblock event
            this.update_NextUnblock_and_Resource();

            //update what the next event will be
            this.updateNextEvent();
        }
        else if (this.getNextEvent() == this.getNextArrival()) {

            //P is the process arriving from the MasterList
            process P = this.getMasterList().get(this.getCurrentIndex());

            if(this.getActiveProcess() != null){
                //if there is a running process, increment it's CPUTime
                //Also increment CPU ActiveTime
                this.ActiveProcess.updateCPU (this.getNextEvent()- this.getTime());
                this.updateActiveTime(this.getNextEvent() - this.getTime());

                //add the next process into the ReadyQ and increment CurrentIndex
                this.ReadyProcesses.add(P);
                this.updateCurrentIndex();
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

            //update global time
            this.updateTime();

            //update the next arrival event
            this.updateNextArrival();

            //update what the next event will be
            this.updateNextEvent();
        }
        else if (this.getNextEvent() == this.getNextSchedExit()) {
            //The active process is finshed running, update its CPUTime and add it to the FinishedQ.
            this.ActiveProcess.updateCPU (this.getNextEvent() - this.getTime());
            this.FinishedQ.add(this.getActiveProcess());
            //update Active time of CPU as well
            this.updateActiveTime(this.getNextEvent() - this.getTime());

            if (this.ReadyProcesses.isEmpty()) {
                //if there are no processes ready to run, begin idol time
              this.updateStartIdolTime(this.getNextEvent());
              //there is no active process now
              this.ActiveProcess = null;

              //update NextBlock and NextSchedExit because the ActiveProcess has changed
              this.updateNextBlock();
              this.updateNextSchedExit();
            }
            else {
                //update ActiveProcess to be the next ready process
                this.updateActiveProcess(this.ReadyProcesses.poll());

                //Update NextBlock and NextSchedExit because the ActiveProcess has changed
                this.updateNextBlock();
                this.updateNextSchedExit();
            }
            //update global time
            this.updateTime();

            //update what the next event will be
            this.updateNextEvent ();
        }
        //the event was the process blocking
        else {
            //update the active processes CPUTime
            this.ActiveProcess.updateCPU (this.getNextEvent() - this.getTime());

            //Check to see what resource the process is blocking on and send it to that resource
            if (this.getActiveProcess().getNextBlockResource() == "A") {
                TheResources[0].arrivingProcess(this.ActiveProcess, this.getTime() + this.getNextEvent());
            }
            else if (this.getActiveProcess().getNextBlockResource() == "B") {
                TheResources[1].arrivingProcess(this.ActiveProcess, this.getTime() + this.getNextEvent());
            }
            else {
                TheResources[2].arrivingProcess(this.ActiveProcess, this.getTime() + this.getNextEvent());
            }

            if (this.ReadyProcesses.isEmpty()) {
                //if there are no processes ready to run, begin idol time
                this.updateStartIdolTime(this.getNextEvent());
                //there is no active process now
                this.ActiveProcess = null;

                //update NextBlock and NextSchedExit because the ActiveProcess has changed
                this.updateNextBlock();
                this.updateNextSchedExit();
            }
            else {
                //update ActiveProcess to be the next ready process
                this.updateActiveProcess(this.ReadyProcesses.poll());

                //Update NextBlock and NextSchedExit because the ActiveProcess has changed
                this.updateNextBlock();
                this.updateNextSchedExit();
            }

            //update global time
            this.updateTime();

            //update what the next event will be
            this.updateNextEvent ();
        }



    }






}
