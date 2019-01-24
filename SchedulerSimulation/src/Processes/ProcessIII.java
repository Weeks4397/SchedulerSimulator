package Processes;
import Generators.*;


public class ProcessIII extends process{

    /** processGenerator object for type 3 processes
     */
    private ProccessIIIGenator PG3 = new ProccessIIIGenator();

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
            if (newBI < RT) {
                this.BlockRecord.add(newBlock);
            }
        }
    }

}
