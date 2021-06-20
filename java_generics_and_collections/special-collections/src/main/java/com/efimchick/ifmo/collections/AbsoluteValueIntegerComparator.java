package com.efimchick.ifmo.collections;

import java.util.Comparator;

public class AbsoluteValueIntegerComparator implements Comparator<Integer>
{
    @Override
    public int compare(Integer o1, Integer o2) {
        return Math.abs(o1) - Math.abs(o2);
    }
}
