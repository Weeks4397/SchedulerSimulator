package ReadyQueue;

import java.util.*;

import Minheap.MinHeap;
import Processes.process;

public abstract class ReadyQ implements ReadyQInterface{

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
     * checks to see if the ReadyQ isEmpty
     * @return a bool that these you if it is empty or not
     */
    public abstract Boolean isEmpty();

    /**
     * returns how many processes are in the readyQ
     * @return int  the amount of processes in the readyQ
     */
    public abstract int size();

}
