package com.epam.rd.autocode.iterator;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class IntArrayFiveTimesIterator implements Iterator<Integer> {
    private final int[] array;
    private int index;
    private int indexCounter;

    public IntArrayFiveTimesIterator(int[] array) {
        this.array = array;
    }

    @Override
    public boolean hasNext() {
        return index < array.length;
    }

    @Override
    public Integer next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        } else {
            countIndexRepeat();
            return array[getFivefoldIndex()];
        }
    }

    private void countIndexRepeat() {
        indexCounter++;
    }

    private Integer getFivefoldIndex() {
        int value = index;
        if (indexCounter == 5) {
            increaseIndex();
            resetIndexCounter();
        }
        return value;
    }

    private void increaseIndex() {
        index++;
    }

    private void resetIndexCounter() {
        indexCounter = 0;
    }
}
