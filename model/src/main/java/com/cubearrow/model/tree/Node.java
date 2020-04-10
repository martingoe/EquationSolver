package com.cubearrow.model.tree;

import com.cubearrow.model.operations.Operation;

public class Node {
    private static final String OPERATION_REGEX = "\\+|\\*|\\-|\\/";


    private Node left;
    private Node right;
    private Node parent;

    public Node(Node left, Node right, Node parent) {
        this.left = left;
        this.right = right;
        this.parent = parent;
    }

    /**
     * Parses a {@link Node} from a {@link String} by trying to parse every option of a {@link Node}
     *
     * @param stringToParse The {@link String} that contains the {@link Node}
     * @return Returns the parsed Node
     */
    public static Node fromString(String stringToParse, Node parent) {

        Number n = Number.fromString(stringToParse, parent);
        if (n != null) {
            return n;
        }

        Variable v = Variable.fromString(stringToParse, parent);
        if (v != null) {
            return v;
        }

        return Operation.fromString(stringToParse, parent);

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

    public boolean equals(Node node) {
        if (this instanceof Operation && node instanceof Operation || this instanceof Equation && node instanceof Equation)
            return this.getRight().equals(node.getRight()) && this.getLeft().equals(node.getLeft());
        else if (this instanceof Variable thisVariable && node instanceof Variable nodeVariable)
            return thisVariable.getVariableName() == nodeVariable.getVariableName();
        else if (this instanceof Number thisNumber&& node instanceof Number nodeNumber)
            return thisNumber.getNumber().equals(nodeNumber.getNumber());
        return false;
    }
}
