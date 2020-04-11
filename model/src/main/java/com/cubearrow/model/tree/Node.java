package com.cubearrow.model.tree;

import com.cubearrow.model.equation.Equation;
import com.cubearrow.model.operations.Operation;

public class Node {
    private static final String OPERATION_REGEX = "\\+|\\*|\\-|\\/";

    private Node left;
    private Node right;
    private Node parent;

    /**
     * Initializes a new Node
     * @param left The left child of the new Node
     * @param right The right child of the new Node
     * @param parent The parent of the new Node
     */
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

    /**
     * Returns the parent Node of the current Node
     * @return the parent of the Node
     */
    public Node getParent() {
        return parent;
    }

    /**
     * Sets the parent Node
     * @param parent The node to be set as parent
     */
    public void setParent(Node parent) {
        this.parent = parent;
    }

    /**
     * Returns the left child Node of the current Node
     * @return the left child of the Node
     */
    public Node getLeft() {
        return left;
    }

    /**
     * Sets the left child Node
     * @param left The node to be set as left child
     */
    public void setLeft(Node left) {
        this.left = left;
    }

    /**
     * Returns the right child Node of the current Node
     * @return the right child of the Node
     */
    public Node getRight() {
        return right;
    }
    /**
     * Sets the right child Node
     * @param right The node to be set as right child
     */
    public void setRight(Node right) {
        this.right = right;
    }

    /**
     * Indicates weather a {@link Node} has the same content as this {@link Node}
     * @param node The {@link Node} to be compared to
     * @return {@code true} if the content is the same; {@code false} if not
     */
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
