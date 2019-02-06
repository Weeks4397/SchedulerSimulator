package Generators;

public class ProccessIIGenerator extends ProccessGenerator {

    /**
     * To create the process runtime and block for Process2.
     *
     */
    public ProccessIIGenerator(){
        super();
        double num = RNG.fRNG();
        processRunTime = (int) Math.ceil(200 + 400 * Math.pow(num, (5 / 3)));
        // Type 2 Has no Block
        theBlock = new BlockGeneratorEmptyBlock();
        BlockOccurs = Integer.MAX_VALUE;
    }
}
