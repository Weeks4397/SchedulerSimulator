package Generators;

public class ProccessIGenator extends ProccessGenator {

    /**
     * To create the process runtime, block type, Block runtime, and block occurrence for Process1.
     *
     */
    public ProccessIGenator(){
        super();
        processRunTime = RNG.RNG(25, 75);

        BlockGen block;

        // if statement to create block run time and block type
        // Type 1 has a 40% chance of having a Block
        // if it has a block there is a 50% chance of type A and Type B
        if (RNG.RNG(100) <= 40) {
            BlockOccurs = RNG.RNG(1, processRunTime - 1);
            int Type = RNG.RNG(2);
            if (Type == 0) {
                block = new BlockGenA();
                BlockRunTime = block.getBlockTime();
                BlockType = block.getBlockType();
            } else if (Type == 1) {
                block = new BlockGenB();
                BlockRunTime = block.getBlockTime();
                BlockType = block.getBlockType();
            }
        } else {
            BlockRunTime = 0;
            BlockType = null;
            BlockOccurs = 0;
        }
    }

}
