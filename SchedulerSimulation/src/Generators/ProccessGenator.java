package Generators;

import Processes.Block;

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
     * constructs a ProccessGenator to be used in Process to populate all random variables in class
     *
     * @param  type   the type of process that needs to be generator
     *
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

    /**
     * To create the process runtime, block type, Block runtime, and block occurrence for Process1.
     *
     */
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

    /**
     * To create the process runtime, block type, Block runtime, and block occurrence for Process2.
     *
     */
    public void process2() {
        double num = RNG.fRNG();
        processRunTime = (int) Math.ceil(200 + 400 * Math.pow(num, (5 / 3)));
        // Type 2 Has no Block
        BlockRunTime = 0;
        BlockType = null;
        BlockOccurs = 0;
    }

    /**
     * To create the process runtime, block type, Block runtime, and block occurrence for Process3.
     *
     */
    public void process3() {
        // creates runtime for process
        double num = RNG.fRNG();
        processRunTime = (int) Math.ceil(150 + 350 * Math.pow(num, (5 / 2)));
        BlockGen3();
        // if statement to create block run time and block type
        // the Block has 35% chance of of Type A 50% chance of Type B and 15% chance of
        // type C

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
     * To create the process runtime, block type, Block runtime, and block occurrence for Process3.
     *
     */
    public void process4() {
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
     * To get the next Block Instance for process 3.
     *@return   a int that represents the next block occurrence
     */
    public int getNextBlockInstence3() {
        double num = RNG.fRNG();
        return (int) Math.ceil(25 + 50 * Math.pow(num, (7/3)));
    }

    /**
     * To get the next Block Instance for process 4.
     *@return   a int that represents the next block occurrence
     */
    public int getNextBlockInstence4() {
        return RNG.RNG(60, 100);
    }

    /**
     *  To create the process arrival time.
     *@return   a int that represents the next arrive time for a process
     */
    public static int ProcessArrival() {
        return RNG.RNG(10, 810);
    }

    /**
     *  To get the process runtime.
     *@return   a int that represents the processes runtime
     */
    public int getprocessRunTime() {
        return processRunTime;
    }

    //Purpose: to get the block.
    /**
     *  To get the block.
     *@return   a block to be used in a process
     */
    public Block getBlock(){
        return new Block(BlockOccurs,BlockType,BlockRunTime);
    }
}
