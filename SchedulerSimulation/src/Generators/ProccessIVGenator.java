package Generators;

public class ProccessIVGenator extends ProccessGenator {
    /**
     * To create the process runtime, block type, Block runtime, and block occurrence for Process4.
     *
     */
    public ProccessIVGenator(){
        super();
        // creates runtime for process
        processRunTime = RNG.RNG(400, 1000);
        // if statement to create block run time and block type
        BlockGen4();
    }
    /**
     * To create the block type, Block runtime, and block occurrence for Process4.
     *
     */
    public void BlockGen4() {
        // the Block has 50% chance of Type B and TypeC
        int Type = RNG.RNG(2);
        if (Type == 0) {
            BlockRunTime = RNG.RNG(75, 125);
            BlockType = "B";
        } else if (Type == 1) {
            double num = RNG.fRNG();
            BlockRunTime = (int) Math.ceil(100 + 300 * Math.pow(num, 2));
            BlockType = "C";
        }
        BlockOccurs = getNextBlockInstence4();
    }
    /**
     * To get the next Block Instance for process 4.
     *@return   a int that represents the next block occurrence
     */
    public int getNextBlockInstence4() {
        return RNG.RNG(60, 100);
    }
}
