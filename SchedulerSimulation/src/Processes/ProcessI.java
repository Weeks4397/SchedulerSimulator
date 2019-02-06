package Processes;
import Generators.*;

import java.util.LinkedList;

/**
 * Processes of type I
 */
public class ProcessI extends process {

    /**
     * processGenerator object for type 1 processes
     */
   private ProccessIGenerator PG1 = new ProccessIGenerator();

    /**
     * the block information for this processI object
     */
    private Block newBlock = PG1.getBlock();

    /**
     * Constructor for a type 1 process
     */
    public ProcessI() {
        super();
        this.Type = 1;
        this.RunTime = PG1.getprocessRunTime();
        this.genBlockRecord();
        updateP1BlockVariables();

    }

    /**
     * generate the block record
     * processes of type 1 have either 1 or no blocks
     */
    public void genBlockRecord(){
        if (newBlock.getBT() != 0){
            this.BlockRecord.add(newBlock);
        }
        else {
            this.BlockRecord = null;
        }
    }

    /**updates the NextBlockInstance, NextBlockResource, and NextBlockTime for processes of type 1
     *
     */
    public void updateP1BlockVariables(){
       if (this.getBlockRecord() == null) {
           this.NextBlockInstant = Integer.MAX_VALUE;
           this.NextBlockResource = null;
           this.NextBlockTime = 0;
       }
       else{
           Block Block1 = this.BlockRecord.poll();
           this.NextBlockInstant = Block1.getBI();
           this.NextBlockResource = Block1.getR();
           this.NextBlockTime = Block1.getBT();
       }
    }
    /**
     * creates a clone of this process
     * @return the cloned process
     */
    public process cloneProcess() {
        ProcessI PI = new ProcessI();
        PI.updateStartBlockWaitTime(this.StartBlockWaitTime);
        PI.setCPU(this.CPUTime);
        PI.updateRunTime(this.RunTime);
        PI.updateType(this.Type);
        PI.updateNextBlockInstant(this.NextBlockInstant);
        PI.updateNextBlockResource(this.NextBlockResource);
        PI.updateNextBlockTime(this.NextBlockTime);
        PI.updateArrivalTime(this.ArrivalTime);
        PI.updateStartReadyTime(this.StartReadyTime);
        PI.updateTotalReadyTime(this.TotalReadyTime);
        PI.setLastEventTime(this.LastEventTime);
        PI.updateFinishTime(this.FinishTime);
        PI.updateBlockServiceTime(this.BlockServiceTime);
        PI.updateBlockWaitTime(this.BlockWaitTime);
        PI.updateServiceStartTime(this.ServiceStartTime);
        PI.updateStringID(this.StringID);
        PI.updatetSchedInstant_Count(this.SchedInstant_Count);
        PI.updateTimeOut_Count(this.TimeOut_Count);
        PI.updatePreempt_Count(this.Preempt_Count);


        PI.updateBlockRecord(new LinkedList<Block>());


        return PI;
    }
}