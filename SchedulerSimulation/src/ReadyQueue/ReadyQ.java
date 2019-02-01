package ReadyQueue;

import java.util.*;
import Processes.process;

/**
 * is the ReadyQ to be used in the scheduler
 * can be either a standard queue or a priority queue
 */
public abstract class ReadyQ {

    /**
     * priQ is used as the ReadyQ for some of the schedulers Algorithms(sjf, srt, lwc)
     */
    public MinHeap<process> PriQ;

    /**
     * Q is used as the ReadyQ for some of the schedulers Algorithms(Fifo, RR)
     */
    public Queue<process> Q;


    /**
     * super Constructor for ReadyQ
     */
    public ReadyQ(){
    }

    /**
     * looks at the first elem of the list based on what list is being used
     * @return the first elem of the list
     */
    public abstract process peek();

    /**
     * removes the first elem of the Q
     * @return the first elem of the Q
     */
    public abstract process poll();

    /**
     * adds the elem to the back of the Q
     * @param elem is a process that will be added to the Q
     */
    public abstract void add(process elem);

    /**
     * checks to see if Queue is empty
     * @return Boolean  true or false
     */
    public abstract Boolean isEmpty();

}
