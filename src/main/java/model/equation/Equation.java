package model.equation;

import model.operations.Operation;
import model.tree.Node;
import model.tree.Variable;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.List;

@SuppressWarnings("rawtypes")
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
     * @param operationClass          the {@link java.lang.Class} of the {@link Operation} that is supposed to be applied
     * @param secondObjectOfOperation The secondObjectOfOperation that is applied to the {@link Operation}
     * @param nodesToExclude          A {@link java.util.List} of {@link Node} that should be excluded from the change
     */
    @SuppressWarnings("unchecked")
    public void applyOperationToNodes(Class operationClass, Object secondObjectOfOperation, List<Node> nodesToExclude) {
        try {
            if (!nodesToExclude.contains(this.getRight())) {
                this.setRight((Node) operationClass.getDeclaredConstructor(Node.class, Node.class)
                        .newInstance(this.getRight(), secondObjectOfOperation));
            }
            if (!nodesToExclude.contains(this.getLeft())) {
                this.setLeft((Node) operationClass.getDeclaredConstructor(Node.class, Node.class)
                        .newInstance(this.getLeft(), secondObjectOfOperation));
            }
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
            e.printStackTrace();
        }
    }

    /**
     * Gets the {@link Variable}s with the {@link Node}s that are in the equation
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

    public HashMap<Variable, Node> getVariables() {
        return getVariables(this);
    }
}
