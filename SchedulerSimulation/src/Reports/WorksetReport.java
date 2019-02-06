package Reports;

import Generators.WorksetGenerator;
import Processes.process;
import Processes.Block;

import java.util.*;

/**
 * The WorksetReport class provides methods to print out report variables from a workset generator object
 * as well as information specific to each process in a formatted fashion.
 */
public class WorksetReport {

    /**Print out how many processes created total and how many of each type were created
     * takes in data members of work set generator object
     *
     * @param total     total amount of processes generated
     * @param T1C       total amount of type 1 processes generated
     * @param T2C       total amount of type 2 processes generated
     * @param T3C       total amount of type 3 processes generated
     * @param T4C       total amount of type 4 processes generated
     */
    public static void PrintNumProcesses(int total, int T1C, int T2C, int T3C, int T4C) {

        System.out.println(String.format("%d processes generated, %d type1, %d type2, %d type3, and %d type4 ",
                total, T1C, T2C, T3C, T4C));
    }

    /**labels1   ID, Type, Arrive, Running, Block List, Total Blocked
     *
     * @return String   a formatted label
     */
    public static String Labels1() {
        return String.format("%s %10s %10s %10s %15s %48s", "ID", "Type", "Arrive", "Running", "Block List", "Total Blocked");
    }

    /** prints information from process objects based on labels
     * @param Processes the master list of processes
     */
    public static void PrintProcessInfo(List<process> Processes) {
        for (int i = 0; i < Processes.size(); i++) {
            //P is the process we are currently reporting on
            process P = Processes.get(i);

            //get variables from P
            String id = P.getStringID();
            int type = P.getType();
            int arrive = P.getArrivalTime();
            int RT = P.getRunTime();

            //format those variables for printing
            System.out.format("%-10s %-8d %-8d %-10d  ",id, type, arrive, RT);

            //TBT is the total block time for the process
            int TBT = 0;

            //LB is a shallow copy of the block record for reporting purposes
            Queue<Block> LB = Processes.get(i).getBlockRecord();

            //Now begin to format and print the block record and TBT if necessary
            if (LB == null) {
                System.out.print("null");
                System.out.print("                                               " + 0);
            }
            else {
                //format each block into "(BI, R, BT)"

                //Initalize an array LBarr that will be populated with blocks formatted into strings
                String[] LBarr = new String[LB.size()+1];

                //The first block has already been polled and is no longer in queue
                //b1 is the first block formatted into a string.
                String b1 = String.format("(%d, %s, %d)", P.getNextBlockInstant(),
                        P.getNextBlockResource(), P.getNextBlockTime());
                LBarr[0] = b1;
                TBT += P.getNextBlockTime();

                //j is the index of LBarr, starts at 1 because we alrady have first block formatted in LBarr
                int j = 1;
                //BlockRecordNew is a new block record to maintain the original block record during reporting
                Queue<Block> BlockRecordNew = new LinkedList<Block>();
                while(!LB.isEmpty()){
                    //B is the next block in the block record
                    Block B = LB.poll();
                    int BI = B.getBI();
                    String R = B.getR();
                    int BT = B.getBT();

                    TBT += BT;

                    //b_string is the next block formatted into a string
                    String b_string = String.format("(%d, %s, %d)", BI, R, BT);
                    LBarr[j] = b_string;
                    j++;
                    BlockRecordNew.add(B);
                }
                //Update the block record back to its orignial form
                Processes.get(i).BlockRecord = BlockRecordNew;


                //LBarr has been totally populated
                //Next Figure out if there will be one, two, or thre blocks on the first line to properly format first line
                //L1, L2, and L3 are the 1st, 2nd, and 3rd block strings respectively
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
            //The entire line has been printed, now go to next line
            System.out.println();
        }
    }

    /**
     * Labels2   Running Time	  Blocks and Blocked Time per Resource 			  Total Blocking
     * @return  String  A formatted label
     */
    public static String Labels2() {
        return String.format("%-20s %-65s %s", "Running Time", "Blocks and Blocked Time per Resource", "Total Blocking");
    }

    /**
     * Labels3   Total	    	  A   time	       B   time	       C   time	       Total    time
     * @return  String  A formatted label
     */
    public static String Labels3() {
        return String.format("%-20s %-20s %-20s %-23s %s", "   Total", "A        time", "B        time", "C        time", "Total       time");
    }

    /**
     * Print information for labels 2 and 3
     * takes in data members from workset generator object
     * @param totalRun  total run time of all processes summed
     * @param Acount    total count of how many times resource A was used
     * @param Bcount    total count of how many times resource B was used
     * @param Ccount    total count of how many times resource C was used
     * @param Atime     total time processes spent being served by A
     * @param Btime     total time processes spent being served by B
     * @param Ctime     total time processes spent being served by C
     * @param totalResource  total count of how many times all resources used
     * @param totalTime     total time processes spent being served by all resources
     */
    public static void totalInfo(int totalRun, int Acount, int Bcount, int Ccount, int Atime,
                                   int Btime, int Ctime, int totalResource, int totalTime ) {
        System.out.println(String.format( "   %-17d %-8d %-11d %-8d %-11d %-8d %-15d %-10d %d",
                totalRun, Acount, Atime, Bcount, Btime, Ccount, Ctime, totalResource, totalTime));
    }

    /**
     *  Label4   Average Interarrival Time (not counting initial process set at time 0)
     * @return  String  a formatted label
     */
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
     *ReportWorkSet calls the other static methods in this class and serves
     * as a final static method to report the initial workset which is generated in the
     * worsetGenerator class
     *
     * @param WSG   init workset
     */
    public static void ReportWorkSet (WorksetGenerator WSG){
       PrintNumProcesses(WSG.totalNumProcesses, WSG.Type1Count, WSG.Type2Count, WSG.Type3Count, WSG.Type4Count);
        System.out.println();
        System.out.println(Labels1());
        WorksetReport.PrintProcessInfo(WSG.Workset);
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
        PrintInterarrivalTime(WSG.totalNumProcesses, WSG.initCount, WSG.FinalAT);

        //reset the static variables

    }





}
