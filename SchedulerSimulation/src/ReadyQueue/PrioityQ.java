package ReadyQueue;

import Processes.process;

public abstract class PrioityQ extends ReadyQ {

    public PrioityQ(){
        super();
    }
    @Override
    public process peek() {
        process peekVal = null;
        peekVal =PriQ.peek();
        return peekVal;
    }

    @Override
    public process poll() {
        process pollVal = null;
        pollVal = PriQ.poll();
        return pollVal;
    }

    @Override
    public void add(process elem) {
        PriQ.add(elem);
    }

    public Boolean isEmpty() {
        return PriQ.isEmpty();
    }
}
