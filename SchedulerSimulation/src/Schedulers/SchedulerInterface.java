package Schedulers;

import Processes.process;
import ReadyQueue.ReadyQ;
import Resources.Resource;

import java.util.List;
import java.util.Queue;

/**
 * a Scheduler is a collection of numbers, a ReadyQ, a running process, a list of resources,
 * the next unblocking resource, and a master list of processes.
 * The scheduler handles the changes of state of the process objects according to an algorithm.
 * These states are: MasterList, Ready, Running, Blocked, and Finished
 * The scheduler uses the MasterList generated by the WorkSetGenerator as its work set of processes.
 * A scheduler is constructed with the appropriate ReadyQ for the algorithm.
 * The scheduler tracks active and idle time of the CPU for reporting performance.
 * Scheduler keeps track of Resources to handle unblocking event
 * Processes entering finished state are added to FinishedQueue for reporting after simulation has completed.
 *
 * There is a subclass of scheduler for each algorithm.
 *
 * Time for the scheduler simulation is represented in hundredths of seconds.
 */
public interface SchedulerInterface {
    /**
     * The scheduler needs to handle the arrival and Exiting of Processes
     * Arrival: Processes can arrive to the ReadyQ or directly to CPU after unblocking or arriving from MasterList
     * Exiting and arriving: Processes can exit from using CPU, The next process will now have to get access
     *                      to the CPU.
     */

    /**
     * arriveReadyQ is a helper for handling a process arriving to the ReadyQ
     * This method depends on whether the algorithm has time out and/or preemption.
     * @param P process     the process that is arriving
     */
    void arriveReadyQ(process P);

    /**
     * ExitCPU is a helper for handling a process exiting from use of the CPU
     * This method depends on whether the algorithm has time out or not
     */
    void ExitCPU();



    /**
     * Methods to update and handle events
     */

    /**updateNextEvent mutates NextEvent to be the min of the possible events
     *this method depends on whether the algorithm has time out or not
     */
    void updateNextEvent ();

    /**
     * handleNextEvent determines the necessary course of action after the next event has been determined.
     * This is when Processes will change state and report variables will be incremented
     * Methods interacting with the MasterList, ReadyQ, FinishedQ, and Resources occur here
     *
     * This method depends on whether the algorithm has time out or not
     */
    void handleNextEvent();



    /**
     * methods to handle each individual event
     */

    /**
     * handleNextUnblock handles the event of a process unblocking from a resource
     */
    void handleNextUnblock();

    /**
     * handleNextArrival handles the event of a process arriving from MasterList
     */
    void handleNextArrival();

    /**
     * handleNextSchedExit handles the event of a process finishing its run time with CPU
     */
    void handleNextSchedExit();

    /**
     * handleNextBlock handles the event of a process blocking and exiting CPU
     */
    void handleNextBlock();

    /**
     * Methods to update individual events
     */

    /**
     * update_NextUnblock_and_Resource  determines the min NextUnblockTime of all the resources and which resource has this time
     * Mutates NextUnblock to be this time
     * Mutates NextUnblockResource to be the resource associated with the time
     *
     * If there are no unblocks occurring,
     * NextUnblock is set to Max_Val
     * NextUnblockResource is set to null
     */
    void update_NextUnblock_and_Resource();

    /**
     * updateNextArrival  mutates NextArrival to be the the ArrivalTime of the process that is next in the MasterList.
     * If at the end of the list, then set to MAXVAL
     */
    void updateNextArrival();

    /**
     * updateNextSchedExit mutates NextSchedExit to be the global time at which the currently running process's
     * RunTime - CPUTime will equal 0
     *If there is no active Process than set NextSchedExit to MAXVAL
     */
    void updateNextSchedExit();

    /**
     * updateNextBlock  mutates NextBlock to be the global time at which the active process will block
     * If the process is not going to block or there is no active process, set to MAXVAL
     */
    void updateNextBlock();



    /**
     * populateReadyQ  populates ReadyQ with the initial ready processes the simulation will start with
     */
    void populateReadyQ();
    /**
     * runAlgorithm updates the fields to a starting position
     * and runs the scheduling algorithm
     */
    void runAlgorithm();

    /**
     * toString formats the scheduler into a printable string for reporting purposes
     */
    String toString();

    /**
     * updateActiveTime increments ActiveTime by the given int
     * @param t    int     the time the scheduler was active this cycle
     */
    void updateActiveTime(int t);

    /**
     * updateIdolTime   increments IdolTime by the given int
     * @param t    int  The time the scheduler was idol this cycle
     */
    void updateIdolTime(int t);

    /**
     * updateStartIdleTime mutates StartIdol Time to the be the given int
     * @param t   int     The global time at which the scheduler became idol
     */
    void updateStartIdolTime(int t);

    /**
     * updateCurrentIndex increments the CurrentIndex by 1
     */
    void updateCurrentIndex();

    /**
     * Mutates time to be equal to the time at which the event has occurred
     */
    void updateTime();

    /**
     * updateActiveProcess  Mutates ActiveProcess to be the given process
     * @param P   process      The given Process
     */
    void updateActiveProcess(process P);

    /**
     *updateNextSCost mutates the NextSCost to be the given time
     * @param time  int  the given time
     */
    void updateNextSCost(int time);

    /**
     * updateOverhead increments overhead by the Scost for the event
     */
    void updateOverhead();


    /**
     * getters for scheduler
     */
    /**
     *Getters for Scheduler
     */
    int getTimeOut_Count();
    int getPreempt_Count();
    int getTime();
    ReadyQ getReadyProcesses();
    Resource[] getTheResources();
    Resource getNextUnblockResource();
    int getNextArrival();
    int getNextSchedExit();
    int getNextUnblock();
    int getNextBlock();
    int getNextEvent();
    int getActiveTime();
    int getIdleTime();
    int getStartIdleTime();
    List<process> getMasterList();
    int getCurrentIndex();
    Queue<process> getFinishedQ();
    String getType();
    int getNextSCost();
    int getOverhead();

}
