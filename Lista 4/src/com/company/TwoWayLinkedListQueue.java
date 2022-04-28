package com.company;

import com.company.exceptions.*;
import com.company.list.TwoWayLinkedList;

import java.util.LinkedList;
import java.util.Queue;

public class TwoWayLinkedListQueue<T> implements IQueue<T> {

    TwoWayLinkedList<T> list;
    int capacity;
    
    public TwoWayLinkedListQueue(int capacity) {
        list = new TwoWayLinkedList<>();
        this.capacity = capacity;
    }

    @Override
    public boolean isEmpty() {
        return list.isEmpty();
    }

    @Override
    public boolean isFull() {
        return list.size()==capacity;
    }

    @Override
    public void enqueue(T value) throws FullQueueException {
        if (isFull())
            throw new FullQueueException();
        list.add(value);
    }

    @Override
    public T first() throws EmptyQueueException {
        if(this.isEmpty())
            throw new EmptyQueueException();
        return list.get(0);
    }

    @Override
    public T dequeue() throws EmptyQueueException {
        if(this.isEmpty())
            throw new EmptyQueueException();
        return list.removeAt(0);
    }

    @Override
    public int size() {
        return list.size();
    }
}
