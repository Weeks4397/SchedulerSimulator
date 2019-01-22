package Testing;

import Generators.WorksetGenerator;
import ReadyQueue.ReadyQ;

public class readyQtest {

    public static void main(String arg[]){

        WorksetGenerator WG = new WorksetGenerator();
        ReadyQ RQ1 = new ReadyQ("FIFO");
        ReadyQ RQ2 = new ReadyQ("RR");
        ReadyQ RQ3 = new ReadyQ("SJF");
        ReadyQ RQ4 = new ReadyQ("LWC");
        ReadyQ RQ5 = new ReadyQ("SRT");

        for(int i = 0; i <= 5; i++){
            RQ1.add(WG.Workset.get(i));
            RQ2.add(WG.Workset.get(i));
            RQ3.add(WG.Workset.get(i));
            RQ4.add(WG.Workset.get(i));
            RQ5.add(WG.Workset.get(i));
        }
        for(int i = 0; i <= 5; i++){
            System.out.println(i + ":");
            System.out.println(RQ1.Poll().getRunTime());
            System.out.println(RQ2.Poll().getRunTime());
            System.out.println(RQ3.Poll().getRunTime());
            System.out.println(RQ4.Poll().getRunTime());
            System.out.println(RQ5.Poll().getRunTime());
        }

    }
}
