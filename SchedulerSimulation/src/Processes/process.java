package Processes;
import java.util.*;

/**
 * A Process contains a set of numbers, a list of blocks, and a string for the next resource.
 * A process can be of 4 different types, TypeI, TypeII, TypeIII, or Type IV.
 *
 */

public abstract class Process {

    //data members of process

    //StringID is the id for the process "P%d%d%d"
    private String StringID;

    //Type is the type of process either 1, 2, 3 or 4
    private int Type;

    //RunTime is the is the amount of time a process needs with cpu before completion
    private int RunTime;

    //NextBlock Instant is the time at which the next block will occur
    //If there is no next block, set to MAXINT
    private int NextBlockInstant;

    //NextBlockResource is the the type of resource that is needed for the next block
    //If there is no next block, set to null
    private String NextBlockResource;

    //NextBlockTime is the length of the next block
    //if there is no next block, set to 0
    private int NextBlockTime;

    //BlockRecords is a list of all of the blocks for a process or can be null
    //A block consists of a block instant, a type of resoruce, and a block length
    private List<Block> BlockRecord;

    //CurrentListIndex is the index of the next block in the BlockRecord or null
    private int CurrentListIndex;

    //MaxListIndex is the index of the last block in the BlockRecord or null
    private int MaxListIndex;

    //ArricalTime is the time at which a process initally arrives to the ready queue from the master list of processes
    private int ArrivalTime;

    //CPUTime is the total time the process has had with the cpu since the last event
    //CPUTime is initially 0
    private int CPUTime;

    //NextReadyTime is initially equal to ArrivalTime
    //NextReadyTime is adjusted when a process arrives back into the ready queue after a block, time-slice, or preemption
    private int NextReadyTime;

    //LastEventTime is the global time at which the last event occured effecting this process
    private int LastEventTime;

    //FinishTime is the global time at which this processes CPUTime becomes equal to the RunTime
    private int FinishTime;

    //BlockServiceTime is the amount of time a process spent in the blocked state
    private int BlockServiceTime;

    //ServiceStartTime is the time at which a process begins to use the resource
    private int ServiceStartTime;

    //Constructor for a process
    public Process() {

        this.StringID = "P-1";
        this.CPUTime = 0;
        this.LastEventTime = 0;
        this.FinishTime = Generators.MAXINT;
        this.BlockServiceTime = 0;
        this.ServiceStartTime = Generators.MAXINT;
        this.Type = -1;
        this.RunTime = -1;
        this.NextBlockInstant = -1;
        this.NextBlockResource = null;
        this.NextBlockTime = -1;
        this.BlockRecord = new ArrayList<Block>();
        this.CurrentListIndex = -1;
        this.MaxListIndex = -1;
        this.ArrivalTime = -1;
        this.NextReadyTime = -1;

    }

    //setters for processes
     /**Sets the StringID
      * If string id is over max limit set to "-1"
      * @param i  keeps track of current processID index
      * @param iMax  the maximum amount of process IDs available
      */
    public void setStringID(int i, int iMax) {
        String id;
        if (i<10) {
            id = String.format("P00%d", i);
        }
        else if (i < 100) {
            id = String.format("P0%d", i);
        }
        else if (i <= iMax){
            id = String.format("P%d", i);
        }
        else {
            id = "-1";
        }
        this.StringID = id;
    }

    /**Generate the BlockRecord
     * sets the block record, the current list index to 0, and the max index
     * to the length of the block record - 1;
     * if there are no blocks the block record, current index, and max index are set to null
     */
    public abstract void genBlockRecord();


    /**sets init arrival time and nextReady time
     * @param t  time at which process scheduled to arrive into ready queue from master list
     */
    Public void setArrivalandReadyTime(int t){
        this.ArrivalTime = t;
        this.NextReadyTime = t;
    }

    //Getters for Processes

    public String getStringID() {
        return this.StringID;
    }

    public int getType() {
        return this.Type;
    }

    public int getRunTime() {
        return this.RunTime;
    }

    public int getNextBlockInstant() {
        return this.NextBlockInstant;
    }

    public String getNextBlockResource() {
        return this.NextBlockResource;
    }

    public int getNextBlockTime() {
        return this.NextBlockTime;
    }

    public List<Block> getBlockRecord() {
        return this.BlockRecord;
    }

    public int getCurrentListIndex() {
        return this.CurrentListIndex;
    }

    public int getMaxListIndex() {
        return this.MaxListIndex;
    }

    public int getArrivalTime() {
        return this.ArrivalTime;
    }

    public int getCPUTime() {
        return this.CPUTime;
    }

    public int getNextReadyTime() {
        return this.NextReadyTime;
    }

    public int getLastEventTime() {
        return this.LastEventTime;
    }

    public int getFinishTime() {
        return this.FinishTime;
    }

    public int getBlockServiceTime() {
        return this.BlockServiceTime;
    }

    public int getServiceStartTime() {
        return this.ServiceStartTime;
    }

    //Mutators for Processes


    //public void updateBlocked (int time) { blockedTime += time – lastEventTime; }
    //public void updateReady (int time) { … }
    public void updateCPU (int time) {
        this.CPUTime += time; }


    //Comparator interface for process objects
    public static final Comparator<Process> By_SJF = new BySJF();
    public static final Comparator<Process> By_SRT = new BySRT();
    public static final Comparator<Process> By_LWC = new ByLWC();

    //compare based on shortest job
    private static class BySJF implements Comparator<Process> {
        public int compare(Process P1, Process P2) {
            int P1jobTime = P1.totalRunTime;
            int P2jobTime = P2.totalRunTime;
            if (P1jobTime < P2jobTime) return 1;
            if (P1jobTime > P2jobTime) return -1;
            else return 0;
        }
    }

    //compare based on shortest remaining run time
    private static class BySRT implements Comparator<Process> {
        public int compare(Process P1, Process P2) {
            int P1remainingTime = P1.totalRunTime - P1.CPUTime;
            int P2remainingTime = P2.totalRunTime - P2.CPUTime;
            if (P1remainingTime < P2remainingTime) return 1;
            if (P1remainingTime > P2remainingTime) return -1;
            else return 0;
        }
    }

    //compare based on least work completed
    private static class ByLWC implements Comparator<Process> {
        public int compare(Process P1, Process P2) {
            int P1remainingTime = P1.totalRunTime - P1.CPUTime;
            int P2remainingTime = P2.totalRunTime - P2.CPUTime;
            if (P1remainingTime > P2remainingTime) return 1;
            if (P1remainingTime < P2remainingTime) return -1;
            else return 0;
        }
    }


}