package Generators;
import java.util.ArrayList;
import Processes.*;

public class WorksetGenerator {

    /**
     * this the list of processes that will be used in the scheduler
     */
    public ArrayList<process> Workset = new ArrayList<process>();
    /**
     * is the total runtime for all the processes in the WorkSet
     */
    public int totalRunTime;
    /**
     * is the number of type 1 process in the initial set of processes
     */
    public int Initial1 = 0;
    /**
     * is the number of type 2 process in the initial set of processes
     */
    public int Initial2 = 0;
    /**
     * is the number of type 3 process in the initial set of processes
     */
    public int Initial3 = 0;
    /**
     * is the number of type 4 process in the initial set of processes
     */
    public int Initial4 = 0;
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
     * is the total count of the amount of processes so for in WorkSet
     */
    //TODO change name of i
    public int i = 0;

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
        if (Rnum <= 49) {
            P = new ProcessI();
        } else if (Rnum <= 79) {
            P = new ProcessII();
        } else if (Rnum <= 96) {
            P = new ProcessIII();
        } else if (Rnum <= 99) {
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
        while(i <= numOfStartSet) {
            P = makeProcess();
            Workset.add(P);
            P.setArrivalandReadyTime(0);
            P.setStringID( i , 1000);

            if(P.getType() == 1){
                Initial1++;
                if(makeProcess().getNextBlockResource() == null){

                }
                else if (P.getNextBlockResource().equals("A")){
                    RACount++;
                    BATime += P.getNextBlockTime();
                }
                else if (P.getNextBlockResource().equals("B")){
                    RBCount++;
                    BBTime += P.getNextBlockTime();
                }

            }
            else if(P.getType() == 2){
                Initial2++;
            }
            else if(P.getType() == 3){
                Initial3++;
                for (int j = 0; j > P.getBlockRecord().size(); j++){
                   if (P.getBlockRecord().get(j).getR().equals("A")){
                        RACount++;
                        BATime += P.getBlockRecord().get(j).getBT();
                   }
                   else if (P.getBlockRecord().get(j).getR().equals("B")){
                        RBCount++;
                        BBTime += P.getBlockRecord().get(j).getBT();
                   }
                   else if (P.getBlockRecord().get(j).getR().equals("C")){
                       RCCount++;
                       BCTime += P.getBlockRecord().get(j).getBT();
                   }

                }
            }
            else if(P.getType() == 4){
                Initial4++;
                for (int j = 0; j > P.getBlockRecord().size(); j++){
                   if (P.getBlockRecord().get(j).getR().equals("B")){
                        RBCount++;
                        BBTime += P.getBlockRecord().get(j).getBT();
                    }
                    else if (P.getBlockRecord().get(j).getR().equals("C")){
                        RCCount++;
                        BCTime += P.getBlockRecord().get(j).getBT();
                    }

                }
            }

            totalRunTime += P.getRunTime();
            i++;
        }
    }

    /**
     * loads the rest of the process ending when Runtime is bigger the MAXINT
     */
    public void UpdateWorkSet() {
        setWorkSet();
        process P = null;
        int currentTime = 0;
        Type1Count = Initial1;
        Type2Count = Initial2;
        Type3Count = Initial3;
        Type4Count = Initial4;


        while(totalRunTime <= ProccessGenator.MAXINT) {
            P = makeProcess();
            Workset.add(P);
            currentTime += ProccessGenator.ProcessArrival();
            P.setArrivalandReadyTime(currentTime);
            P.setStringID( i , 1000);

            if(P.getType() == 1){
                Type1Count++;
                if(makeProcess().getNextBlockResource() == null){

                }
                else if (P.getNextBlockResource().equals("A")){
                    RACount++;
                    BATime += P.getNextBlockTime();
                }
                else if (P.getNextBlockResource().equals("B")){
                    RBCount++;
                    BBTime += P.getNextBlockTime();
                }
            }
            else if(P.getType() == 2){
                Type2Count++;
            }
            else if(P.getType() == 3){
                Type3Count++;
                for (int j = 0; j > P.getBlockRecord().size(); j++){
                    if (P.getBlockRecord().get(j).getR().equals("A")){
                        RACount++;
                        BATime += P.getBlockRecord().get(j).getBT();
                    }
                    else if (P.getBlockRecord().get(j).getR().equals("B")){
                        RBCount++;
                        BBTime += P.getBlockRecord().get(j).getBT();
                    }
                    else if (P.getBlockRecord().get(j).getR().equals("C")){
                        RCCount++;
                        BCTime += P.getBlockRecord().get(j).getBT();
                    }

                }
            }
            else if(P.getType() == 4){
                Type4Count++;
                for (int j = 0; j > P.getBlockRecord().size(); j++){
                    if (P.getBlockRecord().get(j).getR().equals("B")){
                        RBCount++;
                        BBTime += P.getBlockRecord().get(j).getBT();
                    }
                    else if (P.getBlockRecord().get(j).getR().equals("C")){
                        RCCount++;
                        BCTime += P.getBlockRecord().get(j).getBT();
                    }

                }
            }

            totalRunTime += P.getRunTime();
            i++;
        }
    }

}
