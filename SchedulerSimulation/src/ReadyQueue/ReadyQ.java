package ReadyQueue;

import java.util.*;
import Processes.process;

/**
 * is the ReadyQ to be used in the scheduler
 */
public class ReadyQ {

    /**
     * priQ is used as the ReadyQ for some of the schedulers Algorithms
     */
    public MinHeap<process> PriQ;
    /**
     * Q is used as the ReadyQ for some of the schedulers Algorithms
     */
    public Queue<process> Q;
    /**
     * is the TYpe of Q that is being used
     */
    public String QType;

    /**
     * Sets the Ready Q that will be used
     * @param Type is the TYpe of Algorithm that the Q will be used for
     */
    public ReadyQ(String Type){
        QType = Type;
        if(QType.equals("FIFO")){
            Q = new LinkedList<process>();
        }
        else if(QType.equals("RR")){
            Q = new LinkedList<process>();
        }
        else if(QType.equals("SJF")){
            PriQ = new MinHeap<process>(100, Comparators.By_SJF);
        }
        else if(QType.equals("LWC")){
            PriQ = new MinHeap<process>(100, Comparators.By_LWC);
        }
        else if(QType.equals("SRT")){
            PriQ = new MinHeap<process>(100, Comparators.By_SRT);
        }
    }

    /**
     * looks at the first elem of the list based on what list is being used
     * @return the first elem of the list
     */
    public process Peek(){
        process peekVal = null;
        if(QType.equals("FIFO")){
            peekVal = Q.peek();
        }
        else if(QType.equals("RR")){
            peekVal = Q.peek();
        }
        else if(QType.equals("SJF")){
            peekVal =PriQ.peek();
        }
        else if(QType.equals("LWC")){
            peekVal = PriQ.peek();
        }
        else if(QType.equals("SRT")){
            peekVal = PriQ.peek();
        }
        return peekVal;
    }

    /**
     * removes the first elem of the Q
     * @return the first elem of the Q
     */
    public process Poll(){
        process pollVal = null;
        if(QType.equals("FIFO")){
            pollVal = Q.poll();
        }
        else if(QType.equals("RR")){
            pollVal = Q.poll();
        }
        else if(QType.equals("SJF")){
            pollVal =PriQ.poll();
        }
        else if(QType.equals("LWC")){
            pollVal = PriQ.poll();
        }
        else if(QType.equals("SRT")){
            pollVal = PriQ.poll();
        }
        return pollVal;
    }

    /**
     * adds the elem to the back of the Q
     * @param elem is a process that will be added to the Q
     */
    public void add(process elem){
        if(QType.equals("FIFO")){
            Q.add(elem);
        }
        else if(QType.equals("RR")){
            Q.add(elem);
        }
        else if(QType.equals("SJF")){
            PriQ.add(elem);
        }
        else if(QType.equals("LWC")){
            PriQ.add(elem);
        }
        else if(QType.equals("SRT")) {
            PriQ.add(elem);
        }
    }

}
