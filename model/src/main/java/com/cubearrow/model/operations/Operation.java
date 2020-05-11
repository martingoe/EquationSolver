package com.cubearrow.model.operations;


import com.cubearrow.model.equation.EquationInitializer;
import com.cubearrow.model.regex.RegExUtil;
import com.cubearrow.model.tree.Node;
import com.cubearrow.model.tree.Number;

import java.lang.reflect.InvocationTargetException;

/**
 * The base class for an operation, every specific operation derives from this.
 */
public abstract class Operation extends Node {
    private static final String OPERATION_REGEX = "\\+|\\*|\\-|\\/|\\^";

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
    protected Operation(String string, char operationChar, Node parent) {
        super(null, null, parent);

        int startingIndex = getOperationStartingIndex(string);
        int operationIndex = string.indexOf(operationChar, startingIndex);

        String[] operationSides = {string.substring(0, operationIndex), string.substring(operationIndex + 1)};
        this.setLeft(Node.fromString(EquationInitializer.removeBracketsFromOperationIfNecessary(operationSides[0]), this));
        this.setRight(Node.fromString(EquationInitializer.removeBracketsFromOperationIfNecessary(operationSides[1]), this));
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

            int operationIndex = RegExUtil.getStartingIndexOfFirstSubstring(operation, OPERATION_REGEX, startingIndex);
            Class<? extends Operation> operationClass = EquationInitializer.operationSelector.getOperationFromOperationString(String.valueOf(operation.charAt(operationIndex)));

            return operationClass.getConstructor(String.class, Node.class).newInstance(operation, parent);
        } catch (NoSuchMethodException | InvocationTargetException | IllegalAccessException | InstantiationException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Gets the starting index from which the operation Strings are to be parsed by finding if the left part starts with brackets.
     *
     * @param operationString The operation String on which to test for brackets in the left parts of the operation
     * @return Returns the starting index from which to parse for operationStrings
     */
    private static int getOperationStartingIndex(String operationString) {
        if (operationString.charAt(0) == '(') {
            return EquationInitializer.getLastIndexOfFirstBrackets(operationString) - 1;
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
            if (this.getLeft() instanceof Number leftNumber && this.getRight() instanceof Number rightNumber) {
                return getResultFromNumbers(leftNumber, rightNumber);
            }
        } catch (ClassCastException exception) {
            return null;
        }
        return this;
    }

    public abstract Number getResultFromNumbers(Number n1, Number n2);


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
}
