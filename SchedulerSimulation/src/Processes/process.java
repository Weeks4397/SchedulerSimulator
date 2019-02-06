package Processes;
import java.util.LinkedList;
import java.util.Queue;

public abstract class process implements processInterface {

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
     * If there is no next block, set to MAX_val
     */
    public int NextBlockInstant;

    /**
     *  NextBlockResource is the the type of resource that is needed for the next block
     *  If there is no next block, set to null
     */
    public String NextBlockResource;

    /**
     * NextBlockTime is the length of the next block which is the amount of time the process needs
     * the resource for.
     *  if there is no next block, set to 0
     */
    public int NextBlockTime;

    /**
     *  BlockRecord is a queue filled with all of the blocks for a process or can be null
     *  A block consists of a block instant, a type of resoruce, and a block length
     */
    public Queue<Block> BlockRecord;

    /**
     *  ArrivalTime is the time at which a process initially arrives to the ready queue from the master list of processes
     */
    public int ArrivalTime;

    /**
     * CPUTime is the total time the process has had with the cpu since the last event
     * CPUTime is initially 0
     */
    public int CPUTime;

    /**
     * StartReadyTime is initially equal to ArrivalTime
     * StartReadyTime is adjusted when a process arrives back into the ready queue after a block, time-slice, or preemption
     */
    public int StartReadyTime;

    /**
     * TotalReadyTime is the total amount of time a process has spent in the ReadyQ
     */
    public int TotalReadyTime;

    /**
     * LastEventTime is the global time at which the last event occurred effecting this process
     */
    public int LastEventTime;

    /**
     * FinishTime is the global time at which this processes CPUTime becomes equal to the RunTime
     */
    public int FinishTime;

    /**
     * BlockServiceTime is the total amount of time a process has spent being serviced by a resource
     */
    public int BlockServiceTime;

    /**
     * BlockWaitTime is the total amount of time a process waited for service
     * This is the ServiceStartTime - StartBlockWaitTime
     */
    public int BlockWaitTime;

    /**
     * StartBlockWaitTime is the last global time at which the process has blocked
     */
    public int StartBlockWaitTime;

    /**
     * ServiceStartTime is the global time at which a process begins to use the resource
     */
    public int ServiceStartTime;

    /**
     * SchedInstant_Count is the total number of schedule instants the process has been apart of
     * A schedule instant is an event that causes processes to change state.
     */
    public int SchedInstant_Count;

    /**
     * TimeOut_Count is the total number of timeouts the process has had
     */
    public int TimeOut_Count;

    /**
     * Preempt_Count
     */
    public int Preempt_Count;

