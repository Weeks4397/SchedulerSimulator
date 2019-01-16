package Generators;
import java.util.ArrayList;
import Processes.*;

/**
 * Generators a work set to be used in scheduler
 */
public class WorksetGenerator {

    /**
     *  P1, P2, P3, and P4 are the percentages that each process will be created
     */
    private int P1 = 50;
    private int P2 = 30;
    private int P3 = 17;
    private int P4 = 3;


    /**
     * this the list of processes that will be used in the scheduler
     */
    public ArrayList<process> Workset = new ArrayList<process>();
    /**
     * is the total runtime for all the processes in the WorkSet
     */
    public int totalRunTime = 0;
    /**
     * is the number of initial processes generated with arrival time 0
     */
    public int initCount = 0;
    /**
     * is the number of type 1 process in the final set of processes
     */
    public int Type1Count = 0;
    /**
     * is the number of type 2 process in the final set of processes
     */
    public int Type2Count = 0;
    /**
     * is the number of type 3 process in the final set of processes
     */
    public int Type3Count = 0;
    /**
     * is the number of type 4 process in the final set of processes
     */
    public int Type4Count = 0;
    /**
     * is the number of type A Resources in the final set of processes
     */
    public int RACount = 0;
    /**
     * is the number of type B Resources in the final set of processes
     */
    public int RBCount = 0;
    /**
     * is the number of type C Resources in the final set of processes
     */
    public int RCCount = 0;
    /**
     * is the total runtime for all type A Resources
     */
    public int BATime = 0;
    /**
     * is the total runtime for all type B Resources
     */
    public int BBTime = 0;
    /**
     * is the total runtime for all type C Resources
     */
    public int BCTime = 0;
    /**
     * is the arrival time for the last process
     */
    public int FinalAT = 0;
    /**
     * is the total count of the amount of processes so far in WorkSet
     */
    //TODO change name of i
    public int totalNumProcesses = 0;

    /**
     * is the constructor for WorksetGenerator and loads the workSet for use
     */
    public WorksetGenerator(){
        setWorkSet();
        UpdateWorkSet();
    }


    /**
     * creates a process to be put in the WorkSet based on a random number
     * @return process based on type of process
     */
    public process makeProcess() {
        int Rnum = RNG.RNG(100);
        process P = null;
        if (Rnum < P1) {
            P = new ProcessI();
        } else if (Rnum < P1+P2) {
            P = new ProcessII();
        } else if (Rnum < P1+P2+P3) {
            P = new ProcessIII();
        } else if (Rnum < P1+P2+P3+P4) {
            P = new ProcessIV();
        }
        return P;
    }

    /**
     * create the initial work set. all Process with arrival time of 0
     */
    public void setWorkSet() {
        int numOfStartSet = RNG.RNG(3, 8);
        process P = null;
        while(totalNumProcesses <= numOfStartSet) {
            P = makeProcess();
            Workset.add(P);
            P.setArrivalandReadyTime(0);
            totalNumProcesses++;
            P.setStringID( totalNumProcesses , 1000);

            if(P.getType() == 1){
                Type1Count++;
                if(P.getNextBlockResource() == null){

                }
                else if (P.getNextBlockResource() == "A"){
                    RACount++;
                    BATime += P.getNextBlockTime();
                }
                else if (P.getNextBlockResource() == "B"){
                    RBCount++;
                    BBTime += P.getNextBlockTime();
                }
            }
            else if(P.getType() == 2){
                Type2Count++;
            }
            else if(P.getType() == 3){
                Type3Count++;
                for (int j = 0; j < P.getBlockRecord().size(); j++){
                    if (P.getBlockRecord().get(j).getR() == "A"){
                        RACount++;
                        BATime += P.getBlockRecord().get(j).getBT();
                    }
                    else if (P.getBlockRecord().get(j).getR() == "B"){
                        RBCount++;
                        BBTime += P.getBlockRecord().get(j).getBT();
                    }
                    else if (P.getBlockRecord().get(j).getR() == "C"){
                        RCCount++;
                        BCTime += P.getBlockRecord().get(j).getBT();
                    }

                }
            }
            else if(P.getType() == 4) {
                Type4Count++;
                for (int j = 0; j < P.getBlockRecord().size(); j++) {
                    if (P.getBlockRecord().get(j).getR() == "B") {
                        RBCount++;
                        BBTime += P.getBlockRecord().get(j).getBT();
                    } else if (P.getBlockRecord().get(j).getR() == "C") {
                        RCCount++;
                        BCTime += P.getBlockRecord().get(j).getBT();
                    }
                }
            }

            totalRunTime += P.getRunTime();
            initCount++;
        }
    }

    /**
     * loads the rest of the process ending when Runtime is bigger the MAXINT
     */
    public void UpdateWorkSet() {
        process P = null;
        int currentTime = 0;


        while(totalRunTime <= ProccessGenator.MAXINT) {
            P = makeProcess();
            Workset.add(P);
            currentTime += ProccessGenator.ProcessArrival();
            P.setArrivalandReadyTime(currentTime);
            totalNumProcesses++;
            P.setStringID( totalNumProcesses , 1000);

            if(P.getType() == 1){
                Type1Count++;
                if(P.getNextBlockResource() == null){

                }
                else if (P.getNextBlockResource() == "A"){
                    RACount++;
                    BATime += P.getNextBlockTime();
                }
                else if (P.getNextBlockResource() == "B"){
                    RBCount++;
                    BBTime += P.getNextBlockTime();
                }
            }
            else if(P.getType() == 2){
                Type2Count++;
            }
            else if(P.getType() == 3){
                Type3Count++;
                for (int j = 0; j < P.getBlockRecord().size(); j++){
                    if (P.getBlockRecord().get(j).getR() == "A"){
                        RACount++;
                        BATime += P.getBlockRecord().get(j).getBT();
                    }
                    else if (P.getBlockRecord().get(j).getR() == "B"){
                        RBCount++;
                        BBTime += P.getBlockRecord().get(j).getBT();
                    }
                    else if (P.getBlockRecord().get(j).getR() == "C"){
                        RCCount++;
                        BCTime += P.getBlockRecord().get(j).getBT();
                    }

                }
            }
            else if(P.getType() == 4) {
                Type4Count++;
                for (int j = 0; j < P.getBlockRecord().size(); j++) {
                    if (P.getBlockRecord().get(j).getR() == "B") {
                        RBCount++;
                        BBTime += P.getBlockRecord().get(j).getBT();
                    } else if (P.getBlockRecord().get(j).getR() == "C") {
                        RCCount++;
                        BCTime += P.getBlockRecord().get(j).getBT();
                    }
                }
            }

            totalRunTime += P.getRunTime();
        }
        FinalAT = currentTime;
        ProccessGenator.MAXINT = currentTime;
    }

}
