package Generators;

public abstract class BlockGen implements BlockGenInterface{

    /**
     * is the random number that is the runtime for the block
     */
    public int blockTime;
    /**
     * is a string that represents the type of resource the block will use
     */
    public String blockType;

    public BlockGen(){

    }

    //Getters

    public int getBlockTime() {
        return blockTime;
    }

    public String getBlockType() {
        return blockType;
    }
}
