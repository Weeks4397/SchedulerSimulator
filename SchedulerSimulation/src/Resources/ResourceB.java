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
        if (this.BlockedList.isEmpty() && this.ServingProcess == null) {
            //Pair the Process with its unblock time and add to the BlockedList
            BlockedList.add(new UnblockTimePair(theProcess, UnblockTime));

            //This is the only process that will be in service
            this.ServingProcess = theProcess;
            theProcess.ServiceStartTime = time;
            //update the idle time because the resource was idle until this process arrived.
            this.updateIdleTime (time - this.StartIdleTime);
            //This is the only process being served so it must have the Next Unblock Time
            this.updateNextUnblockTime(time + theProcess.getNextBlockTime());
            this.Count++;
        }
        else {
            //Pair the Process with its unblock time and add to the BlockedList
            this.BlockedList.add(new UnblockTimePair(theProcess, UnblockTime));
            theProcess.ServiceStartTime = time;

            //If the process is going to unblock before other processes being served update times
            if(this.BlockedList.peak().get)
            this.Count++;
        }
    }




}