package com.company;

import com.company.exceptions.EmptyQueueException;
import com.company.exceptions.FullQueueException;
import com.company.exceptions.FullStackException;

import java.util.Stack;

public class Inverter {
    public static <T> IQueue<T> invert(IQueue<T> queue) throws EmptyQueueException, FullQueueException, FullStackException {

        ArrayStack<T> stack = new ArrayStack<>(queue.size());
        TwoWayLinkedListQueue<T> new_queue = new TwoWayLinkedListQueue<>(queue.size());

        while(!queue.isEmpty())
            stack.push(queue.dequeue());

        while (!stack.isEmpty())
            new_queue.enqueue(stack.pop());

        return new_queue;
    }
}
