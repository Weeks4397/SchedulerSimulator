package Resources;

import java.util.LinkedList;
import java.util.Queue;
import Processes.process;

/**
 * A Resource is a string for type, a collection of numbers, and either a queue or MinHeap of processes
 * depending on whether the resource is exclusive or not.
 * A Resource can be in 1 of 2 states:
 *  Idle state: The resource is not currently serving any processes.
 *  Active state:  The resource is currently serving at least 1 process.
 * A Resource can be of 3 different types: typeA, typeB, or typeC.
 */
public abstract class  Resource {
    /**
     *  Data members for a resource
     */

    /**
     * Type is the type of resource which can be either a, b, or c
     */
    public String Type;

    /**
     * NextUnblockTime is the global time at which the process currently being served will unblock.
     * When no processes are being served, the time is set to Integer.Max_Value.
     * This value serves as an event in the scheduler for the a process unblocking.
     */
    public int NextUnblockTime;

    /**
     * StartIdleTime is the a marker for the global time at which the resource is no longer serving processes.
     * This marker is used to update IdleTime.
     */
    public int StartIdleTime;

    /**
     *ActiveTime is the the total time a resource has spent in the Active state.
     */
    public int ActiveTime;

    /**
     * IdleTime is the total time a resource has spent in the Idle state.
     */
    public int IdleTime;

    /**
     * BlockedProcessQ can either be empty or a queue of processes.
     * These processes are in the blocked state.
     * BlockedProcessQ is used for exclusive resources that only serve one process at a time.
     */
    public Queue<process> BlockedProcessQ;

    /**
     * BlockedProcessHeap can either be empty or a MinHeap of processes.
     * These processes are in the blocked state.
     * BlockedProcessHeap is used for inclusive resources that serve all blocked processes at once
     */
   // public Minheap<Process> BlockedProcessHeap

    /**
     * ServingProcess is the process currently being served by the resource.
     * For inclusive resources, ServingProcess is the process that is going to finish first.
     */
    public process ServingProcess;

    /**
     * Count is the amount of processes waiting to be served by a resource
     */
    public int Count;

    /**
     *  Exclusive is a boolean that is true if the resource is exclusive and false if not.
     */
    public boolean Exclusive;

    /**
     * numOFBlocks is the total number of processes the resource has served.
     * This value will be used for troubleshooting the resource class.
     */
    public int numOfBlocks;

    /**
     * totalBlockTime is the total amount of block time the resource has served.
     * This value will be used for troubleshooting the resource class.
     */
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
