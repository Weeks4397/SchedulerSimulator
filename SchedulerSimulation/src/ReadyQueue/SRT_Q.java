package ReadyQueue;

import Processes.process;

public class SRT_Q extends PrioityQ{

    public SRT_Q(){
        super();
        PriQ = new MinHeap<process>(100, Comparators.By_SRT);
    }

}
