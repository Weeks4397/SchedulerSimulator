package ReadyQueue;

import Processes.process;

import java.util.LinkedList;

/**
 * this object is constructed in the FIFO algorithm subclass of the scheduler.
 */
public class FIFO_Q extends SimpleQ{

    public FIFO_Q(){
        super();
        Q = new LinkedList<process>();
    }
}
