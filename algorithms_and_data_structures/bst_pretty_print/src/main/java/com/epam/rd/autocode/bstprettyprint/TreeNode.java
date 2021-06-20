package com.epam.rd.autocode.bstprettyprint;

public class TreeNode
{
    private final int value;
    private final TreeNode parent;
    private TreeNode leftChild;
    private TreeNode rightChild;

    public TreeNode(final int value, final TreeNode parent) {
        this.value = value;
        this.parent = parent;
    }

    public int value() {
        return value;
    }

    public TreeNode leftChild() {
        return leftChild;
    }

    public TreeNode rightChild() {
        return rightChild;
    }

    public TreeNode parent() {
        return parent;
    }

    void add(final int i) {
        if (i < value) {
            if (leftChild == null) {
                leftChild = new TreeNode(i, this);
            } else {
                leftChild.add(i);
            }
        } else  if (i > value) {
            if (rightChild == null) {
                rightChild = new TreeNode(i, this);
            } else {
                rightChild.add(i);
            }
        }
    }
}
