package com.company;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class OneWayLinkedList<T> implements IList<T> {

    private class Element{
        private T value;
        private Element next;

        public T getValue() {
            return value;
        }

        public void setValue(T value) {
            this.value = value;
        }

        public Element getNext() {
            return next;
        }

        public void setNext(Element next) {
            this.next = next;
        }

        Element (T data){
            this.value=data;
        }
    }

    Element head = null;

    @Override
    public void add(T value) {
        Element newElement = new Element(value);
        if (head==null){
            head = new Element(value);
            return;
        }
        newElement.setNext(null);
        Element tail = head;
        while (tail.next != null)
            tail = tail.next;
        tail.next = newElement;

    }

    @Override
    public void addAt(int index, T value) throws NoSuchElementException {
        if(index<0)
            throw  new NoSuchElementException();
        Element newElement = new Element(value);
        if(index==0) {
            newElement.setNext(head);
            head=newElement;
        }
        else {
            Element currentElement = getElement(index-1);
            newElement.setNext(currentElement.getNext());
            currentElement.setNext(newElement);
        }
    }

    @Override
    public void clear() {
        head = null;
    }

    @Override
    public boolean contains(T value) {
        return indexOf(value) >= 0;
    }

    @Override
    public T get(int index) throws NoSuchElementException {
        if (index < 0 || index > size())
            throw new NoSuchElementException();
        Element currentElement=head;
        while(index>0 && currentElement!=null){
            index--;
            currentElement=currentElement.getNext();
        }
        return currentElement.getValue();
    }

    public Element getElement(int index){
        if (index<0)
            throw new NoSuchElementException();
        Element currentElement = head;
        while (index>0 && currentElement!=null){
            index--;
            currentElement=currentElement.getNext();
        }
        return currentElement;
    }

    @Override
    public void set(int index, T value) throws NoSuchElementException {
        if (index<0)
            throw new NoSuchElementException();
        getElement(index).setValue(value);
    }

    @Override
    public int indexOf(T value) {
        int pos = 0;
        Element currentElement = head;
        while (currentElement!=null){
            if (currentElement.getValue().equals(value))
                return pos;
            pos++;
            currentElement=currentElement.getNext();
        }
        return -1;
    }

    @Override
    public boolean isEmpty() {
        return head==null;
    }

    @Override
    public T removeAt(int index) throws NoSuchElementException {
        T retVal;
        if (index<0 || index>=size())
            throw new NoSuchElementException();
        if (index==0){
            retVal = head.getValue();
            head = head.getNext();
            return retVal;
        }

        Element currentElement = getElement(index-1);
        retVal = currentElement.getNext().getValue();
        currentElement.setNext(currentElement.getNext().getNext());
        return retVal;
    }

    @Override
    public boolean remove(T value) {
        Element currentElement = head;
        Element previousElement = null;

        if (currentElement != null && currentElement.getValue().equals(value)){
            head = currentElement.getNext();
            return true;
        }

        while (currentElement != null && !currentElement.getValue().equals(value)){
            previousElement = currentElement;
            currentElement = currentElement.getNext();
        }

        if (currentElement == null)
            return false;

        previousElement.setNext(currentElement.getNext());
        return true;
    }

    @Override
    public int size() {
        int count = 0;
        Element currentElement = head;
        while (currentElement!=null){
            count++;
            currentElement = currentElement.getNext();
        }
        return count;
    }

    @Override
    public void print() {
        Iterator it = iterator();
        while (it.hasNext())
            System.out.print(it.next().toString()+"\t");
    }

    @Override
    public Iterator<T> iterator() {
        return new OneWayLinkedListIterator();
    }

    private class OneWayLinkedListIterator implements Iterator<T> {

        Element currentElement = head;

        @Override
        public boolean hasNext() {
            return currentElement != null;
        }

        @Override
        public T next() {
            if (hasNext()){
                T value = currentElement.getValue();
                currentElement = currentElement.getNext();
                return value;
            }
            else
                throw new NoSuchElementException();
        }
    }
}
