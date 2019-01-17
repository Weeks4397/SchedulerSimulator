package ReadyQueue;
import java.util.*;
//TODO: recommit
public class MinHeap<T> {

        // The number of processes currently inside the heap
        private int heapSize = 0;

        // The capacity of the heap
        private int heapCapacity = 0;

        // A list to track the processes inside the heap
        private List<T> heap = null;

        public Comparator<T> algObj;

        // Construct a priority queue with an initial capacity and a comparator object to enable comparison of processes
        public MinHeap(int size, Comparator<T>alg) {
            heap = new ArrayList<T>(size);
            algObj = alg;
        }

        //check to see if the priority queue is empty
        public boolean isEmpty() {
            return heapSize == 0;
        }

        // Return the size of the heap
        public int size() {
            return heapSize;
        }

        //check the next in priority process in the heap
        //if there heap is empty, return null
        public T peek() {
            if (isEmpty()) {
                return null;
            }
            else return heap.get(0);
        }

        // Removes the root of the heap
        public T poll() {
            return removeAt(0);
        }


        // Adds a process to the priority queue
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

        // Tests if the value of node i <= node j
        private boolean less(int i, int j) {
            T node1 = heap.get(i);
            T node2 = heap.get(j);
            int val = algObj.compare(node1, node2);
            return val == 1;
        }

        // Perform heap up towards the top of the tree
        private void heapUp(int k) {

            int parent = (k-1)/2;

            while (k > 0 && less(k, parent)) {
                swap(parent, k);
                k = parent;

                parent = (k-1) / 2;
            }
        }

        // Perform heap down towards the bottom of tree
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

        // Swap two nodes
        private void swap(int i, int j) {
            T elem_i = heap.get(i);
            T elem_j = heap.get(j);

            heap.set(i, elem_j);
            heap.set(j, elem_i);
        }


        // Removes a node at i index
        private T removeAt(int i) {
            if (isEmpty()) return null;

            heapSize--;
            T removed_data = heap.get(i);
            swap(i, heapSize);
            heap.set(heapSize, null);

            // Check if the last element was removed
            if (i == heapSize) return removed_data;
            //P is the process now at the root that will most likely be violating the heap invariant
            T P = heap.get(i);

            // Try heap down on the process to satisfy the invariant
            heapDown(i);

            // try heap up on the process
            if (heap.get(i).equals(P))
                heapUp(i);
            return removed_data;
        }

}
