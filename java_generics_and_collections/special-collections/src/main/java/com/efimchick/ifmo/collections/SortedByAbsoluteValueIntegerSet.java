package com.efimchick.ifmo.collections;

import java.util.Collection;
import java.util.Iterator;
import java.util.Set;
import java.util.TreeSet;

class SortedByAbsoluteValueIntegerSet implements Set<Integer>
{
    private final Set<Integer> SORTED_INTEGER_SET
            = new TreeSet<>(new AbsoluteValueIntegerComparator());

    @Override
    public int size() {
        return SORTED_INTEGER_SET.size();
    }

    @Override
    public boolean isEmpty() {
        return SORTED_INTEGER_SET.isEmpty();
    }

    @Override
    public boolean contains(Object obj) {
        return SORTED_INTEGER_SET.contains(obj);
    }

    @Override
    public Iterator iterator() {
        return SORTED_INTEGER_SET.iterator();
    }

    @Override
    public Object[] toArray() {
        return SORTED_INTEGER_SET.toArray();
    }

    @Override
    public boolean add(Integer obj) {
        return SORTED_INTEGER_SET.add(obj);
    }

    @Override
    public boolean remove(Object obj) {
        return SORTED_INTEGER_SET.remove(obj);
    }

    @Override
    public boolean addAll(Collection collect) {
        return SORTED_INTEGER_SET.addAll(collect);
    }

    @Override
    public void clear() {
        SORTED_INTEGER_SET.clear();
    }

    @Override
    public boolean removeAll(Collection collect) {
        return SORTED_INTEGER_SET.removeAll(collect);
    }

    @Override
    public boolean retainAll(Collection collect) {
        return SORTED_INTEGER_SET.retainAll(collect);
    }

    @Override
    public boolean containsAll(Collection collect) {
        return SORTED_INTEGER_SET.containsAll(collect);
    }

    @Override
    public Object[] toArray(Object[] array) {
        return SORTED_INTEGER_SET.toArray(array);
    }
}
