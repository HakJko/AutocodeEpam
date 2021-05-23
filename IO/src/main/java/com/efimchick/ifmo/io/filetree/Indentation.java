package com.efimchick.ifmo.io.filetree;

import com.efimchick.ifmo.io.filetree.entity.Wrapper;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.IntStream;

public class Indentation
{
    private static final Set<Integer> branchesHashSet = new HashSet<>();

    public static void addIndent(Wrapper path, StringBuilder stringBuilder) {
        String indent = makeIndent(path);
        refreshListOfOpenedBranches(indent);
        indent = addOpenedBranches(indent);
        stringBuilder.append(indent);
    }

    private static String makeIndent(Wrapper wrapper) {
        String tab = "   ";
        String last = "└─ ";
        String jump = "├─ ";
        return tab.repeat(Math.max(0, wrapper.getLevelOfNesting() - 1))
                +
                (wrapper.isLast() ? last : jump);
    }

    private static void refreshListOfOpenedBranches(String indent) {
        char[] chars = indent.toCharArray();
        char openBranches = '├';
        char lastInBranch = '└';

        for (int i = 0; i < chars.length; i++) {
            if (chars[i] == openBranches) {
                branchesHashSet.add(i);
            } else if (chars[i] == lastInBranch) {
                branchesHashSet.remove(i);
            }
        }
    }

    private static String addOpenedBranches(String indent) {
        char[] chars = indent.toCharArray();
        char space = ' ';
        char lineVertical = '│';

        IntStream.range(0, chars.length)
                .filter(i -> branchesHashSet.contains(i) && chars[i] == space)
                .forEach(i -> chars[i] = lineVertical);

        return String.valueOf(chars);
    }
}