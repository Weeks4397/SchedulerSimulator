package Schedulers;

import Processes.process;
import ReadyQueue.ReadyQ;
import Resources.*;

import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;



public abstract class Scheduler implements SchedulerInterface{
    /**
     * data members for a scheduler
     */

    /**
     * Time is the global time of the simulation
     */
    public int Time;

    /**
     * ReadyProcesses is an empty ReadyQ or a ReadyQ of processes
     */
    public ReadyQ ReadyProcesses;

    /**
     * ActiveProcess is the process currently receiving CPU time
     * if there is no active process, set to null
     */
    public process ActiveProcess;

    /**
     * TheResources is an array of the resources that processes can block on
     */
    public Resource[] TheResources;

    /**
     * NextUnblockResource is a resource currently serving a process that is going to
     * finish service first compared to the other resources.  This resource has the lowest NextUnblockTime
     */
    public Resource NextUnblockResource;

    /**These are the possible events that can occur to a process in a scheduler causing the process to change states
     * NextArrival: The next time at which a process in the Master List state will arrive to the ReadyQ entering the ready state
     * NextScedExit: The time the currently running process will will enter the Finished state
     * NextUnblock: The next time at which a process finishes using a resource and must be placed back into ReadyQ
     * NextBlock: The time at which the running process will block
     */
    public int NextArrival, NextSchedExit, NextUnblock, NextBlock;

    /**
     * NextEvent is the lowest of the possible event times
     */
    public int NextEvent;

    /**These are the states of the CPU
     * ActiveTime:  The total time a CPU has been active serving a process throughout the simulation
     * IdleTime:    The total time a CPU has been idle, not serving a process throughout the simulation
     */
    public int ActiveTime, IdleTime;

    /**
     * StartIdleTime is a marker of global time when the CPU goes into the idle state so that
     * we can keep track of total IdleTime
     */
    public int StartIdleTime;

    /**
     * MasterList is the arraylist of processes generated up front in the WorksetGenerator for the simulation
     */
    public List<process> MasterList;

    /**
     * CurrentIndex is the index of the next process to be released into the ReadyQ
     */
    public int CurrentIndex;

    /**
     *FinishedQueue is a queue that processes that finish running with the CPU are added to for
     * reporting purposes after the simulation.
     */
     public Queue<process> FinishedQ;

     //TODO figure out what context switches are
    /**
     * The scheduler has costs for context switch that increment overhead
     * SCostMin:  occurs when readyQ is empty:
     *                  - process finishes or times out
     * SCostFull: occurs when readyQ is not empty:
     *                  -process blocks or times out
     *                  -if algorithm is preemptive and process arrives or unblocks
     * SCostComp: occurs when readyQ is not empty
     */


    /**
     * super constructor for scheduler
     * @param masterList    List<Process>   the work set of processes
     */

    public Scheduler(List<process> masterList){
        this.Time = 0;
        this.ActiveProcess = null;

        //initialize the array of resources that processes can block on
        setTheResources();
        this.NextUnblockResource = null;

        //all possible events initialize to MAXVAL
        this.NextArrival = Integer.MAX_VALUE;
        this.NextSchedExit = Integer.MAX_VALUE;
        this.NextUnblock = Integer.MAX_VALUE;
        this.NextBlock = Integer.MAX_VALUE;

        this.NextEvent = 0;
        this.ActiveTime = 0;
        this.IdleTime = 0;
        this.StartIdleTime = Integer.MAX_VALUE;
        this.MasterList = masterList;
        this.CurrentIndex = 0;
        this.FinishedQ = new LinkedList<process>();

    }

    /**
     * Initializes an array of the Resources a process can block on
     */
    public void setTheResources(){
        ResourceA A = new ResourceA();
        ResourceB B = new ResourceB();
        ResourceC C = new ResourceC();

        this.TheResources = new Resource[] {A, B, C};
    }

    /**
     *Getters for Scheduler
     */
    public int getTime() {
        return this.Time;
    }

    public ReadyQ getReadyProcesses() {
        return this.ReadyProcesses;
    }

