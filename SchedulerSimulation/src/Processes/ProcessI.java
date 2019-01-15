package Processes;

import java.util.*;
import Generators.ProccessGenator;

public class ProcessI extends process {

    // processGenerator object for type 1 processes
    public ProccessGenator PG1 = new ProccessGenator(1);

    //the block information for this processI object
    public Block newBlock = PG1.getBlock();

    //Constructor for a type 1 process
    public ProcessI() {
        super();
        this.Type = 1;
        this.RunTime = PG1.getprocessRunTime();
        this.NextBlockInstant = newBlock.getBI();
        this.NextBlockResource = newBlock.getR();
        this.NextBlockTime = newBlock.getBT();
        this.genBlockRecord();

    }

    //generate the block record, processes of type 1 have either 1 or no blocks
    public void genBlockRecord(){
        if (newBlock.getBT() != 0){
            this.BlockRecord = new ArrayList<Block>();
            this.BlockRecord.add(newBlock);
            this.CurrentListIndex = 0;
            this.MaxListIndex = 1;
        }
        else {
            this.BlockRecord = null;
            this.CurrentListIndex = 0;
            this.MaxListIndex = 0;
        }
    }
}
