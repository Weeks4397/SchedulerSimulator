package Resources;

import Processes.process;

import java.util.List;
import java.util.TreeMap;

public class ResourceB extends Resource {


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

        int unblockTime = this.getNextUnblockTime();
        List<process> blockList = null;

        // Check if there are any processes using this resource. If not update the idle time then add process
        if (processMap.isEmpty()) {
            this.updateIdleTime(time - this.StartIdleTime);
            blockList.add(theProcess);
            processMap.put(unblockTime, blockList);

        } else {

            //  Add the process to the map
            if (processMap.containsKey(unblockTime)) {
                // A process is already unblocking at this time so update the list
                blockList = processMap.get(unblockTime);
                blockList.add(theProcess);
                processMap.replace(unblockTime, blockList);
            } else {

                blockList.add(theProcess);
                processMap.put(unblockTime, blockList);
            }
        }

        //  update the NextUnblockTime
        this.updateNextUnblockTime(time + processMap.firstEntry().getKey());
    }

    @Override
    public process finishService () {
        return null;
    }
}
