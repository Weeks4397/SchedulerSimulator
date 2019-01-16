package Schedulers;

import Generators.WorksetGenerator;
import Processes.process;
import Processes.Block;

import java.util.*;

/**
 * The Report class provides methods to print out report variables from a workset generator object
 * as well as information specific to each process in a formatted fashion.
 */
public class Report {

    //Print out how many processes created total and how many of each type were created
    //takes in data members of work set generator object
    public static void PrintNumProcesses(int total, int T1C, int T2C, int T3C, int T4C){

        System.out.println(String.format("%d processes generated, %d type1, %d type2, %d type3, and %d type4 ",
                total, T1C, T2C, T3C, T4C));
    }

    //labels1   ID, Type, Arrive, Running, Block List, Total Blocked
    public static String Labels1() {
        return String.format("%s %10s %10s %10s %15s %48s", "ID", "Type", "Arrive", "Running", "Block List", "Total Blocked");
    }

    /** prints information from process objects based on labels
     * @param Processes the master list of processes
     */
    public static void PrintProcessInfo(List<process> Processes) {
        for (int i = 0; i < Processes.size(); i++) {
            String id = Processes.get(i).getStringID();
            int type = Processes.get(i).getType();
            int arrive = Processes.get(i).getArrivalTime();
            int RT = Processes.get(i).getRunTime();

            System.out.format("%-10s %-8d %-8d %-10d  ",id, type, arrive, RT);

            int TBT = 0;
            List<Block> LB = Processes.get(i).getBlockRecord();
            if (LB == null) {
                System.out.print("null");
                System.out.print("                                               " + 0);
            }
            else {
                //format each block into "(BI, R, BT)"
                String[] LBarr = new String[LB.size()];
                for (int j=0; j<LB.size(); j++) {
                    Block B = LB.get(j);
                    int BI = B.getBI();
                    String R = B.getR();
                    int BT = B.getBT();

                    TBT += BT;

                    String blist = String.format("(%d, %s, %d)", BI, R, BT);
                    LBarr[j] = blist;
                }
                //Figure out if there will be one, two, or thre blocks on the first line to properly format first line
                String L1 = "";
                String L2 = "";
                String L3 = "";
                String firstLine = "";

                if (LBarr.length >= 1) {
                    L1 = LBarr[0];
                }
                if (LBarr.length >=2 ){
                    L2= LBarr[1];
                }
                if (LBarr.length >=3) {
                    L3 = LBarr[2];
                }
                if (LBarr.length == 1){
                    firstLine = String.format("%s", L1);
                }
                if (LBarr.length == 2) {
                    firstLine = String.format("%s %s", L1, L2);
                }
                if (LBarr.length >= 3) {
                    firstLine = String.format("%s %s %s", L1, L2, L3);
                }
                //print 3 blocks max per line
                for (int k=0; k<LBarr.length; k++) {
                    if(k>0 && k%3==0) {
                        System.out.println();
                        System.out.print("                                         ");
                    }
                    if((LBarr.length == 1 && k==0) || (LBarr.length == 2 && k==1) || (LBarr.length >= 3 && k==2)) {
                        System.out.format("%-50s %d", firstLine, TBT);
                    }
                    if(k>2) {
                        System.out.print(LBarr[k] + " ");
                    }
                }
            }
            System.out.println();
        }
    }

    //Labels2   Running Time	  Blocks and Blocked Time per Resource 			  Total Blocking
    public static String Labels2() {
        return String.format("%-20s %-65s %s", "Running Time", "Blocks and Blocked Time per Resource", "Total Blocking");
    }

    //Labels3   Total	    	  A   time	       B   time	       C   time	       Total    time
    public static String Labels3() {
        return String.format("%-20s %-20s %-20s %-23s %s", "   Total", "A        time", "B        time", "C        time", "Total       time");
    }

    //Print information for labels 2 and 3
    //takes in data members from workset generator object
    public static void totalInfo(int totalRun, int Acount, int Bcount, int Ccount, int Atime,
                                   int Btime, int Ctime, int totalResource, int totalTime ) {
        System.out.println(String.format( "   %-17d %-8d %-11d %-8d %-11d %-8d %-15d %-10d %d",
                totalRun, Acount, Atime, Bcount, Btime, Ccount, Ctime, totalResource, totalTime));
    }

    //Label4   Average Interarrival Time (not counting initial process set at time 0)
    public static String Labels4() {
        return "Average Interarrival Time (not counting initial process set at time 0)";
    }

    /**Print average InterarrivalTime
     *
     * @param totalP    total number of processes
     * @param maxAT     arrival time of last process
     */
    public static void PrintInterarrivalTime(int totalP, int initP, int maxAT) {
        System.out.println (maxAT/(totalP - initP));
    }

    /**
     *
     */
    public static void ReportWorkSet (WorksetGenerator WSG){
       PrintNumProcesses(WSG.i, WSG.Type1Count, WSG.Type2Count, WSG.Type3Count, WSG.Type4Count);
        System.out.println();
        System.out.println(Labels1());
        Report.PrintProcessInfo(WSG.Workset);
        System.out.println();
        System.out.println();
        System.out.println(Labels2());
        System.out.println(Labels3());
        int totalResourceCount = WSG.RACount + WSG.RBCount + WSG.RCCount;
        int totalBlockTime = WSG.BATime + WSG.BBTime + WSG.BCTime;
        totalInfo(WSG.totalRunTime, WSG.RACount, WSG.RBCount, WSG.RCCount, WSG.BATime,
                WSG.BBTime, WSG.BCTime, totalResourceCount, totalBlockTime);
        System.out.println();
        System.out.println(Labels4());
        PrintInterarrivalTime(WSG.i, WSG.initCount, WSG.FinalAT);
    }





}
