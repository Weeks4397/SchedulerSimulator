package Processes;
import Generators.*;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Processes of type III
 */
public class ProcessIII extends process{

    /** processGenerator object for type 3 processes
     */
    private ProccessIIIGenerator PG3 = new ProccessIIIGenerator();

    /**Constructor for a type 3 process
     */
    public ProcessIII() {
        super();
        this.Type = 3;
        this.RunTime = PG3.getprocessRunTime();
        this.genBlockRecord();
        Block Block1 = this.BlockRecord.poll();
        this.NextBlockInstant = Block1.getBI();
        this.NextBlockResource = Block1.getR();
        this.NextBlockTime = Block1.getBT();

    }

    /**
     * generate the block record
     * processes of type 3 have multiple blocks
     * While the next block instance is less than run time, add another block to the block record
     */

    public void genBlockRecord(){

        int RT = PG3.getprocessRunTime();
        Block newBlock = PG3.getBlock();
        int newBI = newBlock.getBI();

        this.BlockRecord.add(newBlock);

        while (newBI < RT) {
            PG3.BlockGen3();
            newBlock = PG3.getBlock();
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
        ProcessIII PIII = new ProcessIII();
        PIII.updateStartBlockWaitTime(this.StartBlockWaitTime);
        PIII.setCPU(this.CPUTime);
        PIII.updateRunTime(this.RunTime);
        PIII.updateType(this.Type);
        PIII.updateNextBlockInstant(this.NextBlockInstant);
        PIII.updateNextBlockResource(this.NextBlockResource);
        PIII.updateNextBlockTime(this.NextBlockTime);
        PIII.updateArrivalTime(this.ArrivalTime);
        PIII.updateNextReadyTime(this.NextReadyTime);
        PIII.setLastEventTime(this.LastEventTime);
        PIII.updateFinishTime(this.FinishTime);
        PIII.updateBlockServiceTime(this.BlockServiceTime);
        PIII.updateBlockWaitTime(this.BlockWaitTime);
        PIII.updateServiceStartTime(this.ServiceStartTime);
        PIII.updateStringID(this.StringID);

        Queue<Block> BQ = new LinkedList<Block>();
        Queue<Block> saveQ = new LinkedList<Block>();
        Block currentBlock;
        while (!this.BlockRecord.isEmpty()){
            currentBlock = this.BlockRecord.poll();
            BQ.add(currentBlock);
            saveQ.add(currentBlock);
        }
        PIII.updateBlockRecord(BQ);
        this.updateBlockRecord(saveQ);

        return PIII;
    }

}
