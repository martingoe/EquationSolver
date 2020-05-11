package com.cubearrow.model.equation;

import com.cubearrow.model.operations.Operation;
import com.cubearrow.model.rewriting.EquationRewriter;
import com.cubearrow.model.tree.Node;
import com.cubearrow.model.tree.Variable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Equation extends Node implements Cloneable {
    private char variableToIsolate;

    /**
     * Initializes an {@link Equation} by specifying the two child nodes.
     *
     * @param left  The left child {@link Node}
     * @param right The right child {@link Node}
     */
    public Equation(Node right, Node left) {
        super(right, left, null);
    }

    /**
     * Initializes an empty Equation using null as every parameter.
     */
    public Equation() {
        super(null, null, null);
    }

    public char getVariableToIsolate() {
        return variableToIsolate;
    }

    public void setVariableToIsolate(char variableToIsolate) {
        this.variableToIsolate = variableToIsolate;
    }


    /**
     * Returns a {@link String} representation of the Equation
     *
     * @return a {@link String} representing the Equation
     */
    @Override
    public String toString() {
        return "%s=%s".formatted(this.getLeft().toString(), this.getRight().toString());
    }

    /**
     * Simplifies one step of the entire equation
     *
     * @return Returns the simplified Operation
     */
    public Equation simplify(EquationRewriter equationRewriter) {
        if (this.getLeft() instanceof Operation leftOperation) {
            this.setLeft(simplifyOperation(leftOperation, equationRewriter));
        }
        if (this.getRight() instanceof Operation rightOperation) {
            this.setRight(simplifyOperation(rightOperation, equationRewriter));
        }
        return simplifyEquation(equationRewriter);
    }

    private Equation simplifyEquation(EquationRewriter equationRewriter) {
        return equationRewriter.applyRulesToEquation(this);
    }

    /**
     * Simplifies a very basic operation. It does not use the simplify function of the operation itself
     *
     * @param operation The {@link Operation} that should be simplified
     * @return Returns the {@link Node} that represent the simplified operation
     */
    private Node simplifyOperation(Operation operation, EquationRewriter equationRewriter) {
        Node result = equationRewriter.applyRulesToOperation(operation, this);

        if (result.getLeft() instanceof Operation leftOperation)
            result.setLeft(simplifyOperation(leftOperation, equationRewriter));
        if (result.getRight() instanceof Operation rightOperation)
            result.setRight(simplifyOperation(rightOperation, equationRewriter));

        return ((Operation) result).getResult();
    }


    /**
     * Gets the {@link Variable}s with the {@link Node}s that are in the parentNode
     * <p>
     * This function is recursive and hence needs a {@link List} of {@link Variable}s as a Argument
     *
     * @param parentNode The current {@link Node} that of the recursion
     * @return Returns the {@link Variable}s as a {@link HashMap}
     */
    private List<Variable> getVariables(Node parentNode) {
        List<Variable> vars = new ArrayList<>();

        vars.addAll(getVariablesInNode(parentNode.getLeft()));
        vars.addAll(getVariablesInNode(parentNode.getRight()));

        // Return the list of Variables
        return vars;
    }

    /**
     * Return the Variables in a single node
     *
     * @param node The node that is checked for variables
     * @return Returns a HashMap with the variables in the node
     */
    private List<Variable> getVariablesInNode(Node node) {
        if (node instanceof Variable) {
            return new ArrayList<>() {{
                add((Variable) node);
            }};
        }

        // If the children node is a operation, add the variables in that operation
        else if (node instanceof Operation) {
            return getVariables(node);
        }
        return new ArrayList<>();
    }

    /**
     * Gets the {@link Variable}s that are in the whole {@link Equation}
     *
     * @return Returns the {@link Variable} in a {@link HashMap}
     */
    public List<Variable> getVariables() {
        return getVariables(this);
    }
}
