package com.company;

import java.util.Iterator;

public class Merger {

    public static OneWayLinkedList<Integer> merge(
            OneWayLinkedList<Integer> list1,
            OneWayLinkedList<Integer> list2) {

        Iterator<Integer> it1 = list1.iterator();
        Iterator<Integer> it2 = list2.iterator();

        OneWayLinkedList<Integer> mergedList = new OneWayLinkedList<>();

        Integer tmp1 = it1.next();
        Integer tmp2 = it2.next();

        int i1 = 0;
        int i2 = 0;

        while (i1< list1.size() && i2< list2.size()){
            if (tmp1<tmp2){
                mergedList.add(tmp1);
                if (it1.hasNext())
                    tmp1 = it1.next();
                i1++;
            }
            else {
                mergedList.add(tmp2);
                if (it2.hasNext())
                    tmp2 = it2.next();
                i2++;
            }
        }
        while (i1< list1.size()){
            mergedList.add(tmp1);
            if (it1.hasNext())
                tmp1 = it1.next();
            i1++;
        }
        while (i2< list2.size()){
            mergedList.add(tmp2);
            if (it2.hasNext())
                tmp2 = it2.next();
            i2++;
        }

        return mergedList;
    }
}
