package Processes;
import java.util.*;
import Generators.ProccessGenator;

public class ProcessIII extends Process{

    // processGenerator object for type 3 processes
    public ProccessGenator PG3 = new ProccessGenator(3);

    //Constructor for a type 3 process
    public ProcessIII() {
        super();
        this.Type = 3;
        this.RunTime = PG3.getprocessRunTime();
        this.genBlockRecord();
        this.NextBlockInstant = this.getBlockRecord.get(this.getCurrentListIndex()).getBI;
        this.NextBlockResource = this.getBlockRecord.get(this.getCurrentListIndex()).getR;
        this.NextBlockTime = this.getBlockRecord.get(this.getCurrentListIndex()).getBT;

    }

    //generate the block record, processes of type 3 have multiple blocks
    //While the next block instance is less than run time, add another block to the block record
    public void genBlockRecord(){

        int RT = PG3.getprocessRunTime();
        Block newBlock = PG3.getBlock();
        int newBI = newBlock.getBI();

        this.BlockRecord.add(newBlock);

        while (newBI < RT) {
            PG3.BlockGen3();
            newBlock = PG3.getBlock();
            newBI += newBlock.getBI();
            if (newBI < RT) {
                this.BlockRecord.add(newBlock);
            }
        }
        this.CurrentListIndex = 0;
        this.MaxListIndex = this.getBlockRecord().size()-1;
    }

}
