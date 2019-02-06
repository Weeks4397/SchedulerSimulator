package ReadyQueue;

import Minheap.Comparators;
import Minheap.MinHeap;
import Processes.process;
/**
 * this object is constructed in the LWC algorithm subclass of the scheduler.
 */
public class LWC_Q extends PrioityQ {

    public LWC_Q(){
        super();
        PriQ = new MinHeap<process>(100, Comparators.By_LWC);
    }

}
