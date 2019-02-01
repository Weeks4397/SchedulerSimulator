package Resources;
import Processes.process;

import java.util.LinkedList;
import java.util.Queue;


/**ResourceA is a resource of type A.
 * It is an exclusive resource
 */
public abstract class ExclusiveResource extends Resource {

    /**
     * BlockedProcessQ can either be an empty queue or a queue of processes.
     * These processes are in the blocked state.
     * BlockedProcessQ is used for exclusive resources that only serve one process at a time.
     * Processes waiting to be served by the resource wait in the BlockedProcessQ
     */
    public Queue<process> BlockedProcessQ;

    /**
     * Constructor for resource of type A
     */
    public ExclusiveResource(){
        super();
        this.BlockedProcessQ = new LinkedList<process>();
    }

    /**
     * Getter for BlockedProcessQ
     */
    public Queue<process> getBlockedProcessQ() {
        return this.BlockedProcessQ;
    }


    /**arrivingProcess for exclusive resource
     *
     * @param theProcess    Process     the process that has arrived at the resource for service
     * @param time     int  The global time at which the process has arrived to the resource
     */
    public void arrivingProcess (process theProcess, int time) {
        //Keep track of the global time at which the process has arrived so we can tell how long it waited for service
        theProcess.updateGlobalBlockInstant(time);
        if (this.BlockedProcessQ.isEmpty() && this.ServingProcess == null) {
            //if no processes waiting or currently being served, go right into service.
            this.ServingProcess = theProcess;
            theProcess.ServiceStartTime = time;
            //update the idle time because the resource was idle until this process arrived.
            this.updateIdleTime (time - this.StartIdleTime);
            this.updateNextUnblockTime(time + theProcess.getNextBlockTime());
        }
        else {
            this.BlockedProcessQ.add(theProcess);		// insert at end of queue to wait for service
            this.Count++;
        }
    }

    /**finishService for an exclusive resource
     *
     * @return Process  the process that has finished using the resource
     */
    public process finishService() {
        //get the time the process finished and the actual process
        int oldTime = this.getNextUnblockTime();
        process oldProcess = this.getServingProcess();
        this.numOfBlocks += 1;
        this.totalBlockTime += oldProcess.getNextBlockTime();

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
        oldProcess.BlockWaitTime += oldProcess.getServiceStartTime() - oldProcess.getStartBlockWaitTime();

        //update the block record of the finishing process to move to the next block if there is one
        //or have process not block again if there is not one.
        oldProcess.GotoNextBlock();

        return oldProcess;

    }

}
