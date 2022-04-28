package com.company;

import java.util.Iterator;

public class Distincter {
    public static TwoWayLinkedList<Integer> distinct(TwoWayLinkedList<Integer> list)
    {
        TwoWayLinkedList<Integer> list1 = new TwoWayLinkedList<>();
        Iterator<Integer> it = list.iterator();
        int tmp = it.next();

        while (it.hasNext()){
            if (!list1.contains(tmp))
                list1.add(tmp);
            tmp = it.next();
        }

        return list1;
    }
}
