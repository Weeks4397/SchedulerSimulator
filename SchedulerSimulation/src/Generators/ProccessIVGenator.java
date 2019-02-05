package Generators;

public class ProccessIVGenator extends ProccessGenator {
    /**
     * To create the process runtime, block type, Block runtime, and block occurrence for Process4.
     *
     */
    public ProccessIVGenator(){
        super();
        // creates runtime for process
        processRunTime = RNG.RNG(400, 1000);
        // if statement to create block run time and block type
        BlockGen4();
    }
    /**
     * To create the block type, Block runtime, and block occurrence for Process4.
     *
     */
    public void BlockGen4() {
        BlockGen block;
        // the Block has 50% chance of Type B and TypeC
        int Type = RNG.RNG(2);
        if (Type == 0) {
            block = new BlockGenB();
            BlockRunTime = block.getBlockTime();
            BlockType = block.getBlockType();
        } else if (Type == 1) {
            block = new BlockGenC();
            BlockRunTime = block.getBlockTime();
            BlockType = block.getBlockType();
        }
        BlockOccurs = getNextBlockInstence4();
    }
    /**
     * To get the next Block Instance for process 4.
     *@return   a int that represents the next block occurrence
     */
    public int getNextBlockInstence4() {
        return RNG.RNG(60, 100);
    }
}
