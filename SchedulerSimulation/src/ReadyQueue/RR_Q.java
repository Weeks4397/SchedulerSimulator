package ReadyQueue;

import Processes.process;

import java.util.LinkedList;

public class RR_Q extends SimpleQ {

    public RR_Q(){
        super();
        Q = new LinkedList<process>();
    }

}
