package Processes;
import java.util.*;
import Generators.ProccessGenator;

public class ProcessIV extends Process{

    // processGenerator object for type 4 processes
    public ProccessGenator PG4 = new ProccessGenator(4);

    //Constructor for a type 4 process
    public ProcessIV() {
        super();
        this.Type = 4;
        this.RunTime = PG4.getprocessRunTime();
        this.genBlockRecord();
        this.NextBlockInstant = this.getBlockRecord.get(this.getCurrentListIndex()).getBI;
        this.NextBlockResource = this.getBlockRecord.get(this.getCurrentListIndex()).getR;
        this.NextBlockTime = this.getBlockRecord.get(this.getCurrentListIndex()).getBT;

    }

    //generate the block record, processes of type 4 have multiple blocks
    //While the next block instance is less than run time, add another block to the block record
    public void genBlockRecord(){

        int RT = PG4.getprocessRunTime();
        Block newBlock = PG4.getBlock();
        int newBI = newBlock.getBI();
        
        this.BlockRecord.add(newBlock);

        while (newBI < RT) {
            PG4.BlockGen4();
            newBlock = PG4.getBlock();
            newBI += newBlock.getBI();
            if (newBI < RT) {
                this.BlockRecord.add(newBlock);
            }
        }
        this.CurrentListIndex = 0;
        this.MaxListIndex = this.getBlockRecord().size()-1;
    }
}
