package Processes;
import Generators.*;

public class ProcessI extends process {

    /**
     * processGenerator object for type 1 processes
     */
   private ProccessIGenator PG1 = new ProccessIGenator();

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
     * generate the block record, processes of type 1 have either 1 or no blocks
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


}