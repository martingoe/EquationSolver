package model.equation;

import model.operations.Operation;
import model.tree.Node;
import model.tree.Number;
import model.tree.Variable;

import java.util.HashMap;
import java.util.List;

public class Equation extends Node {


    /**
     * Initializes an {@link Equation}. This is what saves an equation
     */
    public Equation(Node right, Node left) {
        super(right, left);
    }


    /**
     * Applies an operation on every children node of the equation
     *
     * @param operation      The {@link Operation} that should be applied to the {@link Node}
     * @param nodesToExclude A {@link java.util.List} of {@link Node} that should be excluded from the change
     */
    public void applyOperationToNodes(Operation operation, List<Node> nodesToExclude) {
        if (!nodesToExclude.contains(this.getRight())) {
            this.setRight(operation.applyToNode(this.getRight()));
        }
        if (!nodesToExclude.contains(this.getLeft())) {
            this.setLeft(operation.applyToNode(this.getLeft()));
        }
    }

    /**
     * Calls the simplify function of the operation provided
     *
     * @param operation The {@link Operation} to simplify
     * @return returns the new and simplified {@link Node}
     */
    public Node simplifyOperation(Operation operation) {
        return operation.simplify();
    }

    /**
     * Simplifies a very basic operation. It does not use the simplify function of the operation itself
     *
     * @param operation The {@link Operation} that should be simplified
     * @return Returns the {@link Node} that represent the simplified operation
     */
    public Node simplifySimpleOperations(Operation operation) {
        Node right = operation.getRight();
        Node left = operation.getLeft();


        if (right instanceof Operation) {
            right = simplifySimpleOperations((Operation) right);
        }
        if (left instanceof Operation) {
            left = simplifySimpleOperations((Operation) left);
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

        if (parentNode.getRight() instanceof Variable) {
            vars.put((Variable) parentNode.getRight(), parentNode);
        }
        // If the children node is a operation, add the variables in that operation
        else if (parentNode.getRight() instanceof Operation) {
            vars.putAll(getVariables(parentNode.getRight()));
        }
        if (parentNode.getLeft() instanceof Variable) {
            vars.put((Variable) parentNode.getLeft(), parentNode);
        }
        // If the children node is a operation, add the variables in that operation
        else if (parentNode.getLeft() instanceof Operation) {
            vars.putAll(getVariables(parentNode.getLeft()));
        }
        // Return the list of Variables
        return vars;
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
