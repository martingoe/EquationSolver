package com.cubearrow.model.tree;

public class Node {
    Node left;
    Node right;
    Node parent;

    public Node(Node left, Node right, Node parent) {
        this.left = left;
        this.right = right;
        this.parent = parent;
    }

    public Node getParent() {
        return parent;
    }

    public void setParent(Node parent) {
        this.parent = parent;
    }

    public Node getLeft() {
        return left;
    }

    public void setLeft(Node left) {
        this.left = left;
    }

    public Node getRight() {
        return right;
    }

    public void setRight(Node right) {
        this.right = right;
    }
}
