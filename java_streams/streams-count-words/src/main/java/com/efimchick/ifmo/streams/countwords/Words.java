package com.efimchick.ifmo.streams.countwords;


import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Words
{
    public static final String WHITESPACES = "\\s+";
    public static final String WHITESPACE = " ";
    public static final int MIN_LENGTH = 4;
    public static final int MIN_COUNTER = 10;
    public static final String REGEX_WITHOUT_LETTERS = "\\P{L}+";
    public static final String STRING_FORMAT = "%s - %d\n";

    public String countWords(List<String> lines)
    {
        String stringResult = lines
                .stream()
                .map(str -> str.replaceAll(REGEX_WITHOUT_LETTERS, WHITESPACE))
                .flatMap(str -> Arrays.stream(str.toLowerCase().split(WHITESPACES)))
                .filter(str -> str.length() >= MIN_LENGTH)
                .collect(Collectors.groupingBy(s -> s, Collectors.counting()))
                .entrySet().stream()
                .filter(e -> e.getValue() >= MIN_COUNTER)
                .sorted(Map.Entry.comparingByKey())
                .sorted((e1, e2) -> (int) (e2.getValue() - e1.getValue()))
                .map(e -> String.format(STRING_FORMAT, e.getKey(), e.getValue()))
                .collect(Collectors.joining());

        return deleteLastChar(stringResult);
    }

    private String deleteLastChar(String stringResult)
    {
        StringBuilder strBuilder = new StringBuilder(stringResult);
        return String.valueOf(strBuilder.deleteCharAt(strBuilder.length() - 1));
    }
}
