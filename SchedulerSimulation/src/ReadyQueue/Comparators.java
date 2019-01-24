package ReadyQueue;

import Processes.process;

import java.util.Comparator;

/**The Comparators class serves as a comparator interface for processes.
 * A comparator object of type process is generated for each algorithm.
 * Each comparator object has one method called compare that takes in two processes and compares them.
 * This compare method is based on the algorithm
 */
public class Comparators {

    /**These are the comparator objects which will be used to construct the appropriate priority queue
     * in the ReadyQueue class.
     */
    public static final Comparator<process> By_SJF = new Comparators.BySJF();
    public static final Comparator<process> By_SRT = new Comparators.BySRT();
    public static final Comparator<process> By_LWC = new Comparators.ByLWC();

    /**Generate comparator object for shortest job first algorithm.
     *
     */
    public static class BySJF implements Comparator<process> {
        /**compare compares the processes based on RunTime which is equal to the length of the job
         * @param P1    the first process
         * @param P2    the second process
         * @return int  1 if P1 < P2 , -1 if P1 > P2, or 0 if they are equal
         */
        public int compare(process P1, process P2) {
            int P1jobTime = P1.getRunTime();
            int P2jobTime = P2.getRunTime();
            if (P1jobTime < P2jobTime) return 1;
            if (P1jobTime > P2jobTime) return -1;
            else return 0;
        }
    }

    /**Generate comparator object for shortest remaining time algorithm.
     *
     */
    public static class BySRT implements Comparator<process> {
        /**compare compares the processes based on shortest remaining time
         * shortest remaining time is equal to (RunTime - CPUTime)
         * @param P1    the first process
         * @param P2    the second process
         * @return int  1 if P1 < P2 , -1 if P1 > P2, or 0 if they are equal
         */
        public int compare(process P1, process P2) {
            int P1remainingTime = P1.getRunTime() - P1.getCPUTime();
            int P2remainingTime = P2.getRunTime() - P2.getCPUTime();
            if (P1remainingTime < P2remainingTime) return 1;
            if (P1remainingTime > P2remainingTime) return -1;
            else return 0;
        }
    }

    /**Generate comparator object for least work completed algorithm.
     *
     */
    public static class ByLWC implements Comparator<process> {
        /**compare compares the processes based on least work completed
         * least work completed is equal to (RunTime - CPUTime)
         * this is the opposite of shortest remaining time.
         * @param P1    the first process
         * @param P2    the second process
         * @return int  -1 if P1 < P2 , 1 if P1 > P2, or 0 if they are equal
         */
        public int compare(process P1, process P2) {
            int P1remainingTime = P1.getRunTime() - P1.getCPUTime();
            int P2remainingTime = P2.getRunTime() - P2.getCPUTime();
            if (P1remainingTime > P2remainingTime) return 1;
            if (P1remainingTime < P2remainingTime) return -1;
            else return 0;
        }
    }
}
