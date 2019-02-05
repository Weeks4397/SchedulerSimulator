package Generators;

public class ProccessIIIGenator extends ProccessGenator {
    /**
     * To create the process runtime, block type, Block runtime, and block occurrence for Process3.
     *
     */
    public ProccessIIIGenator(){
        super();
        double num = RNG.fRNG();
        processRunTime = (int) Math.ceil(150 + 350 * Math.pow(num, (5 / 2)));
        BlockGen3();
    }

    /**
     * To create the block type, Block runtime, and block occurrence for Process3.
     *
     */
    public void BlockGen3() {
        BlockGen block;
        int Type = RNG.RNG(100);
        if (Type <= 34) {
            block = new BlockGenA();
            BlockRunTime = block.getBlockTime();
            BlockType = block.getBlockType();
        } else if (Type <= 84) {
            block = new BlockGenB();
            BlockRunTime = block.getBlockTime();
            BlockType = block.getBlockType();
        } else if (Type <= 99) {
            block = new BlockGenC();
            BlockRunTime = block.getBlockTime();
            BlockType = block.getBlockType();
        }
        BlockOccurs = getNextBlockInstence3();
    }
    /**
     * To get the next Block Instance for process 3.
     *@return   a int that represents the next block occurrence
     */
    public int getNextBlockInstence3() {
        double num = RNG.fRNG();
        return (int) Math.ceil(25 + 50 * Math.pow(num, (7/3)));
    }
}
