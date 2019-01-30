package Generators;

import Processes.Block;

/**
 * ProcessGenerator is used to generate random numbers that will be used to help construct processes
 * A ProcessGenerator can be of 4 different types, TypeI, TypeII, TypeIII, or Type IV for each type of process
 */
public abstract class ProccessGenator {
    /**
     * this will be what the runtime for the process is
     */
    public int processRunTime;
    /**
     * this will be the runTime for the block
     */
    public int BlockRunTime;
    /**
     * this is what type of Resource the block is
     */
    public String BlockType;
    /**
     * this is when the block will occur
     */
    public int BlockOccurs;
    /**
     * this is the Max runtime of the system
     * this is updated when the work set is generated
     */
    public static int MAXINT = 25000;

    public ProccessGenator() {

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
        return new Block(BlockOccurs,BlockType,BlockRunTime);
    }
}
