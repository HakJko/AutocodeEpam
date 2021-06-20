package com.efimchick.ifmo.collections;

import java.util.*;

class MedianQueue implements Queue<Integer>
{
    private final LinkedList<Integer> QUEUE = new LinkedList<>();

    @Override
    public boolean add(final Integer integer) {
        return QUEUE.add(integer);
    }

    @Override
    public boolean offer(final Integer integer) {
        QUEUE.offer(integer);
        permutationElem();
        return true;
    }

    @Override
    public Integer remove() {
        Integer removed = QUEUE.remove();
        permutationElem();
        return removed;
    }

    @Override
    public Integer poll() {
        Integer removed = QUEUE.poll();
        permutationElem();
        return removed;
    }

    @Override
    public Integer element() {
        return QUEUE.element();
    }

    @Override
    public Integer peek() {
        return QUEUE.peek();
    }

    @Override
    public int size() {
        return QUEUE.size();
    }

    @Override
    public boolean remove(final Object obj) {
        if (QUEUE.remove(obj)) {
            permutationElem();
            return true;
        }
        return false;
    }

    @Override
    public boolean isEmpty() {
        return QUEUE.isEmpty();
    }

    @Override
    public boolean contains(final Object obj) {
        return QUEUE.contains(obj);
    }

    @Override
    public Iterator<Integer> iterator() {
        return QUEUE.iterator();
    }

    @Override
    public Object[] toArray() {
        return QUEUE.toArray();
    }

    @Override
    public <T> T[] toArray(final T[] array) {
        return array;
    }


    @Override
    public boolean containsAll(final Collection<?> collect) {
        return QUEUE.containsAll(collect);
    }

    @Override
    public boolean addAll(final Collection<? extends Integer> collect) {
        QUEUE.addAll(collect);
        permutationElem();
        return true;
    }

    @Override
    public boolean removeAll(final Collection<?> collect) {
        if (QUEUE.removeAll(collect)) {
            permutationElem();
            return true;
        }
        return false;
    }

    @Override
    public boolean retainAll(final Collection<?> collect) {
        return QUEUE.retainAll(collect);
    }

    @Override
    public void clear() {
        QUEUE.clear();
    }


    private void permutationElem()
    {
        LinkedList<Integer> list = new LinkedList<>(QUEUE);
        Collections.sort(list);
        clear();
        int initialSize = list.size();
        for (int i = 0; i < initialSize; i++) {
            if (isEven(i)) {
                QUEUE.addFirst(list.pollLast());
            } else {
                QUEUE.addFirst(list.pollFirst());
            }
        }
    }

    private boolean isEven(int num) {
        return num % 2 == 0;
    }
}
