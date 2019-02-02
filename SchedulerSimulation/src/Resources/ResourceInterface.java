package Resources;

import Processes.process;

/**
 * A Resource is a:  string for type, a collection of numbers, and either a queue or MinHeap of processes
 * depending on whether the resource is exclusive or not.
 * A Resource can be in 1 of 2 states:
 *     1) Idle state: The resource is not currently serving any processes.
 *     2) Active state:  The resource is currently serving at least 1 process.
 * A Resource can be of 3 different types: typeA, typeB, or typeC.
 */
public interface ResourceInterface {

    /**
     * A process can arrive at a resource or finish using it.
     * These are the two methods to handle these occurances.
     */
    /**arrivingProcess handles the event of a process arriving to a resource for service
     * This method is handled differently depending on whether the resource is exclusive or not
     * @param theProcess    Process     the process that has arrived at the resource for service
     * @param time     int  The global time at which the process has arrived to the resource
     */
    void arrivingProcess(process theProcess, int time);

    /**finishService handles the event of a process finishing using the resource.
     * This method is called in the scheduler to insert the returned process back into the ready queue.
     * This method is different for exclusive and inclusive resources.
     *
     * @return  Process     The process that has finishing using the resource
     */
    process finishService ();

    /**
     * additional methods for updating resource object
     */

    /**updateNextUnblockTime mutates NextUnblockTime to be equal to the given time
     *
     * @param t    int  the given time
     */
    void updateNextUnblockTime(int t);

    /**updateIdleTime increments the IdleTime by the given int
     *
     * @param T int the amount of time the resource was idle
     */
    void updateIdleTime(int T);

    /**updateActiveTime increment the ActiveTime by the given time
     *
     * @param   T   The amount of time the resource was active
     */
    void updateActiveTime(int T);

    /**
     * toString formats important information about process into a string for debugging purposes
     */
    String toString();
}
