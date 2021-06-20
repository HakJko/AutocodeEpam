package com.efimchick.ifmo.collections.countwords;

import java.util.Comparator;

public class ComparatorWord implements Comparator<WordEntity>
{
    @Override
    public int compare(WordEntity o1, WordEntity o2) {
        return o2.getQuantityWords() - o1.getQuantityWords();
    }
}
