package Processes;

import java.util.Queue;

/**
 * A Process contains a set of numbers, a queue of blocks, and a string for the next resource.
 * A process is updated and tracked through the scheduler to provide performance data in the simulation.
 * A Process can be in one of five states:
 *          MasterList: The process is on the Workset list awaiting arrival to the ready queue
 *          Ready:      The process is in the ready queue
 *          Running:    The process is receiving time with the CPU to complete its run time
 *          Blocked:    The process is either waiting for or being served by a resource
 *          Finished:   The process has finished its needed time with the cpu (RunTime - CPUTime == 0)
 * A process can be of 4 different types, TypeI, TypeII, TypeIII, or Type IV.
 *
 */
 interface processInterface {
    /**Sets the StringID
     * If string id is over max limit set to "-1"
     * @param i  keeps track of current processID index
     * @param iMax  the maximum amount of process IDs available
     */
    void setStringID(int i, int iMax);


    /**
     * GotoNextBlock    goes to the next block in the block record if there is one
     * if not set block variables appropriately
     */
    void GotoNextBlock();

    /**
     * toString formats important information about process into a string for debugging purposes
     */
    String toString();

    /**
     * cloneProcess makes a deep copy of a given process
     * @return process  a deep copy of the given process
     */
    process cloneProcess();

    /**Generate the BlockRecord
     * Populates the block record with blocks in order
     * if there are no blocks, the block record is set to null
     */
    void genBlockRecord();

    /**
     * Getters for Processes
     */
     String getStringID();
     int getType();
     int getRunTime();
     int getNextBlockInstant();
     String getNextBlockResource();
     int getNextBlockTime();
     Queue<Block> getBlockRecord();
     int getArrivalTime();
     int getCPUTime();
     int getStartReadyTime();
     int getTotalReadyTime();
     int getLastEventTime();
     int getFinishTime();
     int getBlockServiceTime();
     int getServiceStartTime();
     int getBlockWaitTime();
     int getStartBlockWaitTime();
     int getSchedInstant_Count();
     int getTimeOut_Count();
     int getPreempt_Count();

    /**
     * mutaters for Processes
     */
    void updateStartBlockWaitTime(int time);
    void updateCPU (int time);
    void setCPU (int time);
    void updateRunTime(int runTime);
    void updateNextBlockInstant(int nextBlockInstant);
    void updateNextBlockResource(String nextBlockResource);
    void updateNextBlockTime(int nextBlockTime);
    void updateArrivalTime(int arrivalTime);
    void updateStartReadyTime(int startReadyTime);
    void updateTotalReadyTime(int totalReadyTime);
    void setLastEventTime(int lastEventTime);
    void updateFinishTime(int finishTime);
    void updateBlockServiceTime(int blockServiceTime);
    void updateBlockWaitTime(int blockWaitTime);
    void updateServiceStartTime(int serviceStartTime);
    void updateType(int type);
    void updateBlockRecord(Queue<Block> blockRecord);
    void updateStringID(String stringID);
    void updatetSchedInstant_Count(int schedInstant_Count);
    void updateTimeOut_Count(int timeOut_Count);
    void updatePreempt_Count(int preempt_Count);




}
