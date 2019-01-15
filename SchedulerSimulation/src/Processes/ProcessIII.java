package Processes;

public class ProcessIII extends Process{

    //Constructor for a type 2 process

    public ProcessIII(String stringID, int type, int runTime, int nextBlockInstant, String nextBlockResource,
                     int nextBlockTime, List<Block> blockRecord, int currentListIndex, int maxListIndex,
                     int arrivalTime, int CPUTime, int nextReadyTime, int lastEventTime, int finishTime,
                     int blockServiceTime, int serviceStartTime) {
        super(stringID, arrivalTime, CPUTime, nextReadyTime, lastEventTime, finishTime, blockServiceTime, serviceStartTime);
        this.Type = type;
        this.RunTime = runTime;
        this.NextBlockInstant = nextBlockInstant;
        this.NextBlockResource = nextBlockResource;
        this.NextBlockTime = nextBlockTime;
        this.BlockRecord = blockRecord;
        this.CurrentListIndex = currentListIndex;
        this.MaxListIndex = maxListIndex;
    }
}
