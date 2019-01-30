package Testing;

import Processes.ProcessIII;
import Processes.ProcessIV;
import Processes.process;
import Resources.Resource;
import Resources.ResourceA;
import Resources.ResourceB;

public class ResourceBtester {

    public static void main(String[] args) {

        int time = 100;
        int time2 = 110;
        int time3 = 1500;
        ResourceB B = new ResourceB();

        System.out.println(B.toString());

        System.out.println();
        System.out.println();
        System.out.println();

        process P1 = new ProcessIII();
        System.out.println(P1.toString());
        System.out.println();
        System.out.println();
        System.out.println();
        process P2 = new ProcessIV();
        System.out.println(P2.toString());
        System.out.println();
        System.out.println();
        System.out.println();
        process P3 = new ProcessIV();
        System.out.println(P3.toString());

        System.out.println(B.getBlockedList().size());



        System.out.println();
        System.out.println();
        System.out.println();
        B.arrivingProcess(P1, time);
        B.arrivingProcess(P2, time2);
        B.finishService();
        B.finishService();
        B.arrivingProcess(P3, time3);
        B.finishService();

        System.out.println(B.toString());

    }
}

