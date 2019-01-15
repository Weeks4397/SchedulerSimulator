package Processes;

public class ProcessI extends Process {

//Constructor for a type 1 process

    public ProcessI(String stringID, int type, int runTime, int nextBlockInstant, String nextBlockResource,
                    int nextBlockTime, List<Block> blockRecord, int currentListIndex, int maxListIndex,
                    int arrivalTime, int CPUTime, int nextReadyTime, int lastEventTime, int finishTime,
                    int blockServiceTime, int serviceStartTime) {
        super(stringID, type, runTime, nextBlockInstant, nextBlockResource, nextBlockTime,
                blockRecord, currentListIndex, maxListIndex, arrivalTime, CPUTime, nextReadyTime,
                lastEventTime, finishTime, blockServiceTime, serviceStartTime);
        this.type = 1;
        this.RunTime = runTime;
        this.BlockRecord =
    }
}
