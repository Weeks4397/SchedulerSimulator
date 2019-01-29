package Resources;
import Processes.process;

/**
 * UnBlockTimePair pairs a process with an unblock time.
 * These objects are constructed in ResourceB to keep track of when each process will unblock.
 */
public class UnblockTimePair {
    /**
     * Data members
     */
    /**
     * The time the process will unblock
     */
    private int NextUnblockTime;
    /**
     * process that is arriving to the resource
     */
    private process P;

    public UnblockTimePair(process process, int UnblockTime){
        NextUnblockTime = UnblockTime;
        P = process;
    }

    /**
     *getters for UnblockTimePairs
     */
    public process getP(){
        return P;
    }
    public int getNextUnblockTime(){
        return NextUnblockTime;
    }
}
