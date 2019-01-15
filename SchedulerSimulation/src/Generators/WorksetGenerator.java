package Generators;
import java.util.ArrayList;
import Processes.*;

public class WorksetGenerator {

    //Purpose: To create a new list of processes.
    public ArrayList<process> Workset = new ArrayList<process>();
    public int totalRunTime;
    public int Initial1 = 0;
    public int Initial2 = 0;
    public int Initial3 = 0;
    public int Initial4 = 0;
    public int Type1Count = 0;
    public int Type2Count = 0;
    public int Type3Count = 0;
    public int Type4Count = 0;
    public int RACount = 0;
    public int RBCount = 0;
    public int RCCount = 0;
    public int BATime = 0;
    public int BBTime = 0;
    public int BCTime = 0;
    public int i = 0;

    public WorksetGenerator(){
        setWorkSet();
        UpdateWorkSet();

    }



    //Purpose: To create a new Process.
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

    //Purpose: To create the initial work set.
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

    //Purpose: To update the work set.
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
