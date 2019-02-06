package Generators;

public class ProccessIIIGenerator extends ProccessGenerator {
    /**
     * To create the process runtime, block type, Block runtime, and block occurrence for Process3.
     *
     */
    public ProccessIIIGenerator(){
        super();
        double num = RNG.fRNG();
        processRunTime = (int) Math.ceil(150 + 350 * Math.pow(num, (5 / 2)));
        ChanceA = 35;
        ChanceB = 50;
        ChanceC = 15;
        BlockGen3();
    }

    /**
     * To create the block for Process3.
     *
     */
    public void BlockGen3() {
        BlockOccurs = getNextBlockInstence3();
        int Type = RNG.RNG(100);
        if (Type < ChanceA) {
            theBlock = new BlockGeneratorA();

        } else if (Type < ChanceA + ChanceB) {
            theBlock = new BlockGeneratorB();

        } else {
            theBlock = new BlockGeneratorC();
        }
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
