package com.cubearrow.model.equation;

import com.cubearrow.model.operations.Operation;
import com.cubearrow.model.tree.Node;
import com.cubearrow.model.tree.Number;
import com.cubearrow.model.tree.Variable;

import java.util.HashMap;
import java.util.List;

public class Equation extends Node implements Cloneable {


    /**
     * Initializes an {@link Equation}. This is what saves an equation
     */
    public Equation(Node right, Node left) {
        super(right, left, null);
    }

    public Equation() {
        super(null, null, null);
    }

    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    @Override
    public String toString() {
        return "%s=%s".formatted(this.getLeft().toString(), this.getRight().toString());
    }

    /**
     * Applies an operation on every children node of the equation
     *
     * @param operation      The {@link Operation} that should be applied to the {@link Node}
     * @param nodesToExclude A {@link java.util.List} of {@link Node} that should be excluded from the change
     */
    private void applyOperationToNodes(Operation operation, List<Node> nodesToExclude) {
        if (!nodesToExclude.contains(this.getRight())) {
            this.setRight(operation.applyToNode(this.getRight()));
        }
        if (!nodesToExclude.contains(this.getLeft())) {
            this.setLeft(operation.applyToNode(this.getLeft()));
        }
    }

    /**
     * Simplifies one step of the entire equation
     */
    public void simplify() {
        if (this.getLeft() instanceof Operation) {
            this.setLeft(simplify((Operation) this.getLeft()));
        }
        if (this.getRight() instanceof Operation) {
            this.setRight(simplify((Operation) this.getRight()));
        }
    }

    /**
     * Simplifies one step of the node specified
     *
     * @param node The node to specified
     * @return Returns the simplified node
     */
    private Node simplify(Operation node) {
        Node result = simplifySimpleOperations(node);
        return result instanceof Operation ? simplifyOperation((Operation) result) : result;
    }

    /**
     * Calls the simplify function of the operation provided
     *
     * @param operation The {@link Operation} to simplify
     * @return returns the new and simplified {@link Node}
     */
    private Node simplifyOperation(Operation operation) {
        return operation.simplify();
    }

    /**
     * Simplifies a very basic operation. It does not use the simplify function of the operation itself
     *
     * @param operation The {@link Operation} that should be simplified
     * @return Returns the {@link Node} that represent the simplified operation
     */
    private Node simplifySimpleOperations(Operation operation) {
        Node right = operation.getRight();
        Node left = operation.getLeft();


        if (right instanceof Operation) {
            right = simplify((Operation) right);
        }
        if (left instanceof Operation) {
            left = simplify((Operation) left);
        }

        operation.setLeft(left);
        operation.setRight(right);
        if (right instanceof Number && left instanceof Number) {
            return operation.getResult();
        }
        return operation;
    }


    /**
     * Gets the {@link Variable}s with the {@link Node}s that are in the parentNode
     * <p>
     * This function is recursive and hence needs a {@link List} of {@link Variable}s as a Argument
     *
     * @param parentNode The current {@link Node} that of the recursion
     * @return Returns the {@link Variable}s as a {@link HashMap}
     */
    private HashMap<Variable, Node> getVariables(Node parentNode) {
        HashMap<Variable, Node> vars = new HashMap<>();

        vars.putAll(getVariablesInNode(parentNode.getLeft(), parentNode));
        vars.putAll(getVariablesInNode(parentNode.getRight(), parentNode));

        // Return the list of Variables
        return vars;
    }

    /**
     * Return the Variables in a single node
     *
     * @param node       The node that is checked for variables
     * @param parentNode The parent of the node being checked
     * @return Returns a HashMap with the variables in the node
     */
    private HashMap<Variable, Node> getVariablesInNode(Node node, Node parentNode) {
        if (node instanceof Variable) {
            return new HashMap<>() {{
                put((Variable) node, parentNode);
            }};
        }

        // If the children node is a operation, add the variables in that operation
        else if (node instanceof Operation) {
            return getVariables(node);
        }
        return new HashMap<>();
    }

    /**
     * Gets the {@link Variable}s that are in the whole {@link Equation}
     *
     * @return Returns the {@link Variable} in a {@link HashMap}
     */
    public HashMap<Variable, Node> getVariables() {
        return getVariables(this);
    }
}
