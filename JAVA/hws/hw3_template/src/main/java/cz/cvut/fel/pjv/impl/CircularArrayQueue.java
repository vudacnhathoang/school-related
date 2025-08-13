package cz.cvut.fel.pjv.impl;

import cz.cvut.fel.pjv.Queue;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Implementation of the {@link Queue} backed by fixed size array.
 */
public class CircularArrayQueue implements Queue {
    // TODO: Implement the homework here
    int capacity;
    int readIndex = 0;
    int writeIndex = 0;
    String[] queue;
    /**
     * Creates the queue with capacity set to the value of 5.
     */
    public CircularArrayQueue() {
            this.capacity = 5 +1 ;
            this.queue = new String[this.capacity];
    }

    /**
     * Creates the queue with given {@code capacity}. The capacity represents maximal number of elements that the
     * queue is able to store.
     * @param capacity of the queue
     */
    public CircularArrayQueue(int capacity) {
            this.capacity = capacity+ 1;
            this.queue = new String[this.capacity];
    }

    @Override
    public int size() {
        return (writeIndex - readIndex + capacity) % capacity ;
    }

    @Override
    public boolean isEmpty() {
        if (readIndex == writeIndex)
            return true;
        else
            return false;
    }

    @Override
    public boolean isFull() {
       if ((writeIndex + 1) % capacity == readIndex)
           return true;
       else
           return false;
    }

    @Override
    public boolean enqueue(String obj) {
        if (isFull()|| obj == null)
            return false;
        else {
            queue[writeIndex] = obj;
            writeIndex = (writeIndex + 1) % (capacity);
            return true;
        }

    }

    @Override
    public String dequeue() {
        if (isEmpty())
            return null;
        else {
            String res = queue[readIndex];
            queue[readIndex] = null;
            readIndex = (readIndex + 1) % capacity;
            return res;
        }


    }

    @Override
    public Collection<String> getElements() {
        List<String> elements = new ArrayList<>();
        for (int i = 0; i < size(); i++) {
            int index = (readIndex + i) % capacity;
            elements.add(queue[index]);
        }
        return elements;
    }

    @Override
    public void printAllElements() {
        for (int i = readIndex; i < writeIndex; i++){
            System.out.println(queue[i]);
        }

    }
}
