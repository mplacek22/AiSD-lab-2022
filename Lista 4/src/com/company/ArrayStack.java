package com.company;

import com.company.exceptions.FullStackException;

import java.util.EmptyStackException;
import java.util.Stack;

public class ArrayStack<T> implements IStack<T>{

    T array[];
    int topIndex;

    public ArrayStack(int capacity) {
        array=(T[])(new Object[capacity]);
        topIndex=0;
    }

    @Override
    public boolean isEmpty() {
        return topIndex==0;
    }

    @Override
    public boolean isFull() {
        return topIndex==array.length;
    }

    @Override
    public T top() throws EmptyStackException {
        if(isEmpty())
            throw new EmptyStackException();
        return array[topIndex-1];
    }

    @Override
    public T pop() throws EmptyStackException {
        if(!isEmpty())
            return array[--topIndex];
        throw new EmptyStackException();
    }

    @Override
    public void push(T value) throws FullStackException {
        if(isFull())
            throw new FullStackException();
        array[topIndex++]=value;
    }

    @Override
    public int size() {
        return topIndex;
    }
}
