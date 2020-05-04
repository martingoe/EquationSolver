package com.cubearrow.model.tree;

import com.cubearrow.model.equation.Equation;
import com.cubearrow.model.equation.EquationInitializer;
import com.cubearrow.model.operations.Operation;
import com.cubearrow.model.regex.RegExUtil;
import com.cubearrow.model.rewriting.patterns.*;

/**
 * @param <E> The type of the value of the Node. Mainly used by the Classes {@link Number} and {@link Variable} because they hold values
 */
public class Node<E> implements Cloneable {

    protected E value;
    private Node left;
    private Node right;
    private Node parent;

    /**
     * Initializes a new Node
     *
     * @param left   The left child of the new Node
     * @param right  The right child of the new Node
     * @param parent The parent of the new Node
     */
    public Node(Node left, Node right, Node parent) {
        this.left = left;
        this.right = right;
        this.parent = parent;
    }

    public Node(E value) {
        this.value = value;
    }

    public Node(Node parent, E value) {
        this.parent = parent;
        this.value = value;
    }

    /**
     * Parses a {@link Node} from a {@link String} by trying to parse every option of a {@link Node}
     *
     * @param stringToParse The {@link String} that contains the {@link Node}
     * @return Returns the parsed Node
     */
    public static Node fromString(String stringToParse, Node parent) {
        if(stringToParse.contains("=")){
            return new EquationInitializer(stringToParse).parseEquation();
        }
        Node node = parsePatternVariables(stringToParse, parent);
        if (node != null){
            return node;
        }

        Number number = Number.fromString(stringToParse, parent);
        if (number != null) {
            return number;
        }

        Variable variable = Variable.fromString(stringToParse, parent);
        if (variable != null) {
            return variable;
        }

        return Operation.fromString(stringToParse, parent);
    }

    private static Node parsePatternVariables(String stringToParse, Node parent) {
        if (stringToParse.matches("\\$nu\\d+")) {
            return new GenericPatternNumber(Integer.parseInt(RegExUtil.getFirstSubstring(stringToParse, "[0-9]+", 0)), parent);
        }
        if (stringToParse.matches("\\$op\\d+")) {
            return new GenericPatternOperation(Integer.parseInt(RegExUtil.getFirstSubstring(stringToParse, "[0-9]+", 0)), parent);
        }
        if (stringToParse.matches("\\$var\\d+")) {
            return new GenericPatternVariable(Integer.parseInt(RegExUtil.getFirstSubstring(stringToParse, "[0-9]+", 0)), parent);
        }
        if (stringToParse.matches("\\$\\d+")) {
            return new GenericPatternLiteral(Integer.parseInt(RegExUtil.getFirstSubstring(stringToParse, "[0-9]+", 0)), parent);
        }
        if(stringToParse.matches("\\$ivar\\d+")){
            return new GenericPatternIsolationVariable(Integer.parseInt(RegExUtil.getFirstSubstring(stringToParse, "[0-9]+", 0)), parent);
        }

        return null;
    }

    /**
     * Clones the Node by creating a new Object with a different address in Memory.
     *
     * @return Returns the cloned Object, if a {@link CloneNotSupportedException} is thrown, returns null
     */
    public Node clone() {
        try {
            return (Node) super.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Returns the parent Node of the current Node
     *
     * @return the parent of the Node
     */
    public Node getParent() {
        return parent;
    }

    /**
     * Sets the parent Node
     *
     * @param parent The node to be set as parent
     */
    public void setParent(Node parent) {
        this.parent = parent;
    }

    /**
     * Returns the left child Node of the current Node
     *
     * @return the left child of the Node
     */
    public Node getLeft() {
        return left;
    }

    /**
     * Sets the left child Node
     *
     * @param left The node to be set as left child
     */
    public void setLeft(Node left) {
        this.left = left;
    }

    /**
     * Returns the right child Node of the current Node
     *
     * @return the right child of the Node
     */
    public Node getRight() {
        return right;
    }

    /**
     * Sets the right child Node
     *
     * @param right The node to be set as right child
     */
    public void setRight(Node right) {
        this.right = right;
    }

    /**
     * Indicates weather a {@link Node} has the same content as this {@link Node}
     *
     * @param node The {@link Node} to be compared to
     * @return {@code true} if the content is the same; {@code false} if not
     */
    public boolean equals(Node node) {
        if (this == null || node == null) {
            return this == node;
        }

        if (this.getClass() == node.getClass()) {
            return equalsSameClass(node);
        }
        return false;

    }

    private boolean equalsSameClass(Node node) {
        if (this instanceof Operation || this instanceof Equation) {
            return this.getRight().equals(node.getRight()) && this.getLeft().equals(node.getLeft());
        } else if (this instanceof Variable || this instanceof Number) {
            return this.getValue().equals(node.getValue());
        }
        return false;
    }

    public E getValue() {
        return value;
    }

    public void setValue(E value) {
        this.value = value;
    }
}
