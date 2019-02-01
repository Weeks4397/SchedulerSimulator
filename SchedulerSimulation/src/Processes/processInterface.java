package Processes;

import java.util.Queue;

/**
 * A Process contains a set of numbers, a queue of blocks, and a string for the next resource.
 * A process is updated and tracked through the scheduler to provide performance data in the simulation.
 * A Process can be in one of five states:
 *          MasterList: The process is on the Workset list awaiting arrival to the ready queue
 *          Ready:      The process is in the ready queue
 *          Running:    The proceses is recieving time with the CPU to complete its run time
 *          Blocked:    The process is either waiting for or being served by a resource
 *          Finished:   The process has finished its needed time with the cpu (RunTime - CPUTime == 0)
 * A process can be of 4 different types, TypeI, TypeII, TypeIII, or Type IV.
 *
 */
public interface processInterface {
    /**Sets the StringID
     * If string id is over max limit set to "-1"
     * @param i  keeps track of current processID index
     * @param iMax  the maximum amount of process IDs available
     */
    void setStringID(int i, int iMax);


    /**
     * GotoNextBlock    goes to the next block in the block record if there is one
     * if not set block variables appropriately
     */
    void GotoNextBlock();

    /**
     * toString formats important information about process into a string for debugging purposes
     */
    String toString();

    /**
     * cloneProcess makes a deep copy of a given process
     * @return process  a deep copy of the given process
     */
    process cloneProcess();


}
