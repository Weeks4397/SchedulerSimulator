package Generators;

public class BlockGeneratorEmptyBlock extends BlockGenerator {

    /**
     * constructor for a block that will never occur
     */
    public BlockGeneratorEmptyBlock(int BI){
        super(BI);
        this.block.updateR(null);
        this.block.updateBT(0);

    }
}
