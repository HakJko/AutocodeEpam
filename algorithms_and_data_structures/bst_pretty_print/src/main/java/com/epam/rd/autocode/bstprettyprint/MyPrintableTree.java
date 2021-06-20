package com.epam.rd.autocode.bstprettyprint;

import java.util.ArrayList;
import java.util.List;

public class MyPrintableTree implements PrintableTree
{
    private static final char RIGHT_UP_DOWN = '┤';
    private static final char RIGHT_DOWN = '┐';
    private static final char LEFT_DOWN = '┌';
    private static final char BRANCH_CHAR = '│';
    private TreeNode root;

    @Override
    public void add(final int i) {
        if (root == null) {
            root = new TreeNode(i, null);
        } else {
            root.add(i);
        }
    }

    @Override
    public String prettyPrint()
    {
        StringBuilder builder = new StringBuilder();
        List<Integer> branchesPositions = new ArrayList<>();
        for (PrintNode printNode : getValues(root)) {
            char[] chars = printNode.toString().toCharArray();
            insertBranches(chars, branchesPositions);
            updateBranchesList(chars, branchesPositions);
            builder.append(String.valueOf(chars));
        }
        return builder.toString();
    }

    private List<PrintNode> getValues(final TreeNode node)
    {
        if (node == null) {
            return new ArrayList<>();
        }
        List<PrintNode> values = new ArrayList<>(getValues(node.leftChild()));
        values.add(new PrintNode(node));
        values.addAll(getValues(node.rightChild()));
        return values;
    }

    private void insertBranches(final char[] chars, final List<Integer> branchesPositions)
    {
        for (int i = 0; i < chars.length; i++) {
            if (branchesPositions.contains(i) && chars[i] == ' ') {
                chars[i] = '│';
            }
        }
    }

    private void updateBranchesList(final char[] chars, final List<Integer> branchesPositions)
    {
        for (int i = 0; i < chars.length; i++) {
            if (branchesPositions.contains(i) && chars[i] != BRANCH_CHAR) {
                branchesPositions.remove(Integer.valueOf(i));
            }
            if (chars[i] == RIGHT_DOWN || chars[i] == LEFT_DOWN
                || chars[i] == RIGHT_UP_DOWN) {
                branchesPositions.add(i);
            }
        }
    }
}
