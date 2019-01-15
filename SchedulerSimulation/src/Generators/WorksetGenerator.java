package Generators;
import java.util.ArrayList;
import Processes.process;

public class WorksetGenerator {

    //Purpose: To create a new list of processes.
    public ArrayList<Process> ProccessList = new ArrayList<Process>();
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
    public int BACount = 0;
    public int BBCount = 0;
    public int BCCount = 0;
    public int BATime = 0;
    public int BBTime = 0;
    public int BCTime = 0;





    //Purpose: To create a new Process.
    public Process makeProcess() {
        int Rnum = RNG.RNG(100);;
        Process P = null;
        if (Rnum <= 49) {
            P = new ProcessType1();
        } else if (Rnum <= 79) {
            P = new ProcessType2();
        } else if (Rnum <= 96) {
            P = new ProcessType3();
        } else if (Rnum <= 99) {
            P = new ProcessType4();
        }
        return P;
    }

    //Purpose: To create the initial work set.
    public void setWorkSet() {
        int numOfStartSet = RNG.RNG(3, 8);
        int i = 0;
        Process P = null;
        while(i <= numOfStartSet) {
            P = makeProcess();
            ProccessList.add(P);
            P.setID(Integer.toString(i));
            P.setType();
            P.setRunTime();
            P.setCPUTime();
            P.setArrivalTime(0);
            P.setblockedTime();
            P.getreadyTime();
            P.setlastEventTime();
            P.setfinishTime();
            P.setblockServiceTime();
            P.setserviceStartTime();
            P.setnextBlockType();
            P.setnextBlockTime();
            P.setNextBlockInstent();
            P.setListOfBlocks();
            P.generateNextBlock();

            if(P.getType() == 1){
                Initial1++;
            }
            else if(P.getType() == 2){
                Initial2++;
            }
            else if(P.getType() == 3){
                Initial3++;
            }
            else if(P.getType() == 4){
                Initial4++;
            }

            totalRunTime += P.getRunTime();
            i++;
        }
    }

    //Purpose: To update the work set.
    public void UpdateWorkSet() {
        setWorkSet();
        Process P = null;
        int i = 0;
        int currentTime = 0;
        Type1Count = Initial1;
        Type2Count = Initial2;
        Type3Count = Initial3;
        Type4Count = Initial4;


        while(totalRunTime <= processGenerator.MAXINT) {
            P = makeProcess();
            ProccessList.add(P);
            currentTime += processGenerator.ProcessArrival();
            P.setID(Integer.toString(i));
            P.setType();
            P.setRunTime();
            P.setCPUTime();
            P.setArrivalTime(currentTime);
            P.setblockedTime();
            P.getreadyTime();
            P.setlastEventTime();
            P.setfinishTime();
            P.setblockServiceTime();
            P.setserviceStartTime();
            P.setnextBlockType();
            P.setnextBlockTime();
            P.setNextBlockInstent();
            P.setListOfBlocks();
            P.generateNextBlock();



            totalRunTime += P.getRunTime();
            i++;
        }
    }

}
