package ReadyQueue;

import Processes.process;

/**
 * this object is constructed in the SRT algorithm subclass of the scheduler.
 */
public class SRT_Q extends PrioityQ{

    public SRT_Q(){
        super();
        PriQ = new MinHeap<process>(100, Comparators.By_SRT);
    }

}
