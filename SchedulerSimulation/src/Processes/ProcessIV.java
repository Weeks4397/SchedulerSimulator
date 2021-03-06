package Processes;
import Generators.*;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Processes of type IV
 */
public class ProcessIV extends process{

    /**
     * processGenerator object for type 4 processes
     */
    private ProccessIVGenerator PG4 = new ProccessIVGenerator();

    /**
     * Constructor for a type 4 process
     */
    public ProcessIV() {
        super();
        this.Type = 4;
        this.RunTime = PG4.getprocessRunTime();
        this.genBlockRecord();
        Block Block1 = this.BlockRecord.poll();
        this.NextBlockInstant = Block1.getBI();
        this.NextBlockResource = Block1.getR();
        this.NextBlockTime = Block1.getBT();
    }

    /**
     *  generate the block record
     *  processes of type 4 have multiple blocks
     *  While the next block instance is less than run time, add another block to the block record
     */
    public void genBlockRecord(){

        int RT = PG4.getprocessRunTime();
        Block newBlock = PG4.getBlock();
        int newBI = newBlock.getBI();
        
        this.BlockRecord.add(newBlock);

        while (newBI < RT) {
            PG4.BlockGen4();
            newBlock = PG4.getBlock();
            newBI += newBlock.getBI();
            newBlock.updateBI(newBI);
            if (newBI < RT) {
                this.BlockRecord.add(newBlock);
            }
        }
    }

    /**
     * creates a clone of this process
     * @return the cloned process
     */
    public process cloneProcess() {
        ProcessIV PIV = new ProcessIV();
        PIV.updateStartBlockWaitTime(this.StartBlockWaitTime);
        PIV.setCPU(this.CPUTime);
        PIV.updateRunTime(this.RunTime);
        PIV.updateType(this.Type);
        PIV.updateNextBlockInstant(this.NextBlockInstant);
        PIV.updateNextBlockResource(this.NextBlockResource);
        PIV.updateNextBlockTime(this.NextBlockTime);
        PIV.updateArrivalTime(this.ArrivalTime);
        PIV.updateStartReadyTime(this.StartReadyTime);
        PIV.updateTotalReadyTime(this.TotalReadyTime);
        PIV.updateCurrentReadyTime(this.CurrentReadyTime);
        PIV.setLastEventTime(this.LastEventTime);
        PIV.updateFinishTime(this.FinishTime);
        PIV.updateBlockServiceTime(this.BlockServiceTime);
        PIV.updateBlockWaitTime(this.BlockWaitTime);
        PIV.updateServiceStartTime(this.ServiceStartTime);
        PIV.updateStringID(this.StringID);
        PIV.updatetSchedInstant_Count(this.SchedInstant_Count);
        PIV.updateTimeOut_Count(this.TimeOut_Count);
        PIV.updatePreempt_Count(this.Preempt_Count);

        Queue<Block> BQ = new LinkedList<Block>();
        Queue<Block> saveQ = new LinkedList<Block>();
        Block currentBlock;
        while (!this.BlockRecord.isEmpty()){
            currentBlock = this.BlockRecord.poll();
            BQ.add(currentBlock);
            saveQ.add(currentBlock);
        }
        PIV.updateBlockRecord(BQ);
        this.updateBlockRecord(saveQ);

        return PIV;
    }

}
