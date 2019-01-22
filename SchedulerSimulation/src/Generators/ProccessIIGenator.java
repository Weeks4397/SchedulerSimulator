package Generators;

public class ProccessIIGenator extends ProccessGenator {

    /**
     * To create the process runtime, block type, Block runtime, and block occurrence for Process2.
     *
     */
    public ProccessIIGenator(){
        super();
        double num = RNG.fRNG();
        processRunTime = (int) Math.ceil(200 + 400 * Math.pow(num, (5 / 3)));
        // Type 2 Has no Block
        BlockRunTime = 0;
        BlockType = null;
        BlockOccurs = 0;
    }
}
