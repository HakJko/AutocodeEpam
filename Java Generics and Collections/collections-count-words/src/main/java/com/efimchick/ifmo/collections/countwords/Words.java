package com.efimchick.ifmo.collections.countwords;


import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Words
{
    private static final int MIN_LENGTH_WORD = 4;
    private static final int MIN_AMOUNT_WORDS = 10;
    private static final String REGEX_WITHOUT_LETTERS = "\\P{L}+";


    public String countWords(List<String> lines)
    {
        List<WordEntity> wordEntities = getAllWordsWithQuantity(lines);
        StringBuilder strEntities = new StringBuilder();
        ComparatorWord comparator = new ComparatorWord();

        wordEntities.sort(comparator);

        for (WordEntity wordEntity : wordEntities) {
            if (isSmallOrRareWords(wordEntity))
                strEntities.append(wordEntity);
        }
        strEntities.deleteCharAt(strEntities.length() - 1);
        return strEntities.toString();
    }

    private boolean isSmallOrRareWords(WordEntity wordEntity)
    {
        return wordEntity.getWord().length() >= MIN_LENGTH_WORD
                && wordEntity.getQuantityWords() >= MIN_AMOUNT_WORDS;
    }

    private List<WordEntity> getAllWordsWithQuantity(List<String> lines)
    {
        List<WordEntity> wordsWithQuantity = new ArrayList<>();
        List<String> wordsList = getAllWords(lines);

        Collections.sort(wordsList);
        String comparableWord = wordsList.get(0);
        int counter = 0;

        for (String word : wordsList) {
            if (comparableWord.equals(word)) {
                counter++;
            } else {
                wordsWithQuantity.add(new WordEntity(comparableWord, counter));
                comparableWord = word;
                counter = 1;
            }
        }
        wordsWithQuantity.add(new WordEntity(comparableWord, counter));
        return wordsWithQuantity;
    }

    private List<String> getAllWords(List<String> lines)
    {
        List<String> lowCaseLines = getLowCaseLines(lines);
        List<String> wordsList = new ArrayList<>();

        for (String line : lowCaseLines) {
            String[] words = line.split(REGEX_WITHOUT_LETTERS);
            Collections.addAll(wordsList, words);
        }
        return wordsList;
    }

    private List<String> getLowCaseLines(List<String> lines)
    {
        List<String> lowCaseLines = new ArrayList<>();

        for (String line : lines) {
            lowCaseLines.add(line.toLowerCase());
        }
        return lowCaseLines;
    }
}
