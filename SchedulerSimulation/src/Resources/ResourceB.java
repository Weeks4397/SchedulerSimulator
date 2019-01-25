package Resources;

import Processes.Block;
import Processes.process;

import java.util.List;
import java.util.TreeMap;

/**
 * Recourse B is a Resource class that is an inclusive resource. This means that it can
 * serve multiple processes at the same time.
 */

public class ResourceB extends Resource {

    //TODO: Check Logic, ServiceStartTime in the finishService

    /**
     * The TreeMaps key is the unblock time and the value stored at each key is a List of processes
     * that unblock at the time that the key is
     */
    TreeMap<Integer, List<process>> processMap;

    /**
     * Handles a process arriving for inclusive resource
     * @param theProcess    Process     the process that has arrived at the resource for service
     * @param time     int  The global time at which the process has arrived to the resource
     * @throws IllegalArgumentException if the object given is not of type 'process' or is null
     */
    @Override
    public void arrivingProcess(process theProcess, int time) throws IllegalArgumentException {

        if (theProcess == null){
            throw new IllegalArgumentException("Error: The object given to Resource B is invalid.");
        }

        theProcess.updateGlobalBlockInstant(time);
        //  Update the time that the process will be unblocked at and the ServiceStartTime
        theProcess.ServiceStartTime = time;
        List<process> blockList = null;
        int unBlockTime = theProcess.getNextBlockTime() + time;

        // Check if there are any processes using this resource. If not update the idle time then add process
        if (processMap.isEmpty()) {
            this.updateIdleTime(time - this.StartIdleTime);
            blockList.add(theProcess);
            processMap.put(unBlockTime, blockList);

        } else {

            //  Add the process to the map
            if (processMap.containsKey(unBlockTime)) {
                // A process is already unblocking at this time so update the list
                blockList = processMap.get(unBlockTime);
                blockList.add(theProcess);
                processMap.replace(unBlockTime, blockList);
            } else {

                blockList.add(theProcess);
                processMap.put(unBlockTime, blockList);
            }
        }

        //  update the ServiceStartTime, NextUnblockTime, count
        theProcess.ServiceStartTime = time;
        this.updateNextUnblockTime(time + processMap.firstKey());
        this.Count++;
    }

    /**
     * Handles a process finishing for inclusive resources
     * @return the first process that is unblocked at the given time
     */
    @Override
    public process finishService () {

        // Take the first process out of the process list
        List<process> processList = processMap.firstEntry().getValue();
        process currentProcess = processList.remove(0);
        int oldTime = this.getNextUnblockTime();

        // We know there are not any more processes blocked at this time
        if (processList.isEmpty()) {

            //update Active time by the amount of time the process was served
            this.updateActiveTime(currentProcess.getNextBlockTime());

            // Remove the key from the treeMap
            processMap.remove(processMap.firstEntry());

            // Update the nextUnblockTime, StartIdleTime
            if (!processMap.isEmpty()) {
                this.updateNextUnblockTime(processMap.firstKey());
                this.ServingProcess = processMap.get(processMap.firstKey()).get(0);
            } else {
                this.updateNextUnblockTime(Integer.MAX_VALUE);
                this.StartIdleTime = this.getNextUnblockTime();
                this.ServingProcess = null;
            }

        } else {

            // The unblock time stays the same because there are more processes that need to be returned
            this.ServingProcess = processMap.get(processMap.firstKey()).get(0);
        }

        //increment finishing process's variables to keep track of performance
        currentProcess.BlockServiceTime += oldTime - currentProcess.getServiceStartTime();
        currentProcess.BlockWaitTime += currentProcess.getServiceStartTime() - currentProcess.getGlobalBlockInstant();
        upDateProcessRecords(currentProcess);

        return currentProcess;
    }

    /**
     * A helper function that updates a processes records
     * @param aProcess a process that is of type process
     * @throws IllegalArgumentException if the object given is not of type 'process' or is null
     */
    @SuppressWarnings("Duplicates")
    private void upDateProcessRecords(process aProcess) throws IllegalArgumentException {
        if (aProcess == null){
            throw new IllegalArgumentException("Error: The object given to upDateProcessRecords is invalid.");
        }

        //update the block record of the finishing process to move to the next block if there is one
        //or have process not block again if there is not one.
        if (!aProcess.getBlockRecord().isEmpty()) {
            Block nextBlock = aProcess.BlockRecord.poll();
            aProcess.NextBlockInstant = nextBlock.getBI();
            aProcess.NextBlockResource = nextBlock.getR();
            aProcess.NextBlockTime = nextBlock.getBT();
        }
        else {
            aProcess.NextBlockInstant = Integer.MAX_VALUE;
            aProcess.NextBlockResource = null;
            aProcess.NextBlockTime = 0;
        }
    }
}