package Generators;

public class ProccessIGenator extends ProccessGenator {

    /**
     * To create the process runtime and block for Process1.
     *
     */
    public ProccessIGenator(){
        super();
        processRunTime = RNG.RNG(25, 75);

<<<<<<< HEAD
        this.ChanceA = 50;
        this.ChanceB = 50;
=======
        BlockGen block;
>>>>>>> 54a2adc548bfecae0860090d2c16d889bdbc3709

        // if statement to create block run time and block type
        // Type 1 has a 40% chance of having a Block
        // if it has a block there is a 50% chance of type A and Type B
        if (RNG.RNG(100) <= 40) {
            BlockOccurs = RNG.RNG(1, processRunTime - 1);
<<<<<<< HEAD
            int Type = RNG.RNG(100);
            if (Type < ChanceA) {
              theBlock = new BlockGeneratorA(BlockOccurs);
            } else{
               theBlock = new BlockGeneratorB(BlockOccurs);
=======
            int Type = RNG.RNG(2);
            if (Type == 0) {
                block = new BlockGenA();
                BlockRunTime = block.getBlockTime();
                BlockType = block.getBlockType();
            } else if (Type == 1) {
                block = new BlockGenB();
                BlockRunTime = block.getBlockTime();
                BlockType = block.getBlockType();
>>>>>>> 54a2adc548bfecae0860090d2c16d889bdbc3709
            }
        } else {
            theBlock = new BlockGeneratorEmptyBlock(Integer.MAX_VALUE);
        }
    }

}
