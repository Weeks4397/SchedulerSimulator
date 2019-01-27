package ReadyQueue;
import java.util.*;

/**
 * The MinHeap class is a generic minheap used for priority queue functionality in the simulation
 * MinHeap is used in ReadyQ and for ResourceB
 * MinHeap invariant: In the BST, each Parent node must be less than its children nodes.
 *                    The BST must remain a complete tree.
 * @param <T>   T   any object that implements comparator using a comparator object that has a method called compare
 */
public class MinHeap<T> {

    /**
     * The number of objects currently inside the heap
     */
    private int heapSize = 0;

    /** The capacity of the heap
     *
     */
    private int heapCapacity = 0;

    /** An arrayList to track the objects inside the heap
     *
     */
    private List<T> heap = null;

    /**
     * Initialize a comparator object to be set in the constructor for the minheap.
     */
        public Comparator<T> CompObj;

    /** Constructor for MinHeap
     *
     * @param size  init capacity
     * @param comp   comparator object to enable comparason of objects in heap
     */
    public MinHeap(int size, Comparator<T>comp) {
            heap = new ArrayList<T>(size);
            CompObj = comp;
        }

    /**check to see if the min heap is empty
     *
     * @return  boolean true if empty, false if not
     */
    public boolean isEmpty() {
            return heapSize == 0;
        }

    /** Return the size of the heap
     *
     * @return  int   amount of objects currently in heap
     */
    public int size() {
            return heapSize;
        }

        /** check the next in priority object in the heap
         * if the heap is empty, return null
         */
        public T peek() {
            if (isEmpty()) {
                return null;
            }
            else return heap.get(0);
        }

    /** Removes the root of the heap
     *
     * @return  T     the next in priority object
     */
    public T poll() {
            return removeAt(0);
        }


    /** Adds an object to the minheap
     *
     * @param elem  the process to be added
     */
    public void add(T elem) {
            if (heapSize < heapCapacity) {
                heap.set(heapSize, elem);
            } else {
                heap.add(elem);
                heapCapacity++;
            }

            heapUp(heapSize);
            heapSize++;
        }

    /** Less tests if the value of node i < node j
     * This is based on the comparator object the minheap was constructed with.
     * This method is used in heapup and heapdown to sort the objects in the array
     *
     * @param i index of an object
     * @param j index of another object
     * @return boolean  True if i < j, false if not
     */
        private boolean less(int i, int j) {
            T node1 = heap.get(i);
            T node2 = heap.get(j);
            int val = CompObj.compare(node1, node2);
            return val == 1;
        }

    /** Perform heap up towards the top of the tree
     *
     * @param k index of an object in the array
     */
    private void heapUp(int k) {
            //index of parent node in the array
            int parent = (k-1)/2;

            while (k > 0 && less(k, parent)) {
                swap(parent, k);
                k = parent;

                parent = (k-1) / 2;
            }
        }

    /** Perform heap down towards the bottom of tree
     *
     * @param k index of an object in the array
     */
    private void heapDown(int k) {
            while (true) {
                int left  = 2 * k + 1; // Left child
                int right = 2 * k + 2; // Right child
                int smallest = left;   // Assume left is the smallest node of the two children

                // Find which is smaller left or right
                // If right is smaller set smallest to be right
                if (right < heapSize && less(right, left))
                    smallest = right;

                // Stop if outside the bounds of the tree
                // or stop early if cannot heap down k anymore
                if (left >= heapSize || less(k, smallest)) break;

                // Move down the tree following the smallest node
                swap(smallest, k);
                k = smallest;
            }
        }

    /** Swap two nodes
     *
     * @param i index of an object
     * @param j index of another object
     */
    private void swap(int i, int j) {
            T elem_i = heap.get(i);
            T elem_j = heap.get(j);

            heap.set(i, elem_j);
            heap.set(j, elem_i);
        }


    /** Removes a node at i index
     * This method was intended for troubleshooting purposes and the functionality is not
     * totally necessary in the scheduler.
     * This method is called by Poll()
     * This method also calls heapDown and heapUp to satisfy the MinHeap invariant
     *
     * @param i index of object
     * @return  the object at index i
     */
    private T removeAt(int i) {
            if (isEmpty()) return null;

            heapSize--;
            T removed_data = heap.get(i);
            swap(i, heapSize);
            heap.set(heapSize, null);

            // Check if the last element was removed
            if (i == heapSize) return removed_data;
            //o is the object now at the root that will most likely be violating the heap invariant
            T o = heap.get(i);

            // Try heap down to satisfy the invariant
            heapDown(i);

            // try heap up to satisfy the invariant
            if (heap.get(i).equals(o))
                heapUp(i);
            return removed_data;
        }

}
