package Generators;

public class ProccessIVGenerator extends ProccessGenerator {
    /**
     * To create the process runtime, block type, Block runtime, and block occurrence for Process4.
     *
     */
    public ProccessIVGenerator(){
        super();
        // creates runtime for process
        processRunTime = RNG.RNG(400, 1000);
        ChanceB = 50;
        ChanceC = 50;
        // if statement to create block run time and block type
        BlockGen4();
    }
    /**
     * To create the block for Process4.
     *
     */
    public void BlockGen4() {

        BlockOccurs = getNextBlockInstence4();
        // the Block has 50% chance of Type B and TypeC
        int Type = RNG.RNG(100);
        if (Type < ChanceB) {
            theBlock = new BlockGeneratorB();
        }
        else{
            theBlock = new BlockGeneratorC();

        }
    }
    /**
     * To get the next Block Instance for process 4.
     *@return   a int that represents the next block occurrence
     */
    public int getNextBlockInstence4() {
        return RNG.RNG(60, 100);
    }
}
