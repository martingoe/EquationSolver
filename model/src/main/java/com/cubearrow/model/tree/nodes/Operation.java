package com.cubearrow.model.tree.nodes;


import com.cubearrow.model.problem.Problem;
import com.cubearrow.model.rewriting.EquationRewriter;
import com.cubearrow.model.tree.Node;
import com.cubearrow.model.tree.interfaces.Simplifyable;
import com.cubearrow.model.utils.ProblemInitializationUtil;
import com.cubearrow.model.utils.regex.RegExUtil;

import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.List;

/**
 * The base class for an operation, every specific operation derives from this.
 */
public abstract class Operation extends Node implements Simplifyable {
    private static final List<String> OPERATION_REGEXES = Arrays.asList("\\+|(?<=[0-9]|[a-z]|\\))-", "\\*", "\\/", "\\^", "sin|cos|tan");

    public Operation(Node left, Node right, Node parent) {
        super(left, right, parent);
    }

    public Operation() {
        super(null, null, null);
    }

    /**
     * Parses an operation from a String
     *
     * @param string The string from which the Operation is parsed
     * @param parent The parent of the Operation
     */
    public Operation(String string, char operationChar, Node parent) {
        super(null, null, parent);

        int startingIndex = getOperationStartingIndex(string);
        int operationIndex = string.indexOf(operationChar, startingIndex);

        String[] operationSides = {string.substring(0, operationIndex), string.substring(operationIndex + 1)};
        this.setLeft(Node.fromString(ProblemInitializationUtil.removeBracketsFromOperationIfNecessary(operationSides[0]), this));
        this.setRight(Node.fromString(ProblemInitializationUtil.removeBracketsFromOperationIfNecessary(operationSides[1]), this));
    }

    /**
     * Parses an {@link Operation} from a String.
     * This function calls the recursive part when a part of the parsed Operation is an operation itself.
     *
     * @param operation The String of the operation
     * @param parent    The parent of the Operation
     * @return Returns a instance of {@link Operation}
     */
    public static Operation fromString(String operation, Node parent) {
        try {
            int startingIndex = getOperationStartingIndex(operation);

            String operationString = getOperationString(operation, startingIndex);

            Class<? extends Operation> operationClass = ProblemInitializationUtil.operationSelector.getOperationFromOperationString(operationString);

            return operationClass.getConstructor(String.class, Node.class).newInstance(operation, parent);
        } catch (NoSuchMethodException | InvocationTargetException | IllegalAccessException | InstantiationException e) {
            e.printStackTrace();
        }
        return null;
    }

    private static String getOperationString(String operation, int startingIndex) {
        for (String operationRegex : OPERATION_REGEXES) {
            String operationString = RegExUtil.getFirstSubstring(operation, operationRegex + "(?![^\\(]*\\))", startingIndex);
            if (operationString != null) {
                return operationString;
            }
        }
        return "";
    }

    /**
     * Gets the starting index from which the operation Strings are to be parsed by finding if the left part starts with brackets.
     *
     * @param operationString The operation String on which to test for brackets in the left parts of the operation
     * @return Returns the starting index from which to parse for operationStrings
     */
    private static int getOperationStartingIndex(String operationString) {
        if (operationString.charAt(0) == '(') {
            return ProblemInitializationUtil.getLastIndexOfFirstBrackets(operationString) - 1;
        }
        return 0;
    }

    /**
     * Gets the result of the operation when it's two numbers.
     *
     * @return Returns the {@link Number} instance, if no result was able to be created, returns null
     */
    public Node getResult() {
        try {
            if (this.getLeft() instanceof Number && this.getRight() instanceof Number) {
                return getResultFromNumbers();
            }
        } catch (ClassCastException exception) {
            return null;
        }
        return this;
    }

    public abstract Number getResultFromNumbers();


    /**
     * Returns the operation in a {@link String} representation
     *
     * @return The String instance that represents the operation
     */
    @Override
    public String toString() {
        try {
            return String.format("(%s%s%s)", this.getLeft().toString(), this.getClass().getDeclaredField("OPERATION_STRING").get(this), this.getRight().toString());
        } catch (NoSuchFieldException | IllegalAccessException e) {
            
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Simplifies a very basic operation. It does not use the simplify function of the operation itself
     *
     * @param problem The problem in which the Operation is, needed for the variable to isolate
     * @return Returns the {@link Node} that represent the simplified operation
     */
    @Override
    public Node simplify(EquationRewriter equationRewriter, Problem problem) {
        Node result = equationRewriter.applyRulesToOperation(this, problem);

        if (result.getLeft() instanceof Operation leftOperation)
            result.setLeft(leftOperation.simplify(equationRewriter, problem));
        if (result.getRight() instanceof Operation rightOperation)
            result.setRight(rightOperation.simplify(equationRewriter, problem));

        return ((Operation) result).getResult();
    }
}
