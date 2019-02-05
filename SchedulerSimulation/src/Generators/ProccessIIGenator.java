package Generators;

public class ProccessIIGenator extends ProccessGenator {

    /**
     * To create the process runtime and block for Process2.
     *
     */
    public ProccessIIGenator(){
        super();
        double num = RNG.fRNG();
        processRunTime = (int) Math.ceil(200 + 400 * Math.pow(num, (5 / 3)));
        // Type 2 Has no Block
        theBlock = new BlockGeneratorEmptyBlock(Integer.MAX_VALUE);
    }
}
