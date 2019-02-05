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
     * @param BI    the block instance which is specific to the process this block is generated for
     */
    public BlockGenerator(int BI){
        this.block = new Block();
        this.block.updateBI(BI);
    }

    /**
     * getter for the block
     */
    public Block getBlock(){
        return this.block;
    }

}
