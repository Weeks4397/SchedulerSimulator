package Processes;
import Generators.*;


public class ProcessIV extends process{

    /**
     * processGenerator object for type 4 processes
     */
    private ProccessIVGenator PG4 = new ProccessIVGenator();

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
     *  generate the block record, processes of type 4 have multiple blocks
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
            if (newBI < RT) {
                this.BlockRecord.add(newBlock);
            }
        }
    }

}
