package ReadyQueue;

import Minheap.Comparators;
import Minheap.MinHeap;
import Processes.process;

/**
 * this object is constructed in the SJF algorithm subclass of the scheduler.
 */
public class SJF_Q extends PrioityQ {

    public SJF_Q(){
        super();
        PriQ = new MinHeap<process>(100, Comparators.By_SJF);
    }
}