    public process getActiveProcess() {
        return this.ActiveProcess;
    }

    public Resource[] getTheResources() {
        return this.TheResources;
    }

    public Resource getNextUnblockResource() {
        return this.NextUnblockResource;
    }

    public int getNextArrival() {
        return this.NextArrival;
    }

    public int getNextSchedExit() {
        return this.NextSchedExit;
    }

    public int getNextUnblock() {
        return this.NextUnblock;
    }

    public int getNextBlock(){
        return this.NextBlock;
    }

    public int getNextEvent() {
        return this.NextEvent;
    }

    public int getActiveTime() {
        return this.ActiveTime;
    }

    public int getIdleTime() {
        return this.IdleTime;
    }

    public int getStartIdleTime() {
        return this.StartIdleTime;
    }

    public List<process> getMasterList() {
        return this.MasterList;
    }

    public int getCurrentIndex() {
        return this.CurrentIndex;
    }

    public Queue<process> getFinishedQ(){
        return this.FinishedQ;
    }



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
    public abstract void arriveReadyQ(process P);

    /**
     * ExitCPU is a helper for handling a process exiting from use of the CPU
     * This method depends on whether the algorithm has time out or not
     */
    public abstract void ExitCPU();



    /**
     * Methods to update and handle events
     */

    /**updateNextEvent mutates NextEvent to be the min of the possible events
     *this method depends on whether the algorithm as time out or not
     */
    public abstract void updateNextEvent ();

    /**
     * handleNextEvent determines the necessary course of action after the next event has been determined.
     * This is when Processes will change state and report variables will be incremented
     * Methods interacting with the MasterList, ReadyQ, FinishedQ, and Resources occur here
     *
     * This method depends on whether the algorithm has time out or not
     */
    public abstract void handleNextEvent();



    /**
     * methods to handle each individual event
     */

    /**
     * handleNextUnblock handles the event of a process unblocking from a resource
     */
    public void handleNextUnblock(){
        //P is the process that is unblocking
        process P = this.getNextUnblockResource().finishService();

        //P has finished service and now must arrive to readyQ
        this.arriveReadyQ(P);

        //update the next unblock event
        this.update_NextUnblock_and_Resource();
    }

    /**
     * handleNextArrival handles the event of a process arriving from MasterList
     */
    public void handleNextArrival() {
        //P is the process arriving from the MasterList
        process P = this.getMasterList().get(this.getCurrentIndex());

        //Handle P arriving to the readyQ
        this.arriveReadyQ(P);

        //update the current masterList index
        this.updateCurrentIndex();

        //update the next arrival event
        this.updateNextArrival();
    }

    /**
     * handleNextSchedExit handles the event of a process finishing its run time with CPU
     */
    public void handleNextSchedExit(){
        //The active process is finshed running, update its CPUTime, finished time and add it to the FinishedQ.
        this.ActiveProcess.updateCPU (this.getNextEvent() - this.getTime());
        this.ActiveProcess.updateFinishTime(this.getNextEvent());
        this.FinishedQ.add(this.getActiveProcess());

        //update Active time of CPU as well
        this.updateActiveTime(this.getNextEvent() - this.getTime());

        //The active process has exited CPU.
        //Bring in next process to run if there is one.
        this.ExitCPU();
    }

    /**
     * handleNextBlock handles the event of a process blocking and exiting CPU
     */
    public void handleNextBlock(){
        //update the active processes CPUTime
        this.ActiveProcess.updateCPU (this.getNextEvent() - this.getTime());

        //update Active time of CPU as well
        this.updateActiveTime(this.getNextEvent() - this.getTime());

        //Check to see what resource the process is blocking on and send it to that resource
        if (this.getActiveProcess().getNextBlockResource() == "A") {
            TheResources[0].arrivingProcess(this.ActiveProcess, this.getNextEvent());
        }
        else if (this.getActiveProcess().getNextBlockResource() == "B") {
            TheResources[1].arrivingProcess(this.ActiveProcess, this.getNextEvent());
        }
        else {
            TheResources[2].arrivingProcess(this.ActiveProcess, this.getNextEvent());
        }

        //Update NextUnblock because a process has blocked
        this.update_NextUnblock_and_Resource();

        //The active process has exited CPU.
        //Bring in next process to run if there is one.
        this.ExitCPU();
    }




