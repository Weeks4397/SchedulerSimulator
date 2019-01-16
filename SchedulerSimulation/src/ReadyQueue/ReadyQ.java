package ReadyQueue;

import java.util.*;
import Processes.process;

public class ReadyQ {

    public MinHeap<process> PriQ;
    public Queue<process> Q;

    public ReadyQ(String Type){
        if(Type.equals("FIFO")){
            Q = new LinkedList<process>();
        }
        else if(Type.equals("RR")){
            Q = new LinkedList<process>();
        }
        else if(Type.equals("SJF")){
            PriQ = new MinHeap<process>(100, Comparators.By_SJF);
        }
        else if(Type.equals("LWC")){
            PriQ = new MinHeap<process>(100, Comparators.By_LWC);
        }
        else if(Type.equals("SRT")){
            PriQ = new MinHeap<process>(100, Comparators.By_SRT);
        }

    }

}
