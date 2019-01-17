package Processes;
import Generators.ProccessGenator;
import java.util.ArrayList;
import java.util.Comparator;

/**
 * A Process contains a set of numbers, a list of blocks, and a string for the next resource.
 * A process can be of 4 different types, TypeI, TypeII, TypeIII, or Type IV.
 *
 */
public abstract class process {

    //data members of process

    /**
     * StringID is the id for the process "P%d%d%d"
     */
    public String StringID;

    /** Type is the type of process either 1, 2, 3 or 4
     *
     */
    public int Type;

    /** RunTime is the is the amount of time a process needs with cpu before completion
     *
     */
    public int RunTime;

    /** NextBlock Instant is the time at which the next block will occur
     * If there is no next block, set to MAXINT
     */
    public int NextBlockInstant;

    /**
     *  NextBlockResource is the the type of resource that is needed for the next block
     *  If there is no next block, set to null
     */
    public String NextBlockResource;

    /**
     * NextBlockTime is the length of the next block
     *  if there is no next block, set to 0
     */
    public int NextBlockTime;

    /**
     *  BlockRecords is a list of all of the blocks for a process or can be null
     *  A block consists of a block instant, a type of resoruce, and a block length
     */
    public ArrayList<Block> BlockRecord;

    /**
     * CurrentListIndex is the index of the next block in the BlockRecord or null
     */
    public int CurrentListIndex;

    /**
     * MaxListIndex is the index of the last block in the BlockRecord or null
     */
    public int MaxListIndex;

    /**
     *  ArricalTime is the time at which a process initally arrives to the ready queue from the master list of processes
     */
    public int ArrivalTime;

    /**
     * CPUTime is the total time the process has had with the cpu since the last event
     * CPUTime is initially 0
     */
    public int CPUTime;

    /**
     * NextReadyTime is initially equal to ArrivalTime
     * NextReadyTime is adjusted when a process arrives back into the ready queue after a block, time-slice, or preemption
     */
    public int NextReadyTime;

    /**
     * LastEventTime is the global time at which the last event occured effecting this process
     */
    public int LastEventTime;

    /**
     * FinishTime is the global time at which this processes CPUTime becomes equal to the RunTime
     */
    public int FinishTime;

    /**
     * BlockServiceTime is the amount of time a process spent in the blocked state
     */
    public int BlockServiceTime;

    /**
     * ServiceStartTime is the time at which a process begins to use the resource
     */
    public int ServiceStartTime;

    /**
     * Constructor for a process
     */
    public process() {

        this.StringID = "P-1";
        this.CPUTime = 0;
        this.LastEventTime = 0;
        this.FinishTime = ProccessGenator.MAXINT;
        this.BlockServiceTime = 0;
        this.ServiceStartTime = ProccessGenator.MAXINT;
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
        this.ServiceStartTime = ProccessGenator.MAXINT;
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
     * if there are no blocks, the block record, current index, and max index are set to null
     */
    public abstract void genBlockRecord();


    /**sets init arrival time and nextReady time
     * @param t  time at which process scheduled to arrive into ready queue from master list
     */
    public void setArrivalandReadyTime(int t){
        this.ArrivalTime = t;
        this.NextReadyTime = t;
    }


    /**
     * Getters for Processes
     */


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

    public ArrayList<Block> getBlockRecord() {
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
        this.CPUTime += time;
    }


}