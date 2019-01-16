package Resources;

public class ResourceA extends Resource {
    //setters for ResourceA objects
    public void setType() {
        this.type = "A";
    }

    public void setBlockedProcessesDS() {
        this.blockedProcessesDS = new BlockedProcesses();
        this.blockedProcessesDS.setBlockedProcessesExclusive();
    }

    public void setExclusive() {
        this.exclusive = true;
    }

    //additional methods

    public Process finishService() {
        int oldTime = this.nextUnblockTime;		// current process finish time—for legibility
        Process oldProcess = this.servingProcess;

        this.updateActiveTime(oldProcess.totalBlockTimeForNextBlock);

        if (!this.blockedProcessesDS.isEmptyExclusive()) {

            Process theProcess = this.blockedProcessesDS.getNextProcessExclusive();
            this.nextUnblockTime = oldTime + theProcess.totalBlockTimeForNextBlock;
            this.servingProcess = theProcess;
            theProcess.serviceStartTime = oldTime;
            this.numOfBlocks += 1;
            this.totalBlockTime += theProcess.totalBlockTimeForNextBlock;
        }
        else {
            this.nextUnblockTime = RNG.MAXINT;
            this.servingProcess = null;
            this.startIdleTime = oldTime;
        }
        oldProcess.currentListIndex += 1;
        if (oldProcess.currentListIndex <= oldProcess.maxListIndex) {
            BlockList nextBlock = oldProcess.blockRecord.get(oldProcess.currentListIndex);
            oldProcess.nextBlockInstant = nextBlock.getBI();
            oldProcess.nextBlockResource = nextBlock.getR();
            oldProcess.totalBlockTimeForNextBlock = nextBlock.getBT();
        }
        else {
            oldProcess.nextBlockInstant = RNG.MAXINT;
            oldProcess.nextBlockResource = null;
            oldProcess.totalBlockTimeForNextBlock = 0;
        }
        return oldProcess;

    }

    public void arrivingProcess (Process theProcess, int time) {
        //theProcess.blockedTime =
        if (blockedProcessesDS.isEmptyExclusive()) {
            this.servingProcess = theProcess;
            theProcess.serviceStartTime = time;
            this.updateIdleTime (time - this.startIdleTime);
            this.nextUnblockTime = (time + theProcess.totalBlockTimeForNextBlock);
        }
        else {
            blockedProcessesDS.addExclusive(theProcess);		// insert at end
        }
    }
}
