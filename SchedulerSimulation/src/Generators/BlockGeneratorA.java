package Generators;

public class BlockGeneratorA extends BlockGenerator {

    /**
     * constructor for blocks on a type A resource
     * Average block length is 80.
     */
    public BlockGeneratorA(){
        super();
        this.block.updateR("A");

        this.min = 60;
        this.max = 100;

        this.block.updateBT(RNG.RNG(min, max));

    }
}
