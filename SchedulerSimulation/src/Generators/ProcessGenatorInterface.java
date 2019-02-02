package Generators;

import Processes.Block;

/**
 * ProcessGenerator is used to generate random numbers that will be used to help construct processes
 * A ProcessGenerator can be of 4 different types, TypeI, TypeII, TypeIII, or Type IV for each type of process
 */
public interface ProcessGenatorInterface {


    /**
     *  To get the process runtime.
     *@return   a int that represents the processes runtime
     */
    int getprocessRunTime();

    /**
     *  To get the block.
     *@return   a block to be used in a process
     */
    Block getBlock();
}
