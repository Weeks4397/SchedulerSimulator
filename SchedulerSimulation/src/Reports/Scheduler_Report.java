package Reports;

import Generators.WorksetGenerator;
import Resources.Resource;
import Schedulers.Scheduler;
//import com.sun.tools.javac.util.StringUtils;

public class Scheduler_Report {


    /**
     * Prints the number of process(1, 2, 3, 4) and number of initial processes(1, 2, 3, 4) that a WorksetGenerator creates
     * @param WSG a WorksetGenerator that generates processes for the Scheduler
     */
    public static void tableA(WorksetGenerator WSG){
        String msg = String.format("| Total Processes Generated: %d | Type1: %d | Type2: %d | Type3: %d | Type4: %d |", WSG.totalNumProcesses, WSG.Type1Count, WSG.Type2Count, WSG.Type3Count, WSG.Type4Count);
        String msg2 = String.format("| Initial Processes Generated: %d | Type1: %d | Type2: %d | Type3: %d | Type4: %d |", WSG.initCount, WSG.initType1Count, WSG.initType2Count, WSG.initType3Count, WSG.initType4Count);

        String boarder = getBorder(msg, 1);
        String boarder2 = getBorder(msg2, 1);
        System.out.println(boarder + "\n" + msg + "\n" + boarder);
        System.out.println(boarder2 + "\n" + msg2 + "\n" + boarder2 + "\n");
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

        String msg = String.format("| Number blocks for Resource: %d | A: %d | B: %d | C: %d |", BR1+BR2+BR3, BR1, BR2, BR3);
        String msg2 = String.format("| Total BlockTime for Resource: %d  | A: %d | B: %d | C: %d |",TR1+TR2+TR3, TR1, TR2, TR3);
        String boarder = getBorder(msg, 1);
        String boarder2 = getBorder(msg2, 1);

        System.out.println(boarder + "\n" + msg + "\n" + boarder);
        System.out.println(boarder2 + "\n" + msg2 + "\n" + boarder2 + "\n");
    }

    public static void tableC(Scheduler scheduler) {
        String titles = "| Algorithm | Service Time | Overhead Time | Idle Time | Finish Time | # of Timeouts | #  of Preempts | Min Throughput | Min Service Ratio |";
        System.out.println(getBorder(titles, 1) + "\n" + titles + "\n" + getBorder(titles,2));

    }

    public static void CreateReport(WorksetGenerator WSG, Scheduler scheduler) {
        tableA(WSG);
        tableB(scheduler);
        tableC(scheduler);
    }


    public static String getBorder(String aString, int type){
        String boarder = "";
        for( int i = 0; i < aString.length(); i++) {

            if (type == 1) {
                if (aString.charAt(i) == '|')
                    boarder += "+";
                else
                    boarder += "-";
            }
            else {
                boarder+= "=";
            }
        }
        return boarder;
    }
}
