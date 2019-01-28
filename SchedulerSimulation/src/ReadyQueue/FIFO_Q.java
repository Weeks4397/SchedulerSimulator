package ReadyQueue;

import Processes.process;

import java.util.LinkedList;

public class FIFO_Q extends ReadyQ{


    public FIFO_Q(){
        super();
        Q = new LinkedList<process>();
    }
    @Override
    public process peek() {
        process peekVal = null;
        peekVal = Q.peek();
        return peekVal;
    }

    @Override
    public process poll() {
        process pollVal = null;
        pollVal = Q.poll();
        return pollVal;
    }

    @Override
    public void add(process elem) {
        Q.add(elem);
    }

    @Override
    public Boolean isEmpty() {
        return Q.isEmpty();
    }
}
