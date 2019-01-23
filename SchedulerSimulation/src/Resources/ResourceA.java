package Resources;
import Processes.process;
import Processes.Block;

public class ResourceA extends Resource {
    //setters for ResourceA objects
    public void setType() {
        this.type = "A";
    }


    public void setExclusive() {
        this.exclusive = true;
    }

    //additional methods

    public process finishService() {
        int oldTime = this.nextUnblockTime;		// current process finish time—for legibility
        process oldProcess = this.servingProcess;

        this.updateActiveTime(oldProcess.getNextBlockTime());

        if (!this.BlockedProcessQ.isEmpty()) {

            process theProcess = this.BlockedProcessQ.remove();
            this.nextUnblockTime = oldTime + theProcess.getNextBlockTime();
            this.servingProcess = theProcess;
            theProcess.ServiceStartTime = oldTime;
            this.numOfBlocks += 1;
            this.totalBlockTime += theProcess.getNextBlockTime();
        }
        else {
            this.nextUnblockTime = Integer.MAX_VALUE;
            this.servingProcess = null;
            this.startIdleTime = oldTime;
        }

        oldProcess.BlockServiceTime += oldTime - oldProcess.getServiceStartTime();
        oldProcess.BlockWaitTime += oldProcess.getServiceStartTime() + oldProcess.getNextBlockTime() - oldProcess.getGlobalBlockInstant();

        if (oldProcess.CurrentListIndex < oldProcess.MaxListIndex) {
            Block nextBlock = oldProcess.getBlockRecord().get(oldProcess.getCurrentListIndex() + 1);
            oldProcess.NextBlockInstant = nextBlock.getBI();
            oldProcess.NextBlockResource = nextBlock.getR();
            oldProcess.NextBlockTime = nextBlock.getBT();
            oldProcess.CurrentListIndex += 1;
        }
        else {
            oldProcess.NextBlockInstant = Integer.MAX_VALUE;
            oldProcess.NextBlockResource = null;
            oldProcess.NextBlockTime = 0;
        }
        return oldProcess;

    }

    public void arrivingProcess (process theProcess, int time) {
        theProcess.updateGlobalBlockInstant(time);
        if (this.BlockedProcessQ.isEmpty()) {
            this.servingProcess = theProcess;
            theProcess.ServiceStartTime = time;
            this.updateIdleTime (time - this.startIdleTime);
            this.nextUnblockTime = (time + theProcess.getNextBlockTime());
        }
        else {
            this.BlockedProcessQ.add(theProcess);		// insert at end of queue
        }
    }
}
