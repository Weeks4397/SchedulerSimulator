package ReadyQueue;

import Processes.process;

public class LWC_Q extends PrioityQ {

    public LWC_Q(){
        super();
        PriQ = new MinHeap<process>(100, Comparators.By_LWC);
    }

}
