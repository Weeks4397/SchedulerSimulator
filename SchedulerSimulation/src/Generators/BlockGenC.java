package Generators;

public class BlockGenC extends BlockGen {

    public BlockGenC(){
        blockType = "B";
        double num2 = RNG.fRNG();
        blockTime = (int) Math.ceil(100 + 300 * Math.pow(num2, 2));
    }
}
