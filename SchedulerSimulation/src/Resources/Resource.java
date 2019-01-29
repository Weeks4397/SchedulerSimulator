package Resources;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import Processes.process;
//TODO: implement abstract class for exclusive resources A and C
//TODO: make a tester for these resources
/**
 * A Resource is a:  string for type, a collection of numbers, and either a queue or MinHeap of processes
 * depending on whether the resource is exclusive or not.
 * A Resource can be in 1 of 2 states:
 *     1) Idle state: The resource is not currently serving any processes.
 *     2) Active state:  The resource is currently serving at least 1 process.
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
     * super constructor for resource
     */

    public Resource(){
        this.NextUnblockTime = Integer.MAX_VALUE;
        this.StartIdleTime = Integer.MAX_VALUE;
        this.ActiveTime = 0;
        this.IdleTime = 0;
        this.ServingProcess = null;
        this.Count = 0;
        this.numOfBlocks = 0;
        this.totalBlockTime = 0;
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

    public boolean getExclusive() {
        return this.Exclusive;
    }

    public int getNumOfBlocks() {
        return this.numOfBlocks;
    }

    public int getTotalBlockTime() {
        return this.totalBlockTime;
    }

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



}
