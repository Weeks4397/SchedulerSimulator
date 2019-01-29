package Resources;

import Processes.Block;
import Processes.process;
import ReadyQueue.Comparators;
import ReadyQueue.MinHeap;

import java.util.List;
import java.util.TreeMap;

/**
 * Resource B is a Resource class that is an inclusive resource. This means that it can
 * serve multiple processes at the same time.
 */

public class ResourceB extends Resource {

    /**
     * BlockedProcessHeap can either be an empty MinHeap or a MinHeap of processes.
     * These processes are in the blocked state.
     * BlockedProcessHeap is used for inclusive resources that serve all blocked processes at once
     */
    public MinHeap<UnblockTimePair> BlockedList = new MinHeap(50, Comparators.By_UBT);



    /**arrivingProcess for inclusive resource
     *
     * @param theProcess    Process     the process that has arrived at the resource for service
     * @param time     int  The global time at which the process has arrived to the resource
     */
    public void arrivingProcess (process theProcess, int time) {
        //UnblockTime is the gobal time this process is scheduled to unblock at.
        int  UnblockTime = theProcess.getNextBlockTime() + time;

        //Pair the Process with its unblock time and add to the BlockedList
        this.BlockedList.add(new UnblockTimePair(theProcess, UnblockTime));
        theProcess.ServiceStartTime = time;

        //Check to see if resource has been idle
        if (this.BlockedList.isEmpty() && this.ServingProcess == null) {

            //This is the only process that will be in service
            this.ServingProcess = theProcess;

            //update the idle time because the resource was idle until this process arrived.
            this.updateIdleTime (time - this.StartIdleTime);
            //This is the only process being served so it must have the Next Unblock Time
            this.updateNextUnblockTime(UnblockTime);
        }
        else {
            //If the process is going to unblock before other processes being served, update varibles
            if(this.BlockedList.peek().getP().getStringID() == theProcess.StringID){
                this.NextUnblockTime = UnblockTime;
                this.ServingProcess = theProcess;
            }
        }
        this.Count++;
    }

    /**finisService for an inclusive resource
     *
     * @return Process  the process that has finished using the resource
     */
    public process finishService() {
        //get the time the process finished and the actual process
        int oldTime = this.getNextUnblockTime();
        process oldProcess = this.BlockedList.poll().getP();

        //update Active time by the amount of time the process was served
        this.updateActiveTime(oldProcess.getNextBlockTime());

        //determine whether the BlockedProcessQ has more processes waiting to use the resource or not
        if (!this.BlockedProcessQ.isEmpty()) {
            //If there are processes waiting, get the next process and update variables
            process theProcess = this.BlockedProcessQ.remove();
            this.Count--;
            this.updateNextUnblockTime(oldTime + theProcess.getNextBlockTime());
            this.ServingProcess = theProcess;
            theProcess.ServiceStartTime = oldTime;  //the time at which the last process finished is the ServiceStartTime for this process
            this.numOfBlocks += 1;
            this.totalBlockTime += theProcess.getNextBlockTime();
        }
        else {
            //if there are no processes waiting set NextUnblockTime to Maxval so event does not occur in scheduler
            this.updateNextUnblockTime(Integer.MAX_VALUE);
            this.ServingProcess = null;
            //keep track of when idle time begins
            this.StartIdleTime = oldTime;
        }

        //increment finishing process's variables to keep track of performance
        oldProcess.BlockServiceTime += oldTime - oldProcess.getServiceStartTime();
        oldProcess.BlockWaitTime += oldProcess.getServiceStartTime() - oldProcess.getGlobalBlockInstant();

        //update the block record of the finishing process to move to the next block if there is one
        //or have process not block again if there is not one.
        if (!oldProcess.getBlockRecord().isEmpty()) {
            Block nextBlock = oldProcess.BlockRecord.poll();
            oldProcess.NextBlockInstant = nextBlock.getBI();
            oldProcess.NextBlockResource = nextBlock.getR();
            oldProcess.NextBlockTime = nextBlock.getBT();
        }
        else {
            oldProcess.NextBlockInstant = Integer.MAX_VALUE;
            oldProcess.NextBlockResource = null;
            oldProcess.NextBlockTime = 0;
        }
        return oldProcess;

    }




}