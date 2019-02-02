package Reports;

import Generators.WorksetGenerator;
import Processes.*;
import Schedulers.*;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Driver {

    public static void main(String[] args) {

        process p1 = new ProcessI();
        process p2 = new ProcessII();
        process p3 = new ProcessIII();
        process p4 = new ProcessIV();
        process p5 = new ProcessIII();

        List<process> Parr =new ArrayList<process>();
        Parr.add(p1);
        Parr.add(p2);
        Parr.add(p3);
        Parr.add(p4);
        Parr.add(p5);
        p1.updateArrivalTime(0);
        p2.updateArrivalTime(0);
        p3.updateArrivalTime(0);
        p4.updateArrivalTime(100);
        p5.updateArrivalTime(500);
        for(int i = 0; i < Parr.size(); i++){
            Parr.get(i).updateStringID(String.format("P%d", i+1));
            System.out.println(Parr.get(i));
            Queue<Block> temp = new LinkedList<>();
            System.out.print("The Block record: ");
            while(!Parr.get(i).BlockRecord.isEmpty()){
                Block theBlock = Parr.get(i).BlockRecord.poll() ;
                System.out.print(theBlock + " ");
                temp.add(theBlock);
            }
            Parr.get(i).BlockRecord = temp;
            System.out.println();
        }



        Scheduler_FIFO FIFO = new Scheduler_FIFO(Parr);
        Scheduler_RR RR = new Scheduler_RR(Parr);
        Scheduler_SJF SJF = new Scheduler_SJF(Parr);
        Scheduler_SRT SRT = new Scheduler_SRT(Parr);
        Scheduler_LWC LWC = new Scheduler_LWC(Parr);

        FIFO.runAlgorithm();
        RR.runAlgorithm();
        SJF.runAlgorithm();
        SRT.runAlgorithm();
        LWC.runAlgorithm();
        System.out.println();
        System.out.println();
        System.out.println();

        System.out.println("--------------------------------------");
        System.out.println(FIFO);

        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println(FIFO.FinishedQ.poll().toString());
        System.out.println();
        System.out.println(FIFO.FinishedQ.poll().toString());
        System.out.println();
        System.out.println(FIFO.FinishedQ.poll().toString());
        System.out.println();
        System.out.println(FIFO.FinishedQ.poll().toString());
        System.out.println();
        System.out.println(FIFO.FinishedQ.poll().toString());


        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println("--------------------------------------");
        System.out.println(RR);
        System.out.println();
        System.out.println();
        System.out.println(RR.FinishedQ.poll().toString());
        System.out.println();
        System.out.println(RR.FinishedQ.poll().toString());
        System.out.println();
        System.out.println(RR.FinishedQ.poll().toString());
        System.out.println();
        System.out.println(RR.FinishedQ.poll().toString());
        System.out.println();
        System.out.println(RR.FinishedQ.poll().toString());

        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println("--------------------------------------");
        System.out.println(SJF);
        System.out.println();
        System.out.println();
        System.out.println(SJF.FinishedQ.poll().toString());
        System.out.println();
        System.out.println(SJF.FinishedQ.poll().toString());
        System.out.println();
        System.out.println(SJF.FinishedQ.poll().toString());
        System.out.println();
        System.out.println(SJF.FinishedQ.poll().toString());
        System.out.println();
        System.out.println(SJF.FinishedQ.poll().toString());
        System.out.println("--------------------------------------");
        System.out.println(SRT);
        System.out.println();
        System.out.println();
        System.out.println(SRT.FinishedQ.poll().toString());
        System.out.println();
        System.out.println(SRT.FinishedQ.poll().toString());
        System.out.println();
        System.out.println(SRT.FinishedQ.poll().toString());
        System.out.println();
        System.out.println(SRT.FinishedQ.poll().toString());
        System.out.println();
        System.out.println(SRT.FinishedQ.poll().toString());
        System.out.println("--------------------------------------");
        System.out.println(LWC);
        System.out.println();
        System.out.println();
        System.out.println(LWC.FinishedQ.poll().toString());
        System.out.println();
        System.out.println(LWC.FinishedQ.poll().toString());
        System.out.println();
        System.out.println(LWC.FinishedQ.poll().toString());
        System.out.println();
        System.out.println(LWC.FinishedQ.poll().toString());
        System.out.println();
        System.out.println(LWC.FinishedQ.poll().toString());
    }
}
