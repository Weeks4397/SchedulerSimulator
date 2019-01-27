package Testing;

import Generators.WorksetGenerator;
import ReadyQueue.*;

public class readyQtest {

    public static void main(String arg[]){

        WorksetGenerator WG = new WorksetGenerator();
        FIFO_Q RQ1 = new FIFO_Q();
        RR_Q RQ2 = new RR_Q();
        SJF_Q RQ3 = new SJF_Q();
        LWC_Q RQ4 = new LWC_Q();
        SRT_Q RQ5 = new SRT_Q();

        for(int i = 0; i <= 5; i++){
            RQ1.add(WG.Workset.get(i));
            RQ2.add(WG.Workset.get(i));
            RQ3.add(WG.Workset.get(i));
            RQ4.add(WG.Workset.get(i));
            RQ5.add(WG.Workset.get(i));

        }
        //TODO make sure all of these ReadyQs are working correctly, not every ReadyQ sorts by run time
        //TODO also we need an isEmpty method for ReadyQ
        for(int i = 0; i <= 5; i++){
            System.out.println(i + ":");
            System.out.println(RQ1.poll().getRunTime());
            System.out.println(RQ2.poll().getRunTime());
            System.out.println(RQ3.poll().getRunTime());
            System.out.println(RQ4.poll().getRunTime());
            System.out.println(RQ5.poll().getRunTime());

        }

    }
}
