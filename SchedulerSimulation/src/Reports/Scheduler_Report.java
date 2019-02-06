package Reports;

import Generators.WorksetGenerator;
import Processes.process;
import Resources.Resource;
import Schedulers.Scheduler;
import Schedulers.Scheduler_FIFO;

import java.awt.*;

public class Scheduler_Report {


    public static int minThroughput = Integer.MAX_VALUE;
    public static int minServiceRatio = Integer.MAX_VALUE;



    /**
     * Prints the number of process(1, 2, 3, 4) and number of initial processes(1, 2, 3, 4) that a WorksetGenerator creates
     * @param WSG a WorksetGenerator that generates processes for the Scheduler
     */
    public static void tableA(WorksetGenerator WSG){
        String msg = String.format("Total Processes Generated: %d, Type1: %d, Type2: %d, Type3: %d, Type4: %d", WSG.totalNumProcesses, WSG.Type1Count, WSG.Type2Count, WSG.Type3Count, WSG.Type4Count);
        String msg2 = String.format("Initial Processes Generated: %d, Type1: %d, Type2: %d, Type3: %d, Type4: %d", WSG.initCount, WSG.initType1Count, WSG.initType2Count, WSG.initType3Count, WSG.initType4Count);

        System.out.println(msg);
        System.out.println(msg2);
    }

    /**
     * Prints the number of blocks for a Resource(A,B,C) and the total block time for a Resource(A,B,C)
     * @param scheduler a Scheduler
     */
    public static void tableB(Scheduler scheduler) {
        Resource[] resourceList = scheduler.TheResources;
        int TR1 = 0, TR2 = 0 , TR3 = 0;
        int BR1 = 0, BR2 = 0, BR3 = 0;

        for (Resource resource: resourceList) {
            switch (resource.getType()) {
                case "A":
                    BR1 = resource.getTotalBlockTime();
                    TR1 = resource.getNumOfBlocks();
                    break;

                case "B":
                    BR2 = resource.getTotalBlockTime();
                    TR2 = resource.getNumOfBlocks();

                case "C":
                    BR1 = resource.getTotalBlockTime();
                    TR1 = resource.getNumOfBlocks();
            }
        }

        String msg = String.format("Number blocks for Resource: %d, A: %d,  B: %d,  C: %d", BR1+BR2+BR3, BR1, BR2, BR3);
        String msg2 = String.format("Total BlockTime for Resource: %d,  A: %d,  B: %d,  C: %d",TR1+TR2+TR3, TR1, TR2, TR3);
        System.out.println(msg);
        System.out.println(msg2);
    }

    public static void overViewTable1(Scheduler scheduler) {

        System.out.println(String.format("%s %20s %20s %20s %20s %20s %20s %20s %20s", "Algorithm", "Service Time", "Overhead Time", "Idle Time", "Finish Time", "# of Timeouts", "#  of Preempts", "Min Throughput", "Min Service Ratio"));
        System.out.println(String.format("%s %20s %20s %22s %20s %18s %20s %20s %17s", scheduler.getType(), scheduler.getActiveTime(), scheduler.getOverhead(), scheduler.getIdleTime(), scheduler.getTime(), scheduler.getTimeOut_Count(), scheduler.getPreempt_Count(), minThroughput, minServiceRatio));

    }

    public static void overViewTable2(Scheduler scheduler) {
        System.out.println(String.format("%s %20s %20s %20s %20s %20s %20s %20s %20s", "Process", "Type", "Arrival", "Running Time", "Ready Time", "Block Service", "Block Wait", "Sched Instants", "Timeouts", "Preempts", "Finish Time"));
        System.out.println(String.format("%s %20s %20s %22s %20s %18s %20s %20s %17s", scheduler.getType(), scheduler.getActiveTime(), scheduler.getOverhead(), scheduler.getIdleTime(), scheduler.getTime(), scheduler.getTimeOut_Count(), scheduler.getPreempt_Count(), minThroughput, minServiceRatio));


    }


    public static void overViewTable3(Scheduler scheduler) {
    }

    public static void overViewTable4(Scheduler scheduler) {

        System.out.println("Algorithm: " + scheduler.getType());
        System.out.println(String.format("%s %20s %20s %20s %20s %24s", "Process", "Residency", "Time Needed", "Delay", "Throughput", "Service Ratio"));


        for (process aProcess : scheduler.getFinishedQ()) {
            int residency = aProcess.getArrivalTime() - aProcess.getFinishTime();
            int timeNeeded = aProcess.getRunTime() + aProcess.getBlockServiceTime();
            int delay = aProcess.getFinishTime() - aProcess.getArrivalTime() - timeNeeded;
            int throughput = timeNeeded / residency;
            int serviceRatio = aProcess.getRunTime() / (aProcess.getRunTime() + aProcess.getTotalReadyTime());

            // check to see if the throughput or the serviceRatio is less then the global. If so then update the global
            if (throughput < minThroughput)
                minThroughput = throughput;
            if (serviceRatio < minServiceRatio)
                minServiceRatio = serviceRatio;

            System.out.println(String.format("%s %20s %20s %22s %17s %22s", aProcess.getStringID(), residency, timeNeeded, delay, throughput, serviceRatio));

        }

    }

        public static void CreateReport(WorksetGenerator WSG, Scheduler scheduler) {
        System.out.println("*** Process Set Characteristics ***");
        tableA(WSG);
        System.out.println();
        tableB(scheduler);
       // tableB(scheduler);
        System.out.println();
        System.out.println("*** CPU View ***" + "\n");
        System.out.println();
        System.out.println();
        overViewTable4(scheduler);
        System.out.println();
        System.out.println();
        overViewTable1(scheduler);

        }
}
