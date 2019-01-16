package ReadyQueue;

import java.util.*;
import Processes.process;

public class ReadyQ {

    public MinHeap<process> PriQ;
    public Queue<process> Q;
    public String QType;

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
