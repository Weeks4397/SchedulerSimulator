package Generators;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

import Processes.*;

/**
 * Generators a work set to be used in scheduler and records some information on the work set into variables
 */
public class WorksetGenerator {

    /**
     *  P1, P2, P3, and P4 are the percentages that each type of process could be created
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
     * is the number of type 1 process created at time = 0
     */
    public int initType1Count = 0;

    /**
     * is the number of type 1 process in the final set of processes
     */
    public int Type1Count = 0;

    /**
     * is the number of type 2 process created at time = 0
     */
    public int initType2Count = 0;

    /**
     * is the number of type 2 process in the final set of processes
     */
    public int Type2Count = 0;

    /**
     * is the number of type 3 process created at time = 0
     */
    public int initType3Count = 0;

    /**
     * is the number of type 4 process created at time = 0
     */
    public int initType4Count = 0;

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
     * Create the initial work set.
     * All Process with arrival time of 0
     * There can be between 3 and 8 of these processes.
     */
    public void setWorkSet() {
        int numOfStartSet = RNG.RNG(3, 8);
        process P = null;
        while(totalNumProcesses < numOfStartSet) {
            P = makeProcess();
            Workset.add(P);
            P.setArrivalTime(0);
            totalNumProcesses++;
            P.setStringID( totalNumProcesses , 1000);

            if(P.getType() == 1){
                initType1Count ++;
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
                initType2Count++;
                Type2Count++;
            }
            else if(P.getType() == 3){
                initType3Count++;
                Type3Count++;
                //get the information for the first block and record it
                if (P.getNextBlockResource() == "A"){
                    RACount++;
                    BATime += P.getNextBlockTime();
                }
                else if (P.getNextBlockResource() == "B"){
                    RBCount++;
                    BBTime += P.getNextBlockTime();
                }
                else if (P.getNextBlockResource() == "C"){
                    RCCount++;
                    BCTime += P.getNextBlockTime();
                }

                //shallow copy of the block record for reporting
                Queue<Block> P3BlockRecordCopy = P.getBlockRecord();
                //New queue so original block record is kept in tact, blocks will be added to this queue
                //as they are reported on
                Queue<Block> P3BlockRecordNew = new LinkedList<Block>();
                 while(!P3BlockRecordCopy.isEmpty()){
                    Block currentBlock = P3BlockRecordCopy.poll();
                    if (currentBlock.getR() == "A"){
                        RACount++;
                        BATime += currentBlock.getBT();
                    }
                    else if (currentBlock.getR() == "B"){
                        RBCount++;
                        BBTime += currentBlock.getBT();
                    }
                    else if (currentBlock.getR() == "C"){
                        RCCount++;
                        BCTime += currentBlock.getBT();
                    }
                    P3BlockRecordNew.add(currentBlock);

                }
                 P.BlockRecord = P3BlockRecordNew;
            }
            else if(P.getType() == 4) {
                Type4Count++;
                initType4Count++;

                //get the information for the first block and record it
                if (P.getNextBlockResource() == "B"){
                    RBCount++;
                    BBTime += P.getNextBlockTime();
                }
                else if (P.getNextBlockResource() == "C"){
                    RCCount++;
                    BCTime += P.getNextBlockTime();
                }

                //shallow copy of the block record for reporting
                Queue<Block> P4BlockRecordCopy = P.getBlockRecord();
                //New queue so original block record is kept in tact, blocks will be added to this queue
                //as they are reported on
                Queue<Block> P4BlockRecordNew = new LinkedList<Block>();
                while(!P4BlockRecordCopy.isEmpty()){
                    Block currentBlock = P4BlockRecordCopy.poll();
                    if (currentBlock.getR() == "B") {
                        RBCount++;
                        BBTime += currentBlock.getBT();
                    } else if (currentBlock.getR() == "C") {
                        RCCount++;
                        BCTime += currentBlock.getBT();
                    }
                    P4BlockRecordNew.add(currentBlock);
                }
                P.BlockRecord = P4BlockRecordNew;
            }

            totalRunTime += P.getRunTime();
            initCount++;
        }
    }

    /**
     * loads the rest of the process ending when Runtime is bigger than MAXINT
     */
    public void UpdateWorkSet() {
        process P = null;
        int currentTime = 0;


        while(totalRunTime <= ProccessGenerator.MAXINT) {
            P = makeProcess();
            Workset.add(P);
            currentTime += ProccessGenerator.ProcessArrival();
            P.setArrivalTime(currentTime);
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
                //get the information for the first block and record it
                if (P.getNextBlockResource() == "A"){
                    RACount++;
                    BATime += P.getNextBlockTime();
                }
                else if (P.getNextBlockResource() == "B"){
                    RBCount++;
                    BBTime += P.getNextBlockTime();
                }
                else if (P.getNextBlockResource() == "C"){
                    RCCount++;
                    BCTime += P.getNextBlockTime();
                }

                //shallow copy of the block record for reporting
                Queue<Block> P3BlockRecordCopy = P.getBlockRecord();
                //New queue so original block record is kept in tact, blocks will be added to this queue
                //as they are reported on
                Queue<Block> P3BlockRecordNew = new LinkedList<Block>();
                while(!P3BlockRecordCopy.isEmpty()){
                    Block currentBlock = P3BlockRecordCopy.poll();
                    if (currentBlock.getR() == "A"){
                        RACount++;
                        BATime += currentBlock.getBT();
                    }
                    else if (currentBlock.getR() == "B"){
                        RBCount++;
                        BBTime += currentBlock.getBT();
                    }
                    else if (currentBlock.getR() == "C"){
                        RCCount++;
                        BCTime += currentBlock.getBT();
                    }
                    P3BlockRecordNew.add(currentBlock);

                }
                P.BlockRecord = P3BlockRecordNew;
            }
            else if(P.getType() == 4) {
                Type4Count++;

                //get the information for the first block and record it
                if (P.getNextBlockResource() == "B"){
                    RBCount++;
                    BBTime += P.getNextBlockTime();
                }
                else if (P.getNextBlockResource() == "C"){
                    RCCount++;
                    BCTime += P.getNextBlockTime();
                }

                //shallow copy of the block record for reporting
                Queue<Block> P4BlockRecordCopy = P.getBlockRecord();
                //New queue so original block record is kept in tact, blocks will be added to this queue
                //as they are reported on
                Queue<Block> P4BlockRecordNew = new LinkedList<Block>();
                while(!P4BlockRecordCopy.isEmpty()){
                    Block currentBlock = P4BlockRecordCopy.poll();
                    if (currentBlock.getR() == "B") {
                        RBCount++;
                        BBTime += currentBlock.getBT();
                    } else if (currentBlock.getR() == "C") {
                        RCCount++;
                        BCTime += currentBlock.getBT();
                    }
                    P4BlockRecordNew.add(currentBlock);
                }
                P.BlockRecord = P4BlockRecordNew;
            }

            totalRunTime += P.getRunTime();
        }
        FinalAT = currentTime;
    }

}
