package core;

import adt.PriorityQueueInterface;

/**
 * Max Priority Queue implementation based on a binary heap.
 * Stores Disk objects and orders them based on their free space.
 */
public class MaxPQ implements PriorityQueueInterface {

    private Disk[] heap;     // heap array (1-based indexing)
    private int size;        // number of elements in the heap

    private static final int DEFAULT_CAPACITY = 2;

    /**
     * Constructs an empty Max Priority Queue.
     */
    public MaxPQ() {
        heap = new Disk[DEFAULT_CAPACITY];
        size = 0;
    }

    /**
     * Inserts a Disk into the priority queue.
     */
    public void add(Disk item) {
        if (size == heap.length - 1) {
            grow();
        }
        heap[++size] = item;
        swim(size);
    }

    /**
     * Returns the maximum element without removing it.
     */
    public Disk peek() {
        if (size == 0) {
            return null;
        }
        return heap[1];
    }

    /**
     * Removes and returns the maximum element.
     */
    public Disk getMax() {
        if (size == 0) {
            return null;
        }

        Disk max = heap[1];
        swap(1, size);
        heap[size] = null;
        size--;
        sink(1);

        return max;
    }

    /**
     * Returns the number of elements in the queue.
     */
    public int size() {
        return size;
    }

    /**
     * Helper method to restore heap order upwards.
     */
    private void swim(int i) {
        while (i > 1 && heap[i].compareTo(heap[i / 2]) > 0) {
            swap(i, i / 2);
            i = i / 2;
        }
    }

    /**
     * Helper method to restore heap order downwards.
     */
    private void sink(int i) {
        while (2 * i <= size) {
            int j = 2 * i;
            if (j < size && heap[j].compareTo(heap[j + 1]) < 0) {
                j++;
            }
            if (heap[i].compareTo(heap[j]) >= 0) {
                break;
            }
            swap(i, j);
            i = j;
        }
    }

    /**
     * Swaps two elements in the heap.
     */
    private void swap(int i, int j) {
        Disk temp = heap[i];
        heap[i] = heap[j];
        heap[j] = temp;
    }

    /**
     * Doubles the heap capacity.
     */
    private void grow() {
        Disk[] newHeap = new Disk[heap.length * 2];
        for (int i = 1; i <= size; i++) {
            newHeap[i] = heap[i];
        }
        heap = newHeap;
    }
}
