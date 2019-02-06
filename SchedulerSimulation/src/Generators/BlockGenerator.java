package Generators;

import Processes.Block;


public abstract class BlockGenerator {
    /**
     * data members for BlockGenerator
     */

    /**
     * block is the new block object that is going to be generated
     */
    public Block block;

    /**
     * Min is the minimum amount of time a block can be.
     */
    public int min;

    /**
     * max is the max amount of time a block can be.
     */
    public int max;

    /**
     * super constructor for block object
     */
    public BlockGenerator(){
        this.block = new Block();
    }

    /**
     * getter for the block
     */
    public Block getBlock(){
        return this.block;
    }

}
