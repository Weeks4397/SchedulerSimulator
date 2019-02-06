package Generators;

public class BlockGeneratorB extends BlockGenerator {

    /**
     * constructor for blocks on a type B resource
     * Average block length is 100.
     */
    public BlockGeneratorB(){
        super();
        this.block.updateR("B");

        this.min = 75;
        this.max = 125;

        this.block.updateBT(RNG.RNG(min, max));

    }
}
