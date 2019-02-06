package Generators;

public class BlockGeneratorC extends BlockGenerator {

    /**
     * constructor for blocks on a type C resource
     * Blocks of type C have a Block time that uses a nonuniform distribution
     * Average block length is 200.
     */
    public BlockGeneratorC(){
        super();
        this.block.updateR("C");

        this.min = 100;
        this.max = 400;

        double num =  RNG.fRNG();
        this.block.updateBT((int) Math.ceil(min + (max - 100) * Math.pow(num, 2)));

    }
}
