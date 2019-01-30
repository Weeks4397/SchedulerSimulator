package Testing;

import Processes.ProcessIII;
import Processes.ProcessIV;
import Processes.process;
import Resources.Resource;
import Resources.ResourceA;

public class ResourceAtester {

    public static void main(String[] args) {

       int time = 100;
       int time2 = 1500;
        ResourceA A = new ResourceA();

        System.out.println(A.toString());

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
        A.arrivingProcess(P1, time);
        A.finishService();
        A.arrivingProcess(P2, time2);
        A.finishService();

        System.out.println(A.toString());

    }
}
