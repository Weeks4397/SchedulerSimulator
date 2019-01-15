package Processes;

import Generators.ProccessGenator;

public class ProcessI extends Process {

//Constructor for a type 1 process
    public ProccessGenator PG = new ProccessGenator(1);

    public ProcessI(String stringID, int type, int runTime, int nextBlockInstant, String nextBlockResource,
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
