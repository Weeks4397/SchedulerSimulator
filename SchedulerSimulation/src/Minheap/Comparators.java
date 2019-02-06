package Minheap;

import Processes.process;
import Resources.*;

import java.util.Comparator;

/**The Comparators class serves as a comparator interface for objects.
 * For the ReadyQs:
 *      A comparator object of type process is generated for each algorithm.
 *      Each comparator object has one method called compare that takes in two processes and compares them.
 *      This compare method is based on the algorithm.
 *
 * For ResourceB:
 *      A comparator object of type UnblockTimePair is generated.
 *      This comparator object has one method called compare that takes in two UnblockTimePairs and compares them.
 *      This compare method is based on a key in the UnblockTimePair.
 */
public class Comparators {

    /**These are the comparator objects which will be used to construct the appropriate priority queue
     * in the ReadyQueue class.
     */
    public static final Comparator<process> By_SJF = new Comparators.BySJF();
    public static final Comparator<process> By_SRT = new Comparators.BySRT();
    public static final Comparator<process> By_LWC = new Comparators.ByLWC();
    public static final Comparator<UnblockTimePair> By_UBT = new Comparators.ByUBT();

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
            int P1jobTime = P1.getRunTime() - P1.getCPUTime();
            int P2jobTime = P2.getRunTime() - P2.getCPUTime();
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
         * least work completed is equal to CPUTime
         * @param P1    the first process
         * @param P2    the second process
         * @return int  1 if P1 < P2 , -1 if P1 > P2, or 0 if they are equal
         */
        public int compare(process P1, process P2) {
            int P1work = P1.getCPUTime();
            int P2work = P2.getCPUTime();
            if (P1work < P2work) return 1;
            if (P1work > P2work) return -1;
            else return 0;
        }
    }

    /**Generate comparator object for UnblockTimePair objects constructed in resourceB
     *
     */
    public static class ByUBT implements Comparator<UnblockTimePair> {
        /**compare compares the UnblockTImePairs based on the UnblockTime of each process
         * @param UTP1    the first UnblockTimePair
         * @param UTP2   the second UnblockTimePair
         * @return int  1 if UTP1 < UTP2 , -1 if UTP1 > UTP2, or 0 if they are equal
         */
        public int compare(UnblockTimePair UTP1, UnblockTimePair UTP2){
            int UTP1time = UTP1.getNextUnblockTime();
            int UTP2time = UTP2.getNextUnblockTime();
            if (UTP1time < UTP2time) return 1;
            if (UTP1time > UTP2time) return -1;
            else return 0;
        }
    }
}
