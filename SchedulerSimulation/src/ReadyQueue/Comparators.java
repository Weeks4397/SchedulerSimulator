package ReadyQueue;

import Processes.process;

import java.util.Comparator;

public class Comparators {

    public static final Comparator<process> By_SJF = new Comparators.BySJF();
    public static final Comparator<process> By_SRT = new Comparators.BySRT();
    public static final Comparator<process> By_LWC = new Comparators.ByLWC();

    //compare based on shortest job
    public static class BySJF implements Comparator<process> {
        public int compare(process P1, process P2) {
            int P1jobTime = P1.getRunTime();
            int P2jobTime = P2.getRunTime();
            if (P1jobTime < P2jobTime) return 1;
            if (P1jobTime > P2jobTime) return -1;
            else return 0;
        }
    }

    //compare based on shortest remaining run time
    public static class BySRT implements Comparator<process> {
        public int compare(process P1, process P2) {
            int P1remainingTime = P1.getRunTime() - P1.getCPUTime();
            int P2remainingTime = P2.getRunTime() - P2.getCPUTime();
            if (P1remainingTime < P2remainingTime) return 1;
            if (P1remainingTime > P2remainingTime) return -1;
            else return 0;
        }
    }

    //compare based on least work completed
    public static class ByLWC implements Comparator<process> {
        public int compare(process P1, process P2) {
            int P1remainingTime = P1.getRunTime() - P1.getCPUTime();
            int P2remainingTime = P2.getRunTime() - P2.getCPUTime();
            if (P1remainingTime > P2remainingTime) return 1;
            if (P1remainingTime < P2remainingTime) return -1;
            else return 0;
        }
    }
}
