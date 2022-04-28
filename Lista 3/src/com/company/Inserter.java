package com.company;

import java.util.NoSuchElementException;

public class Inserter {
    public static TwoWayLinkedList<String> insert(
            TwoWayLinkedList<String> list1,
            TwoWayLinkedList<String> list2,
            int beforeIndex) throws NoSuchElementException {

        if (beforeIndex<0 || beforeIndex>list1.size())
            throw new NoSuchElementException();

        TwoWayLinkedList<String> list3 = list1;

        int tmp_index = beforeIndex;
        for (int i = 0; i < list2.size(); i++) {
            list3.addAt(tmp_index, list2.get(i));
            tmp_index++;
        }

        return list3;
    }

    public static TwoWayLinkedList<String> insert(
            TwoWayLinkedList<String> list1,
            TwoWayLinkedList<String> list2,
            String beforeElement) throws NoSuchElementException {

        if (!list1.contains(beforeElement))
            throw new NoSuchElementException();

        TwoWayLinkedList<String> list3 =  list1;
        int index = list1.indexOf(beforeElement);
        list3 = insert(list1, list2, index);

        return list3;
    }
}
