package com.cubearrow.model.tree.nodes;

import com.cubearrow.model.rewriting.EquationRewriter;
import com.cubearrow.model.tree.Node;
import com.cubearrow.model.problem.Problem;
import com.cubearrow.model.tree.interfaces.Simplifyable;
import com.cubearrow.model.utils.ProblemInitializationUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Equation extends Node implements Cloneable, Simplifyable {
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
    @Override
    public Node simplify(EquationRewriter equationRewriter, Problem problem) {
        if (this.getLeft() instanceof Operation leftOperation) {
            this.setLeft(leftOperation.simplify(equationRewriter, problem));
        }
        if (this.getRight() instanceof Operation rightOperation) {
            this.setRight(rightOperation.simplify(equationRewriter, problem));
        }
        return simplifyEquation(equationRewriter, problem);
    }

    private Equation simplifyEquation(EquationRewriter equationRewriter, Problem problem) {
        return equationRewriter.applyRulesToEquation(this, problem);
    }



    /**
     * Initializes an equation based on a String.
     * It uses recursion to get the information in the brackets of the operation and generates a
     *
     * @return Returns an {@link Equation} instance with the information
     */
    public static Equation fromString(String string){
        String equationString = ProblemInitializationUtil.cleanOperationString(string);
        String[] split = equationString.split("=");
        Equation equation = new Equation();
        equation.setLeft(Node.fromString(split[0], equation));
        equation.setRight(Node.fromString(split[1], equation));
        return equation;
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
