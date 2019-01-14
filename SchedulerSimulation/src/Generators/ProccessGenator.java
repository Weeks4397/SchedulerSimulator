package Generators;

public class ProccessGenator {
    /**
     * this will be what the runtime for the process is
     */
    private int processRunTime;
    /**
     * this will be the runTime for the block
     */
    private int BlockRunTime;
    /**
     * this is what type of Resource the block is
     */
    private String BlockType;
    /**
     * this is when the block will occur
     */
    private int BlockOccurs;
    /**
     * this is the Max runtime of the system
     */
    public static int MAXINT = 25000;

    /**
     *
     * @param type
     */
    public ProccessGenator(int type) {
        if (type == 1) {
            process1();
        } else if (type == 2) {
            process2();
        } else if (type == 3) {
            process3();
        } else if (type == 4) {
            process4();
        }
    }

    //Purpose: To create the runtime for Process1.
    public void process1() {
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

    //Purpose: To create the runtime for Process2.
    public void process2() {
        double num = RNG.fRNG();
        processRunTime = (int) Math.ceil(200 + 400 * Math.pow(num, (5 / 3)));
        // Type 2 Has no Block
        BlockRunTime = 0;
        BlockType = null;
        BlockOccurs = 0;
    }

    //Purpose: To create the runtime for Process3.
    public void process3() {
        // creates runtime for process
        double num = RNG.fRNG();
        processRunTime = (int) Math.ceil(150 + 350 * Math.pow(num, (5 / 2)));
        BlockGen3();
        // if statement to create block run time and block type
        // the Block has 35% chance of of Type A 50% chance of Type B and 15% chance of
        // type C

    }

    //Purpose: To create the runtime for the Block generator 3.
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

    //Purpose: To create the run time for Process4.
    public void process4() {
        // creates runtime for process
        processRunTime = RNG.RNG(400, 1000);
        // if statement to create block run time and block type
        BlockGen4();

    }

    //Purpose: To create the run time for the Block Generator 4.
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

    //Purpose: To get the next Block Instance.
    public int getNextBlockInstence3() {
        double num = RNG.fRNG();
        return (int) Math.ceil(25 + 50 * Math.pow(num, (7/3)));
    }

    //Purpose: To get the next Block Instance.
    public int getNextBlockInstence4() {
        return RNG.RNG(60, 100);
    }

    //Purpose: To create the process arrival time.
    public static int ProcessArrival() {
        return RNG.RNG(10, 810);
    }

    //Purpose: To get the process run time.
    public int getprocessRunTime() {
        return processRunTime;
    }

    //Purpose: to get the block.
    public int getBlock() {
        return BlockRunTime;
    }

    //Purpose: To get the block type.
    public String getBlockType() {
        return BlockType;
    }

    //Purpose: To get the occurring block type.
    public int getBlockOccurs() {
        return BlockOccurs;
    }
}
