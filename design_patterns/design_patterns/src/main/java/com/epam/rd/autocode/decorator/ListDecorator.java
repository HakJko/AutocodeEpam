package com.epam.rd.autocode.decorator;

import java.util.*;;

public class ListDecorator implements List<String>
{
    private final List<String> source;

    public ListDecorator(List<String> source) {
        this.source = source;
    }

    @Override
    public int size() {
        return source.size() % 2 == 0
                ? source.size() / 2
                : source.size() / 2 + 1;
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    @Override
    public boolean contains(Object o) {
        return false;
    }

    @Override
    public Iterator<String> iterator() {
        return null;
    }

    @Override
    public Object[] toArray() {
        return new Object[0];
    }

    @Override
    public <T> T[] toArray(T[] a) {
        return null;
    }

    @Override
    public boolean add(String s) {
        return false;
    }

    @Override
    public boolean remove(Object o) {
        return false;
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        return false;
    }

    @Override
    public boolean addAll(Collection<? extends String> c) {
        return false;
    }

    @Override
    public boolean addAll(int index, Collection<? extends String> c) {
        return false;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        return false;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        return false;
    }

    @Override
    public void clear() {
    }

    @Override
    public String get(int index) {
        return source.get(getEvenIndexFromSource(index));
    }

    private int getEvenIndexFromSource(int index) {
        return index * 2;
    }

    @Override
    public String set(int index, String element) {
        return null;
    }

    @Override
    public void add(int index, String element) {

    }

    @Override
    public String remove(int index) {
        return null;
    }

    @Override
    public int indexOf(Object o) {
        return 0;
    }

    @Override
    public int lastIndexOf(Object o) {
        return 0;
    }

    @Override
    public ListIterator<String> listIterator() {
        return getEvenList().listIterator();
    }

    private List<String> getEvenList() {
        List<String> evenIndex = new ArrayList<>();
        for (int i = 0; i < size(); i++) evenIndex.add(get(i));
        return evenIndex;
    }

    @Override
    public ListIterator<String> listIterator(int index) {
        return null;
    }

    @Override
    public List<String> subList(int fromIndex, int toIndex) {
        return null;
    }
}
