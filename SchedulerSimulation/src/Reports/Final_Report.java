/**
 * Final report is a report class that prints report tables 1-5
 * It is called in the Scheduler_Tester and scheduler_controller class
 */

package Reports;

import Generators.WorksetGenerator;
import Processes.process;
import Resources.Resource;
import Schedulers.Scheduler;
import java.text.DecimalFormat;
import java.util.List;

public class Final_Report {

    /**
     * minThroughput is the smallest throughput of all the processes
     */
    public static double minThroughput = Integer.MAX_VALUE;

    /**
     * minServiceRatio is the smallest service ratio of all the processes
     */
    public static double minServiceRatio = Integer.MAX_VALUE;

    /**
     * final that can be changed to format the report to a certain number of decimal points
     */
    private final static DecimalFormat DF2 = new DecimalFormat(".###");


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
                    BR3 = resource.getTotalBlockTime();
                    TR3 = resource.getNumOfBlocks();
            }
        }

        String msg = String.format("Number blocks for Resource: %d, A: %d,  B: %d,  C: %d", BR1+BR2+BR3, TR1, TR2, TR3);
        String msg2 = String.format("Total BlockTime for Resource: %d,  A: %d,  B: %d,  C: %d",TR1+TR2+TR3, BR1, BR2, BR3);
        System.out.println(msg);
        System.out.println(msg2);
    }

    /**
     * This is the CUP View table for ALL REPORTS BEING GENERATED. It logs the scheduler Algorithm, Service Time, Overhead Time, Idle Time, Finish Time,
     * # of Timeouts, #  of Preempts, Min Throughput, and Min Service Ratio
     * @param schedulerList a list of object type scheduler
     */
    public static void overViewTableAll(List<Scheduler> schedulerList) {

        System.out.println(String.format("%s %20s %20s %20s %20s %20s %20s %20s %20s", "Algorithm", "Service Time", "Overhead Time", "Idle Time", "Finish Time", "# of Timeouts", "#  of Preempts", "Min Throughput", "Min Service Ratio"));

        for (Scheduler scheduler: schedulerList) {

            String type = scheduler.getType();
            while(type.length() < 4){
                type = type+  " ";
            }

            for (process aProcess : scheduler.getFinishedQ()) {
                double residency = aProcess.getFinishTime() - aProcess.getArrivalTime();
                double timeNeeded = aProcess.getRunTime() + aProcess.getBlockServiceTime();
                double delay = aProcess.getFinishTime() - aProcess.getArrivalTime() - timeNeeded;
                double throughput = timeNeeded / residency;
                double serviceRatio = (double) aProcess.getRunTime() /((double) aProcess.getRunTime() + (double) aProcess.getTotalReadyTime());

                // check to see if the throughput or the serviceRatio is less than the global. If so than update the global
                if (throughput < minThroughput)
                    minThroughput = throughput;
                if (serviceRatio < minServiceRatio)
                    minServiceRatio = serviceRatio;
            }

            System.out.println(String.format("%s %20s %20s %22s %20s %18s %20s %20s %17s", type, scheduler.getActiveTime(), scheduler.getOverhead(), scheduler.getIdleTime(), scheduler.getTime(), scheduler.getTimeOut_Count(), scheduler.getPreempt_Count(), DF2.format(minThroughput), DF2.format(minServiceRatio)));
            //reset minThroughput and min ServiceRatio
            minThroughput = Integer.MAX_VALUE;
            minServiceRatio = Integer.MAX_VALUE;
        }
    }

    /**
     * This is the CUP View table. It logs the scheduler Algorithm, Service Time, Overhead Time, Idle Time, Finish Time,
     * # of Timeouts, #  of Preempts, Min Throughput, and Min Service Ratio
     * @param scheduler a scheduler object
     */
    public static void overViewTable1(Scheduler scheduler) {

        System.out.println(String.format("%s %20s %20s %20s %20s %20s %20s %20s %20s", "Algorithm", "Service Time", "Overhead Time", "Idle Time", "Finish Time", "# of Timeouts", "#  of Preempts", "Min Throughput", "Min Service Ratio"));
        System.out.println(String.format("%s %20s %20s %22s %20s %18s %20s %20s %17s", scheduler.getType(), scheduler.getActiveTime(), scheduler.getOverhead(), scheduler.getIdleTime(), scheduler.getTime(), scheduler.getTimeOut_Count(), scheduler.getPreempt_Count(), DF2.format(minThroughput), DF2.format(minServiceRatio)));
        //reset minthroughput and minServiceRatio
        minServiceRatio = Integer.MAX_VALUE;
        minThroughput = Integer.MAX_VALUE;
    }

    /**
     * This table is part of the Process View. It logs the following per process: ProcessID, Type, Arrival, Running Time,
     * Ready Time, Block Service time, Block Wait time, Sched Instants, # of timeouts, # of Preempts, and Finish Time
     * @param scheduler a scheduler object
     */
    public static void overViewTable2(Scheduler scheduler) {
        System.out.println("Algorithm: " + scheduler.getType());
        System.out.println(String.format("%s %20s %20s %20s %20s %20s %20s %20s %20s %20s %20s", "Process", "Type", "Arrival", "Running Time", "Ready Time", "Block Service", "Block Wait", "Sched Instants", "Timeouts", "Preempts", "Finish Time"));

        for(process aProcess: scheduler.getFinishedQ()){
            System.out.println(String.format("%s %21s %20s %18s %20s %18s %20s %20s %22s %20s %20s", aProcess.getStringID(), aProcess.getType(), aProcess.getArrivalTime(), aProcess.getRunTime(), aProcess.getTotalReadyTime(), aProcess.getBlockServiceTime(), aProcess.getBlockWaitTime(), aProcess.getSchedInstant_Count(), aProcess.getTimeOut_Count(), aProcess.getPreempt_Count(), aProcess.getFinishTime()));
        }
    }

    /**
     * This table is part of the Process view. It logs the Process, Residency, Time Needed, Delay, Throughput, Service Ratio
     * - Residency = Finish - Arrival
     * - Time Needed 	= Running Time + Block Service Time
     * - Delay = Finish – Arrival – Total Time
     * - Throughput = Time Needed / Residency
     * - Service Ratio = Running Time / (Running Time + Ready Time)
     * @param scheduler a Scheduler object
     */
    public static void overViewTable4(Scheduler scheduler) {

        System.out.println("Algorithm: " + scheduler.getType());
        System.out.println(String.format("%s %20s %20s %20s %20s %24s", "Process", "Residency", "Time Needed", "Delay", "Throughput", "Service Ratio"));



        for (process aProcess : scheduler.getFinishedQ()) {
            double residency = aProcess.getFinishTime() - aProcess.getArrivalTime();
            double timeNeeded = aProcess.getRunTime() + aProcess.getBlockServiceTime();
            double delay = aProcess.getFinishTime() - aProcess.getArrivalTime() - timeNeeded;
            double throughput = timeNeeded / residency;
            double serviceRatio = (double) aProcess.getRunTime() /((double) aProcess.getRunTime() + (double) aProcess.getTotalReadyTime());

            // check to see if the throughput or the serviceRatio is less then the global. If so then update the global
            if (throughput < minThroughput)
                minThroughput = throughput;
            if (serviceRatio < minServiceRatio)
                minServiceRatio = serviceRatio;

            System.out.println(String.format("%s %20s %20s %22s %17s %22s", aProcess.getStringID(), DF2.format(residency), DF2.format(timeNeeded), DF2.format(delay), DF2.format(throughput), DF2.format(serviceRatio)));

        }

    }


    /**
     * This table is the Resource View. It logs the type of resource, number of requests per resource, total service time per resource,
     * total idle time per resource, and max list length per resource
     * @param scheduler
     */
    public static void overViewTable5(Scheduler scheduler) {
        System.out.println("Algorithm: " + scheduler.getType());
        System.out.println(String.format("%s %20s %20s %20s %20s", "Resource", "Number of Requests", "Total Service Time", "Total Idle Time", "Max List Length"));

        for (Resource aResource: scheduler.getTheResources()){
            System.out.println(String.format("%s %20s %20s %20s %20s", aResource.getType(), aResource.getNumOfBlocks(), aResource.getActiveTime(), aResource.getIdleTime(), aResource.getMaxCount()));
        }
    }


    /**
     * Creates a report for a single Scheduler
     * @param WSG a WorksetGenerator that was used in the scheduler
     * @param scheduler a Scheduler object
     */
    public static void CreateReport(WorksetGenerator WSG, Scheduler scheduler) {
        System.out.println("*** Process Set Characteristics ***");
        tableA(WSG);
        System.out.println();
        tableB(scheduler);
        System.out.println();
        System.out.println();
        System.out.println("*** Process View ***");
        System.out.println();
        overViewTable2(scheduler);
        System.out.println();
        overViewTable4(scheduler);
        System.out.println();
        System.out.println();
        System.out.println("*** Resource View ***");
        overViewTable5(scheduler);
        System.out.println();
        System.out.println();
        System.out.println("*** CPU View ***");
        overViewTable1(scheduler);
    }


    /**
     * Creates a report for all schedulers when given a list of schedulers
     * @param WSG a WorksetGenerator that was used in all the schedulers in the list
     * @param schedulerList a list of Schedulers
     */
    public static void createAll(WorksetGenerator WSG, List<Scheduler> schedulerList) {

        for (Scheduler scheduler : schedulerList){

            /*
            System.out.println("*** Process Set Characteristics ***");
            tableA(WSG);
            System.out.println();
            tableB(scheduler);
            System.out.println();
            System.out.println();
            System.out.println("*** Process View ***");
            System.out.println();
            overViewTable2(scheduler);
            System.out.println();
            overViewTable4(scheduler);
            System.out.println();
            System.out.println();

            */
            System.out.println("*** Resource View ***");
            overViewTable5(scheduler);
            System.out.println();
            System.out.println();
        }
        System.out.println("*** CPU View ***");
        overViewTableAll(schedulerList);
    }

}
