package com.efimchick.ifmo.collections;

import java.util.*;

class PairStringList implements List<String>
{
    private final List<String> LIST = new ArrayList<>();

    @Override
    public int size() {
        return LIST.size();
    }

    @Override
    public Iterator<String> iterator() {
        return LIST.iterator();
    }

    @Override
    public boolean add(final String str) {
        for (int i = 0; i < 2; i++) {
            LIST.add(str);
        }
        return true;
    }

    @Override
    public void add(int index, final String elem) {
        index = correctIndex(index);
        for (int i = 0; i < 2; i++) {
            LIST.add(index, elem);
        }
    }

    @Override
    public boolean remove(final Object obj) {
        for (int i = 0; i < 2; i++) {
            LIST.remove(obj);
        }
        return true;
    }

    @Override
    public String remove(final int index) {
        LIST.remove(index);
        return LIST.remove(getPairIndex(index));
    }

    @Override
    public boolean addAll(final Collection<? extends String> collect) {
        for (String string : collect) {
            add(string);
        }
        return true;
    }

    @Override
    public boolean addAll(int index, final Collection<? extends String> collect) {
        index = correctIndex(index);
        for (String string : collect) {
            add(index, string);
            index = index + 2;
        }
        return true;
    }

    @Override
    public String get(final int index) {
        return LIST.get(index);
    }

    @Override
    public String set(final int index, final String elem) {
        LIST.set(index, elem);
        return LIST.set(getPairIndex(index), elem);
    }

    @Override
    public <T> T[] toArray(final T[] array) {
        return array;
    }

    @Override
    public void clear() {
        LIST.clear();
    }

    @Override
    public boolean isEmpty() {
        return LIST.isEmpty();
    }

    @Override
    public boolean contains(final Object obj) {
        return LIST.contains(obj);
    }

    @Override
    public Object[] toArray() {
        return LIST.toArray();
    }

    @Override
    public boolean containsAll(final Collection<?> collect) {
        return LIST.containsAll(collect);
    }

    @Override
    public boolean removeAll(final Collection<?> collect) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean retainAll(final Collection<?> collect) {
        throw new UnsupportedOperationException();
    }

    @Override
    public int indexOf(final Object obj) {
        throw new UnsupportedOperationException();
    }

    @Override
    public int lastIndexOf(final Object obj) {
        throw new UnsupportedOperationException();
    }

    @Override
    public ListIterator<String> listIterator() {
        throw new UnsupportedOperationException();
    }

    @Override
    public ListIterator<String> listIterator(final int index) {
        throw new UnsupportedOperationException();
    }

    @Override
    public List<String> subList(final int fromIndex, final int toIndex) {
        throw new UnsupportedOperationException();
    }

    private int correctIndex(int index) {
        if (isOdd(index)) {
            return ++index;
        } else return index;
    }

    private int getPairIndex(int index) {
        if (isEven(index)) {
            return ++index;
        } else {
            return --index;
        }
    }

    private boolean isEven(final int num) {
        return (num ^ (num + 1)) == 1;
    }

    private boolean isOdd(final int num) {
        return !isEven(num);
    }
}