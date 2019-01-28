package Resources;
import Processes.process;

public class unBlockTimePair {
    private int nextunBlockTime;
    private process P;

    public unBlockTimePair(process process, int unBlockTime){
        nextunBlockTime = unBlockTime;
        P = process;
    }

    public process getP(){
        return P;
    }
    public int getnextunBlockTime(){
        return nextunBlockTime;
    }
}
