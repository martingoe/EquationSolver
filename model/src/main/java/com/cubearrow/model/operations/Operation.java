package com.cubearrow.model.operations;


import com.cubearrow.model.equation.EquationInitializer;
import com.cubearrow.model.regex.RegExUtilities;
import com.cubearrow.model.tree.Node;
import com.cubearrow.model.tree.Number;

import java.lang.reflect.InvocationTargetException;

/**
 * The base class for an operation, every specific operation derives from this.
 */
public abstract class Operation extends Node {
    public static final int PRIORITY = -1;
    private static final String OPERATION_REGEX = "\\+|\\*|\\-|\\/";

    public Operation(Node left, Node right, Node parent) {
        super(left, right, parent);
    }


    public Operation() {
        super(null, null, null);
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
            int startingIndex = getStartingIndex(operation);

            int operationIndex = RegExUtilities.getStartingIndexOfFirstSubstring(operation, OPERATION_REGEX, startingIndex);
            Class<Operation> operationClass = EquationInitializer.operationSelector.getOperationFromOperationString(String.valueOf(operation.charAt(operationIndex)));

            String[] operationSides = operation.split(String.format("\\%s", operationClass.getDeclaredField("OPERATION_STRING").get(null)));
            return parseOperationFromOperationSides(operationSides, operationClass, parent);
        } catch (NoSuchFieldException | NoSuchMethodException | InstantiationException | InvocationTargetException | IllegalAccessException e) {
            e.printStackTrace();
        }
        return null;
    }

    private static int getStartingIndex(String operation) {
        int startingIndex = 0;
        if (operation.startsWith("(")) {
            startingIndex = EquationInitializer.getLastIndexOfFirstBrackets(operation) - 1;
        }
        return startingIndex;
    }

    /**
     * Parses an Operation from two operation sides and the class type of the Operation
     *
     * @param operationSides The two operation sides as String in an array of strings
     * @param operationClass The class of the operation to be generated
     * @param parent         The parent of the new operation
     * @return Returns an instance of the newly parsed Operation
     * @throws NoSuchMethodException     Thrown because theoretically the needed constructor could be missing
     * @throws IllegalAccessException    Can be thrown when the constructor needed is not accessible from this class
     * @throws InvocationTargetException Thrown if the constructor of the operation throws an error
     * @throws InstantiationException    Thrown if the new instance can not be initiated,
     *                                   can be thrown because of multiple reasons, see {@link InstantiationException}
     */
    private static Operation parseOperationFromOperationSides(String[] operationSides, Class<Operation> operationClass, Node parent)
            throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        Operation result = operationClass.getDeclaredConstructor().newInstance();
        result.setLeft(Node.fromString(EquationInitializer.removeBracketsFromOperationIfNecessary(operationSides[0]), result));
        result.setRight(Node.fromString(EquationInitializer.removeBracketsFromOperationIfNecessary(operationSides[1]), result));
        result.setParent(parent);
        return result;
    }

    /**
     * Gets the result of the operation when it's two numbers.
     *
     * @return Returns the {@link Number} instance, if no result was able to be created, returns null
     */
    public Number getResult() {
        try {
            return getResultFromNumbers((Number) this.getLeft(), (Number) this.getRight());
        } catch (ClassCastException exception) {
            return null;
        }
    }

    public abstract Number getResultFromNumbers(Number n1, Number n2);

    public abstract Node applyToNode(Node node);

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
