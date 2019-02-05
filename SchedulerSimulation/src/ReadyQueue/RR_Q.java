package ReadyQueue;

import Processes.process;

import java.util.LinkedList;

/**
 * this object is constructed in the RR algorithm subclass of the scheduler.
 */
public class RR_Q extends SimpleQ {

    public RR_Q(){
        super();
        Q = new LinkedList<process>();
    }

}
