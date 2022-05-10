

import java.util.Iterator;
import java.util.NoSuchElementException;

public class TwoWayLinkedList<T> implements IList<T> {

    private class Element{
        private T value;
        private Element next;
        private Element prev;
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
        public Element getPrev() {
            return prev;
        }
        public void setPrev(Element prev) {
            this.prev = prev;
        }
        Element(T data){
            this.value=data;
        }

        public void insertAfter(Element elem){
            elem.setNext(this.getNext());
            elem.setPrev(this);
            this.getNext().setPrev(elem);
            this.setNext(elem);
        }

        public void insertBefore(Element elem){
            elem.setNext(this);
            elem.setPrev(this.getPrev());
            this.getPrev().setNext(elem);
            this.setPrev(elem);
        }

        public void remove(){
            this.getNext().setPrev(this.getPrev());
            this.getPrev().setNext(this.getNext());
        }
    }

    Element sentinel = null;
    public TwoWayLinkedList() {
        sentinel=new Element(null);
        sentinel.setNext(sentinel);
        sentinel.setPrev(sentinel);
    }

    private Element getElement(int index){
        Element tmp = sentinel.getNext();
        int count = 0;
        while (!tmp.equals(sentinel) && count<index){
            tmp = tmp.getNext();
            count++;
        }
        if (tmp.equals(sentinel))
            return null;
        return tmp;
    }

    @Override
    public void add(T value) {
        Element newElement = new Element(value);
        sentinel.insertBefore(newElement);
    }

    @Override
    public void addAt(int index, T value) throws NoSuchElementException {
        if (index < 0 || index>size()+1)
            throw new NoSuchElementException();

        Element newElement = new Element(value);
        if (index==0){
            sentinel.insertAfter(newElement);
        }
        else {
            Element prevElement = getElement(index-1);
            prevElement.insertAfter(newElement);
        }
    }

    @Override
    public void clear() {
        sentinel.setNext(sentinel);
        sentinel.setPrev(sentinel);
    }

    @Override
    public boolean contains(T value) {
        return indexOf(value)!=-1;
    }

    @Override
    public T get(int index) throws NoSuchElementException {
        return getElement(index).getValue();
    }

    @Override
    public void set(int index, T value) throws NoSuchElementException {
        if (index < 0 || index>size()+1)
            throw new NoSuchElementException();
        getElement(index).setValue(value);
    }

    @Override
    public int indexOf(T value) {
        Element tmp = sentinel.getNext();
        int count = 0;
        while (!tmp.equals(sentinel) && !tmp.getValue().equals(value)){
            tmp = tmp.getNext();
            count++;
        }
        if (tmp.equals(sentinel))
            return -1;
        return count;
    }

    @Override
    public boolean isEmpty() {
        return sentinel.getNext().equals(sentinel);
    }

    @Override
    public T removeAt(int index) throws NoSuchElementException {
        if (index < 0 || index>size()+1)
            throw new NoSuchElementException();

        Element elem=getElement(index);
        elem.remove();
        return elem.getValue();
    }

    @Override
    public boolean remove(T value) {
        if (indexOf(value)>=0){
            Element tmp = getElement(indexOf(value));
            tmp.remove();
            return true;
        }
        else
            return false;
    }

    @Override
    public int size() {
        Element tmp = sentinel.getNext();
        int count = 0;
        while (!tmp.equals(sentinel)){
            tmp = tmp.getNext();
            count++;
        }
        return count;
    }

    @Override
    public void print() {
        Element currentElem = sentinel;
        while (currentElem.getNext()!=sentinel){
           System.out.print(currentElem.getNext().getValue()+"\t");
           currentElem = currentElem.getNext();
        }
        System.out.println();
    }

    @Override
    public Iterator<T> iterator() {
        return new TwoWayLinkedListIterator();
    }

    private class TwoWayLinkedListIterator implements Iterator<T> {

        Element currentElem = sentinel;
        @Override
        public boolean hasNext() {
            if (currentElem.getNext().equals(sentinel)){
                return false;
            }
            else
                return true;
        }

        @Override
        public T next() {
            if(!hasNext())
                throw new NoSuchElementException();

            currentElem = currentElem.getNext();
            return currentElem.getValue();
        }
    }
}
