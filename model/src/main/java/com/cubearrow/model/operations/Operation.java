package com.cubearrow.model.operations;


import com.cubearrow.model.equation.EquationInitializer;
import com.cubearrow.model.regex.OperationSelector;
import com.cubearrow.model.regex.RegExUtilities;
import com.cubearrow.model.tree.Node;
import com.cubearrow.model.tree.Number;

import java.lang.reflect.InvocationTargetException;

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
     * @param parent The parent of the Operation
     * @return Returns a instance of {@link Operation}
     */
    public static Operation fromString(String operation, Node parent) {
        try {
            int startingIndex = 0;
            if (operation.startsWith("(")) {
                startingIndex = EquationInitializer.getLastIndexOfFirstBrackets(operation) - 1;
            }

            int operationIndex = RegExUtilities.getFirstSubstring(operation, OPERATION_REGEX, startingIndex);
            Class<Operation> operationClass = new OperationSelector().getOperationFromOperationString(String.valueOf(operation.charAt(operationIndex)));

            String[] operationSides = operation.split(String.format("\\%s", operationClass.getDeclaredField("OPERATION_STRING").get(null)));
            Operation result = operationClass.getDeclaredConstructor().newInstance();
            result.setLeft(Node.fromString(EquationInitializer.removeBracketsFromOperationIfNecessary(operationSides[0]), result));
            result.setRight(Node.fromString(EquationInitializer.removeBracketsFromOperationIfNecessary(operationSides[1]), result));
            result.setParent(parent);
            return result;
        } catch (NoSuchFieldException | NoSuchMethodException | InstantiationException | InvocationTargetException | IllegalAccessException e) {
            e.printStackTrace();
        }
        return null;
    }

    public abstract Node simplify();

    public Number getResult() {
        return getResultFromNumbers((Number) this.getLeft(), (Number) this.getRight());
    }

    public abstract Number getResultFromNumbers(Number n1, Number n2);

    public abstract Node applyToNode(Node node);

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
