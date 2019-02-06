package Generators;

import Processes.Block;


public abstract class ProccessGenerator implements ProcessGeneratorInterface {

    /**
     * this will be what the runtime for the process is
     */
    int processRunTime;

    /**
     * this will generate the block for the process
     */
    BlockGenerator theBlock;

    /**
     * this is when theBlock will occur
     */
    int BlockOccurs;

    /**
     * ChanceA is the percentage chance that a process will Block on Resource A
     */
    int ChanceA;

    /**
     * ChanceB is the percentage chance that a process will Block on Resource B
     */
    int ChanceB;

    /**
     * ChanceC is the percentage chance that a process will Block on Resource C
     */
    int ChanceC;

    /**
     * this is the Max runtime of the system
     * this is updated when the work set is generated
     */
    public static int MAXINT = 25000;

    public ProccessGenerator() {

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

    /**
     *  To get the block.
     *@return   a block to be used in a process
     */
    public Block getBlock(){
        theBlock.getBlock().updateBI(BlockOccurs);
        return theBlock.getBlock();
    }
}