    /**
     * Constructor for a process
     */
    public process() {

        this.StringID = "P-1";
        this.CPUTime = 0;
        this.LastEventTime = 0;
        this.FinishTime = Integer.MAX_VALUE;
        this.BlockServiceTime = 0;
        this.ServiceStartTime = Integer.MAX_VALUE;
        this.BlockRecord = new LinkedList<Block>();
        this.StartReadyTime = Integer.MAX_VALUE;
        this.TotalReadyTime = 0;
        this.ServiceStartTime = Integer.MAX_VALUE;
        this.BlockWaitTime = 0;
        this.StartBlockWaitTime = Integer.MAX_VALUE;
        this.SchedInstant_Count = 0;
        this.TimeOut_Count = 0;
        this.Preempt_Count = 0;
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
     * Populates the block record with blocks in order
     * if there are no blocks, the block record is set to null
     */
    public abstract void genBlockRecord();


    /**sets init arrival time
     * @param t  time at which process scheduled to arrive into ready queue from master list
     */
    public void setArrivalTime(int t){
        this.ArrivalTime = t;
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

    public Queue<Block> getBlockRecord() {
        return this.BlockRecord;
    }

    public int getArrivalTime() {
        return this.ArrivalTime;
    }

    public int getCPUTime() {
        return this.CPUTime;
    }

    public int getStartReadyTime() {
        return this.StartReadyTime;
    }

    public int getTotalReadyTime() {
        return this.TotalReadyTime;
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

    public int getBlockWaitTime(){return this.BlockWaitTime;}

    public int getStartBlockWaitTime() {return this.StartBlockWaitTime;}

    public int getSchedInstant_Count() {return this.SchedInstant_Count;}

    public int getTimeOut_Count() { return this.TimeOut_Count; }

    public int getPreempt_Count(){return this.Preempt_Count;}

    //Mutators for Processes

    /**updates the global block instant to be current global time
     * @param time  time is a non null int > 0
     */
    public void updateStartBlockWaitTime(int time){
        this.StartBlockWaitTime = time;
    }
    public void updateCPU (int time) {
        this.CPUTime += time;
    }

    public void setCPU (int time) {
        this.CPUTime = time;
    }

    public void updateRunTime(int runTime) {
        RunTime = runTime;
    }

    public void updateNextBlockInstant(int nextBlockInstant) {
        NextBlockInstant = nextBlockInstant;
    }

    public void updateNextBlockResource(String nextBlockResource) {
        NextBlockResource = nextBlockResource;
    }

    public void updateNextBlockTime(int nextBlockTime) {
        NextBlockTime = nextBlockTime;
    }

    public void updateArrivalTime(int arrivalTime) {
        ArrivalTime = arrivalTime;
    }

    public void updateStartReadyTime(int startReadyTime) {
        StartReadyTime = startReadyTime;
    }

    public void updateTotalReadyTime(int totalReadyTime) {
        TotalReadyTime = totalReadyTime;
    }

    public void setLastEventTime(int lastEventTime) {
        LastEventTime = lastEventTime;
    }

    public void updateFinishTime(int finishTime) {
        FinishTime = finishTime;
    }

    public void updateBlockServiceTime(int blockServiceTime) {
        BlockServiceTime = blockServiceTime;
    }

    public void updateBlockWaitTime(int blockWaitTime) {
        BlockWaitTime = blockWaitTime;
    }

    public void updateServiceStartTime(int serviceStartTime) {
        ServiceStartTime = serviceStartTime;
    }

    public void updateType(int type) {
        Type = type;
    }

    public void updateBlockRecord(Queue<Block> blockRecord) {
        BlockRecord = blockRecord;
    }

    public void updateStringID(String stringID) {
        StringID = stringID;
    }

    public void updatetSchedInstant_Count(int schedInstant_Count) {SchedInstant_Count = schedInstant_Count;}

    public void updateTimeOut_Count(int timeOut_Count) {TimeOut_Count = timeOut_Count; }

    public void updatePreempt_Count(int preempt_Count){Preempt_Count = preempt_Count;}


    /**
     * GotoNextBlock    goes to the next block in the block record if there is one
     * if not set block variables appropriately
     */
    public void GotoNextBlock(){
        if (!this.getBlockRecord().isEmpty()) {
            Block nextBlock = this.BlockRecord.poll();
            this.NextBlockInstant = nextBlock.getBI();
            this.NextBlockResource = nextBlock.getR();
            this.NextBlockTime = nextBlock.getBT();
        }
        else {
            this.NextBlockInstant = Integer.MAX_VALUE;
            this.NextBlockResource = null;
            this.NextBlockTime = 0;
        }
    }

    /**
     * toString formats important information about process into a string for debugging purposes
     */
    public String toString(){
       String theProcess = String.format("StringID: %s\nType: %s\nArrival Time: %d\nRun Time: %d" +
                       "\nCPU time: %d\nNext Block Time: %d\nNext Block Resource: %s \nNext Block Instant: %d" +
                       "\nBlock Service Time: %d\nBlock Wait Time: %d\nFinish Time: %d\nTotal Ready Time: %d\nSched Instant Count: %d" +
                       "\nTimeout count: %d\nPreempt count: %d",
               this.getStringID(), this.getType(), this.getArrivalTime(), this.getRunTime(),
               this.getCPUTime(), this.getNextBlockTime(), this.getNextBlockResource(), this.getNextBlockInstant(),
                this.getBlockServiceTime(), this.getBlockWaitTime(), this.getFinishTime(), this.getTotalReadyTime(),
               this.getSchedInstant_Count(), this.getTimeOut_Count(), this.getPreempt_Count());
       return theProcess;
    }

    /**
     * cloneProcess makes a deep copy of a given process
     * @return process  a deep copy of the given process
     */
    public abstract process cloneProcess();

}