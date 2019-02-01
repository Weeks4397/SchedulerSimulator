package Generators;

import Processes.Block;

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
