package Generators;

public class ProccessIGenator extends ProccessGenator {

    /**
     * To create the process runtime and block for Process1.
     *
     */
    public ProccessIGenator(){
        super();
        processRunTime = RNG.RNG(25, 75);

        this.ChanceA = 50;
        this.ChanceB = 50;

        // if statement to create block run time and block type
        // Type 1 has a 40% chance of having a Block
        // if it has a block there is a 50% chance of type A and Type B
        if (RNG.RNG(100) <= 40) {
            BlockOccurs = RNG.RNG(1, processRunTime - 1);
            int Type = RNG.RNG(100);
            if (Type < ChanceA) {
              theBlock = new BlockGeneratorA(BlockOccurs);
            } else{
               theBlock = new BlockGeneratorB(BlockOccurs);
            }
        } else {
            theBlock = new BlockGeneratorEmptyBlock(Integer.MAX_VALUE);
        }
    }

}
