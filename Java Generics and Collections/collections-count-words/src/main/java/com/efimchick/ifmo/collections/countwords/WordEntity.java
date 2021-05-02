package com.efimchick.ifmo.collections.countwords;

public class WordEntity
{
    private int quantityWords;
    private String word;

    public WordEntity(String word, int quantityWords) {
        this.quantityWords = quantityWords;
        this.word = word;
    }

    public int getQuantityWords() {
        return quantityWords;
    }

    public String getWord() {
        return word;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(word);
        sb.append(" - ");
        sb.append(quantityWords);
        sb.append("\n");
        return String.valueOf(sb);
    }
}
