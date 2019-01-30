package ReadyQueue;

import Processes.process;

import java.util.LinkedList;

public class FIFO_Q extends SimpleQ{

    public FIFO_Q(){
        super();
        Q = new LinkedList<process>();
    }
}
