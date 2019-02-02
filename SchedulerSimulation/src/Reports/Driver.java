package Reports;

import Generators.WorksetGenerator;
import Schedulers.*;

public class Driver {

    public static void main(String[] args) {

        WorksetGenerator Test = new WorksetGenerator();
        WorksetReport.ReportWorkSet(Test);

        Scheduler_FIFO FIFO = new Scheduler_FIFO(Test.Workset);
        Scheduler_RR RR = new Scheduler_RR(Test.Workset);
        Scheduler_SJF SJF = new Scheduler_SJF(Test.Workset);
        Scheduler_SRT SRT = new Scheduler_SRT(Test.Workset);
        Scheduler_LWC LWC = new Scheduler_LWC(Test.Workset);

        //FIFO.runAlgorithm();
      //  RR.runAlgorithm();
     //   SJF.runAlgorithm();
     //   SRT.runAlgorithm();
      //  LWC.runAlgorithm();

        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println();
        WorksetReport.ReportWorkSet(Test);
        System.out.println();
        System.out.println();
        System.out.println();

        //System.out.println(FIFO);
        System.out.println();
        System.out.println();
        System.out.println();
       // System.out.println(RR);
        System.out.println();
        System.out.println();
        System.out.println();
       // System.out.println(SJF);
        System.out.println();
        System.out.println();
        System.out.println();
        //System.out.println(SRT);
        System.out.println();
        System.out.println();
        System.out.println();
        //System.out.println(LWC);
        System.out.println();
        System.out.println();
        System.out.println();
    }
}
