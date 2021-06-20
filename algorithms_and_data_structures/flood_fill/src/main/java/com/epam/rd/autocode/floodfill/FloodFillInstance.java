package com.epam.rd.autocode.floodfill;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class FloodFillInstance implements FloodFill
{

    @Override
    public void flood(String map, FloodLogger logger)
    {
        StringBuilder stringBuilderMap = new StringBuilder(map);
        int lineLength = getLineLength(stringBuilderMap);
        addWaterPositions(stringBuilderMap, lineLength);
        logger.log(map);

        if (isLand(stringBuilderMap)) {
            flood(stringBuilderMap.toString(), logger);
        } else {
            logger.log(stringBuilderMap.toString());
        }
    }

    private boolean isLand(StringBuilder stringBuilderMap)
    {
        return IntStream.range(0, stringBuilderMap.length())
                .anyMatch(i -> stringBuilderMap.charAt(i) == FloodFill.LAND);
    }

    private void addWaterPositions(StringBuilder stringBuilderMap, int lineLength)
    {
        List<Integer> indexes = getWaterIndexes(stringBuilderMap);
        indexes.forEach(index -> {
            addWaterInLeft(stringBuilderMap, index);
            addWaterInRight(stringBuilderMap, index);
            addWaterInUp(stringBuilderMap, lineLength, index);
            addWaterInDown(stringBuilderMap, lineLength, index);
        });
    }

    private void addWaterInDown(StringBuilder stringBuilderMap, int lineLength, Integer index)
    {
        if ((index + lineLength) < stringBuilderMap.length() && (index + lineLength) >= 0
                && stringBuilderMap.charAt(index + lineLength) != '\n') {
            stringBuilderMap.setCharAt(index + lineLength, WATER);
        }
    }

    private void addWaterInUp(StringBuilder stringBuilderMap, int lineLength, Integer index)
    {
        if ((index - lineLength) < stringBuilderMap.length() && (index - lineLength) >= 0
                && stringBuilderMap.charAt(index - lineLength) != '\n') {
            stringBuilderMap.setCharAt(index - lineLength, WATER);
        }
    }

    private void addWaterInRight(StringBuilder stringBuilderMap, Integer index)
    {
        if ((index + 1) < stringBuilderMap.length() && (index + 1) >= 0
                && stringBuilderMap.charAt(index + 1) != '\n') {
            stringBuilderMap.setCharAt(index + 1, WATER);
        }
    }

    private void addWaterInLeft(StringBuilder stringBuilderMap, Integer index)
    {
        if ((index - 1) < stringBuilderMap.length() && (index - 1) >= 0
                && stringBuilderMap.charAt(index - 1) != '\n') {
            stringBuilderMap.setCharAt(index - 1, WATER);
        }
    }

    private int getLineLength(StringBuilder newMap) {
        return newMap.indexOf("\n") + 1;
    }

    private List<Integer> getWaterIndexes(StringBuilder newMap)
    {
        return IntStream.range(0, newMap.length())
                .filter(index -> newMap.charAt(index) == WATER)
                .boxed()
                .collect(Collectors.toList());
    }
}