package ReadyQueue;

import Processes.process;

/**
 * is the ReadyQ to be used in the scheduler
 * can be either a standard queue or a priority queue
 */
public interface ReadyQInterface {

    /**
     * looks at the first elem of the list based on what list is being used
     * @return the first elem of the list
     */
    process peek();

    /**
     * removes the first elem of the Q
     * @return the first elem of the Q
     */
    process poll();

    /**
     * adds the elem to the back of the Q
     * @param elem is a process that will be added to the Q
     */
    void add(process elem);

    /**
     * checks to see if the ReadyQ isEmpty
     * @return a bool that these you if it is empty or not
     */
    Boolean isEmpty();

}
