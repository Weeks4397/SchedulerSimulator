package Resources;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import Processes.process;

public abstract class  Resource implements ResourceInterface{
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
     * This value serves as an event in the scheduler for a process unblocking.
     */
    public int NextUnblockTime;

    /**
     * StartIdleTime is a marker for the global time at which the resource is no longer serving processes.
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
     * ServingProcess is the process currently being served by the resource.
     * For inclusive resources, ServingProcess is the process that is going to finish first.
     */
    public process ServingProcess;

    /**
     * Count is the amount of processes waiting to be served by a resource
     */
    public int Count;

    /**
     * MaxCount is the maximum count of processes that were in the Queue or Heap during the simulation
     */
    public int MaxCount;

    /**
     * StartWaitTime is a marker in global time so that we can keep track of WaitTime
     */
    public int StartWaitTime;

    /**
     * WaitTime is the total time that there were processes waiting to use a resource
     */
    public int WaitTime;

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
     * super constructor for resource
     */

    public Resource(){
        this.NextUnblockTime = Integer.MAX_VALUE;
        this.StartIdleTime = 0;
        this.ActiveTime = 0;
        this.IdleTime = 0;
        this.ServingProcess = null;
        this.Count = 0;
        this.MaxCount = 0;
        this.numOfBlocks = 0;
        this.totalBlockTime = 0;
        this.StartWaitTime = Integer.MAX_VALUE;
        this.WaitTime = 0;
    }

    /**
     * getters for resource object
     */

    public String getType() {
        return this.Type;
    }

    public int getNextUnblockTime() {
        return this.NextUnblockTime;
    }

    public int getStartIdleTime() {
        return this.StartIdleTime;
    }

    public int getActiveTime() {
        return this.ActiveTime;
    }

    public int getIdleTime() {
        return this.IdleTime;
    }

    public process getServingProcess() {
        return this.ServingProcess;
    }

    public int getCount() {
        return this.Count;
    }

    public int getMaxCount(){return this.MaxCount;}

    public int getNumOfBlocks() {
        return this.numOfBlocks;
    }

    public int getTotalBlockTime() {
        return this.totalBlockTime;
    }

    public int getStartWaitTime(){return this.StartWaitTime;}

    public int getWaitTime(){return this.WaitTime;}

    /**
     * A process can arrive at a resource or finish using it.
     * These are the two methods to handle these occurances.
     */
    /**arrivingProcess handles the event of a process arriving to a resource for service
     * This method is handled differently depending on whether the resource is exclusive or not
     * @param theProcess    Process     the process that has arrived at the resource for service
     * @param time     int  The global time at which the process has arrived to the resource
     */
    public abstract void arrivingProcess(process theProcess, int time);

    /**finishService handles the event of a process finishing using the resource.
     * This method is called in the scheduler to insert the returned process back into the ready queue.
     * This method is different for exclusive and inclusive resources.
     *
     * @return  Process     The process that has finishing using the resource
     */
    public abstract process finishService ();

    /**
     * additional methods for updating resource object
     */

    /**updateNextUnblockTime mutates NextUnblockTime to be equal to the given time
     *
     * @param t    int  the given time
     */
    public void updateNextUnblockTime(int t) {
        this.NextUnblockTime = t;
    }

    /**updateIdleTime increments the IdleTime by the given int
     *
     * @param T int the amount of time the resource was idle
     */
    public void updateIdleTime(int T) {
        this.IdleTime += T;
    }

    /**updateActiveTime increment the ActiveTime by the given time
     *
     * @param   T   The amount of time the resource was active
     */
    public void updateActiveTime(int T) {
        this.ActiveTime += T;
    }

    /**
     * toString formats important information about resource into a string for debugging purposes
     */
    public String toString(){
        String theResource = String.format("Type: %s\nActive time: %d\nIdle time: %d\nNumber of blocks served: %d" +
                        "\nAmount of Block Time Served: %d\nMax Count: %d\nWait time: %d",
                this.getType(), this.getActiveTime(), this.getIdleTime(),
                this.getNumOfBlocks(), this.getTotalBlockTime(), this.getMaxCount(), this.getWaitTime());
        return theResource;
    }



}
