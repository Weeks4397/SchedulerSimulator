package Generators;

import Processes.Block;

public class BlockGenA extends BlockGen {

    public BlockGenA(){
        blockType = "A";
        blockTime = RNG.RNG(60, 100);
    }
}
