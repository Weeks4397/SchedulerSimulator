package Resources;

import java.util.LinkedList;
import java.util.Queue;
import Processes.process;
//TODO comment
public abstract class  Resource {
    /**
     *  Data members for a resource
     */

    public String type;
    public int nextUnblockTime;
    public int startIdleTime;
    public int activeTime;
    public int idleTime;
    public Queue<process> BlockedProcessQ;
   // public Minheap<Process> BlockedProcessHeap
    public process servingProcess;
    public int count;
    public boolean exclusive;
    public int numOfBlocks;
    public int totalBlockTime;

    /**
     * Setters for resource object
     */

    /**
     * sets type of resources
     */
    public abstract void setType();

    /**
     * sets the next unblock time
     */
    public void setNextUnblockTime() {
        this.nextUnblockTime = Integer.MAX_VALUE;
    }

    /**
     * sets the initial startIdleTime to MAXINT
     */
    public void setStartIdleTime() {
        this.startIdleTime = Integer.MAX_VALUE;
    }

    /**
     * Initializes the active time for the resource to 0
     */
    public void setActiveTime() {
        this.activeTime = 0;
    }

    /**
     * Initializes the idle time for the resource to 0
     */
    public void setIdleTime() {
        this.idleTime = 0;
    }

    /**
     * Initializes the Queue for resource a and c
     */
    public void setBlockedProcessQ(){
        this.BlockedProcessQ = new LinkedList<process>();
    }

    //Initializes the Minheap for resource b
    //public  void setMinHeap...(){};

    /**
     * sets the process being served to null
     */
    public void setServingProcess() {
        this.servingProcess = null;
    }

    /**
     * sets the count for the blocked processes list to 0
     */
    public void setCount() {
        this.count = 0;
    }

    /**
     * is this resource exclusive?
     */
    public abstract void setExclusive();

    /**
     * initializes number of blocks to 0
     */
    public void setNumOfBlocks() {
        this.numOfBlocks = 0;
    }

    /**
     * initializes total block time to 0
     */
    public void setTotalBlockTime() {
        this.totalBlockTime = 0;
    }


    /**
     * getters for resource object
     * @return
     */

    public String getType() {
        return this.type;
    }

    public int getNextUnblockTime() {
        return this.nextUnblockTime;
    }

    public int getStartIdleTime() {
        return this.startIdleTime;
    }

    public int getActiveTime() {
        return this.activeTime;
    }

    public int getIdleTime() {
        return this.idleTime;
    }

    public Queue<process> getBlockedProcessQ() {
        return this.BlockedProcessQ;
    }

    //public minHeap<Process> getBlockedProcessminheap() {
   //     return this.minHeap<Process>;
    //}

    public process getServingProcess() {
        return this.servingProcess;
    }

    public int getCount() {
        return this.count;
    }

    public boolean getExclusive() {
        return this.exclusive;
    }

    public int getNumOfBlocks() {
        return this.numOfBlocks;
    }

    public int getTotalBlockTime() {
        return this.totalBlockTime;
    }

    /**
     * additional methods for updating resource object
     */


    public void updateNextUnblockTime(process P) {
        this.nextUnblockTime = P.getNextBlockTime();
    }

    public abstract void arrivingProcess(process theProcess, int time);

    public void updateIdleTime(int T) {
        this.idleTime += T;
    }

    public void updateActiveTime(int PST) {
        this.activeTime += PST;
    }


    public abstract process finishService ();

}
