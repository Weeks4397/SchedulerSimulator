package Generators;

public class ProccessIGenator extends ProccessGenator {

    /**
     * To create the process runtime, block type, Block runtime, and block occurrence for Process1.
     *
     */
    public ProccessIGenator(){
        super();
        processRunTime = RNG.RNG(25, 75);

        // if statement to create block run time and block type
        // Type 1 has a 40% chance of having a Block
        // if it has a block there is a 50% chance of type A and Type B
        if (RNG.RNG(100) <= 40) {
            BlockOccurs = RNG.RNG(1, processRunTime - 1);
            int Type = RNG.RNG(2);
            if (Type == 0) {
                BlockRunTime = RNG.RNG(60, 100);
                BlockType = "A";
            } else if (Type == 1) {
                BlockRunTime = RNG.RNG(75, 125);
                BlockType = "B";
            }
        } else {
            BlockRunTime = 0;
            BlockType = null;
            BlockOccurs = 0;
        }
    }

}
