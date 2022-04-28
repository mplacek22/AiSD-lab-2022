package com.company;

import com.company.exceptions.*;

public interface IQueue<T> {
    boolean isEmpty();
    boolean isFull();
    void enqueue(T value) throws FullQueueException, com.company.exceptions.FullQueueException;
    T first() throws EmptyQueueException, com.company.exceptions.EmptyQueueException;
    T dequeue() throws EmptyQueueException, com.company.exceptions.EmptyQueueException;
    int size();
}
