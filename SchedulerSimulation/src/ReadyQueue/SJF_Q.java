package ReadyQueue;

import Processes.process;

public class SJF_Q extends PrioityQ {

    public SJF_Q(){
        super();
        PriQ = new MinHeap<process>(100, Comparators.By_SJF);
    }
}
