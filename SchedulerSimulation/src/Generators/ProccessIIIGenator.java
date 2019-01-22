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
        int Type = RNG.RNG(100);
        if (Type <= 34) {
            BlockRunTime = RNG.RNG(60, 100);
            BlockType = "A";
        } else if (Type <= 84) {
            BlockRunTime = RNG.RNG(75, 125);
            BlockType = "B";
        } else if (Type <= 99) {
            double num2 = RNG.fRNG();
            BlockRunTime = (int) Math.ceil(100 + 300 * Math.pow(num2, 2));
            BlockType = "C";
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
