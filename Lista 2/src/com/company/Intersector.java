package com.company;


public class Intersector {
    public static OneWayLinkedList<Integer> intersect(
            OneWayLinkedList<Integer> list1,
            OneWayLinkedList<Integer> list2) {

        OneWayLinkedList<Integer> list3 = new OneWayLinkedList<>();

        for (Integer element: list1) {
            if (list2.contains(element))
                list3.add(element);
        }

        return list3;

    }
}