    /**
     * Methods to update individual event
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
    public void update_NextUnblock_and_Resource() {
        int min = TheResources[0].getNextUnblockTime();
        Resource R = TheResources[0];
        for (Resource x : TheResources) {
            if (x.getNextUnblockTime() < min) {
                min = x.getNextUnblockTime();
                R = x;
            }
        }
        if (min < Integer.MAX_VALUE){
            this.NextUnblock = min;
            this.NextUnblockResource = R;
        }
        else {
            this.NextUnblock = Integer.MAX_VALUE;
            this.NextUnblockResource = null;
        }
    }

    /**
     * updateNextArrival  mutates NextArrival to be the the ArrivalTime of the process that is next in the MasterList
     * if at the end of the list, then set to MAXVAL
     */
    public void updateNextArrival() {
        if (this.getCurrentIndex() < this.getMasterList().size()) {
            this.NextArrival = this.getMasterList().get(getCurrentIndex()).getArrivalTime();
        }
        else{
            this.NextArrival = Integer.MAX_VALUE;
        }
    }

    /**
     * updateNextSchedExit mutates NextSchedExit to be the global time at which the currently running process's
     * RunTime - CPUTime will equal 0
     *If there is no active Process than set NextSchedExit to MAXVAL
     */
    public void updateNextSchedExit() {
        if (this.getActiveProcess() == null){
            this.NextSchedExit = Integer.MAX_VALUE;
        }
        else{
            this.NextSchedExit= this.getNextEvent() + (this.ActiveProcess.getRunTime() - this.ActiveProcess.getCPUTime());
        }
    }

    /**
     * updateNextBlock  mutates NextBlock to be the global time at which the active process will block
     * If the process is not going to block or there is no active process, set to MAXVAL
     */
    public void updateNextBlock(){
        if(this.getActiveProcess() == null || this.getActiveProcess().getNextBlockResource() == null){
            this.NextBlock = Integer.MAX_VALUE;
        }
        else{
            process P = this.getActiveProcess();
            this.NextBlock = P.getNextBlockInstant() - P.getCPUTime() + this.getNextEvent();
        }

    }


    /**
     * updateActiveTime increments ActiveTime by the given int
     * @param t    int     the time the scheduler was active this cycle
     */
    public void updateActiveTime(int t) {
        this.ActiveTime += t;
    }

    /**
     * updateIdolTime   increments IdolTime by the given int
     * @param t    int  The time the scheduler was idol this cycle
     */
    public void updateIdolTime(int t) {
        this.IdleTime += t;
    }

    /**
     * updateStartIdleTime mutates StartIdol Time to the be the given int
     * @param t   int     The global time at which the scheduler became idol
     */
    public void updateStartIdolTime(int t) {
        this.StartIdleTime = t;
    }

    /**
     * updateCurrentIndex increments the CurrentIndex by 1
     */
    public void updateCurrentIndex() {
        this.CurrentIndex += 1;
    }

    /**
     * Mutates time to be equal to the time at which the event has occurred
     */
    public void updateTime() {
        this.Time = this.NextEvent;
    }

    /**
     * updateActiveProcess  Mutates ActiveProcess to be the given process
     * @param P   process      The given Process
     */
    public void updateActiveProcess(process P) {
        this.ActiveProcess = P;
    }

    /**
     * populateReadyQ  populates ReadyQ with the initial ready processes the simulation will start with
     */
    public  void populateReadyQ(){
        //while the next process in the MasterList has an arrival time of 0
        //add it to the ReadyQ because that is one of the initial processes
        while(this.getMasterList().get(this.getCurrentIndex()).getArrivalTime() == 0){
            this.ReadyProcesses.add(this.getMasterList().get(this.getCurrentIndex()));
            this.updateCurrentIndex();
        }
    }


}
