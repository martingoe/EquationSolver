package model.equation;

import model.operations.Operation;
import model.regex.OperationSelector;
import model.regex.RegExUtilities;
import model.tree.Node;
import model.tree.Number;
import model.tree.Variable;

import java.lang.reflect.InvocationTargetException;

public class EquationInitializer {
    private static final String OPERATION_REGEX = "\\+|\\*|\\-|\\/";

    /**
     * Initializes an equation based on a String.
     * It uses recursion to get the information in the brackets of the operation and generates a
     *
     * @param equationString The String that hold the equation, both sides are separated with a "="
     * @return Returns an {@link Equation} instance with the information
     */
    public static Equation parseEquation(String equationString) {
        String[] split = equationString.split("=");
        return new Equation(parseOperationOrNumberOrVariable(split[0]), parseOperationOrNumberOrVariable(split[1]));
    }

    /**
     * Parses a Float from a String and returns it in a {@link Number}.
     *
     * @param numberString The String that holds the Number. Can have the following formats: "4", "4.0"
     * @return Returns the parsed number in a {@link Number}. If it does not have the right format, it returns null.
     */
    private static Number parseNumber(String numberString) {
        try {
            return new Number(Float.valueOf(numberString));
        } catch (NumberFormatException e) {
            return null;
        }
    }


    /**
     * Parses a {@link Variable} from a String
     *
     * @param numberString The String that contains the char which represents the Variable as a String.
     * @return Returns the generated {@link Variable}. If the input is more than one char and is not a lowercase character it returns null
     */
    private static Variable parseVariable(String numberString) {
        if (numberString.length() > 1) return null;

        if (numberString.matches("[a-z]")) {
            return new Variable(numberString.toCharArray()[0]);
        }
        return null;
    }


    /**
     * Parses an {@link Operation} from a String.
     * This function calls the recursive part when a part of the parsed Operation is an operation itself.
     *
     * @param operation The String of the operation
     * @return Returns a instance of {@link Operation}
     */
    private static Operation parseOperation(String operation) {

        int startingIndex = 0;
        if (operation.startsWith("(")) {
            startingIndex = getLastIndexOfFirstBrackets(operation);
        }

        int operationIndex = RegExUtilities.getFirstSubstring(operation, OPERATION_REGEX, startingIndex);
        Class operationClass = OperationSelector.getOperationFromOperationString(operation.charAt(operationIndex));

        Node left = parseOperationOrNumberOrVariable(removeBracketsFromOperationIfNecessary(operation.substring(0, operationIndex)));
        Node right = parseOperationOrNumberOrVariable(
                removeBracketsFromOperationIfNecessary(operation.substring(operationIndex + 1)));

        try {
            return (Operation) operationClass.getDeclaredConstructor(Node.class, Node.class).newInstance(right, left);
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
            e.printStackTrace();
        }
        return null;
    }

    private static String removeBracketsFromOperationIfNecessary(String operation) {
        if (operation.startsWith("(")) {
            return operation.substring(1, operation.length() - 1);
        }
        return operation;
    }

    private static Node parseOperationOrNumberOrVariable(String string) {
        Number n = parseNumber(string);
        if (n != null) {
            return n;
        }

        Variable v = parseVariable(string);
        if (v != null) {
            return v;
        }

        return (Node) parseOperation(string);
    }

    private static int getLastIndexOfFirstBrackets(String equationPart) {
        int bracketAmount = 0;
        for (int i = 0; i < equationPart.length(); i++) {
            char charAtIndex = equationPart.charAt(i);
            if (charAtIndex == '(') {
                bracketAmount++;
            } else if (charAtIndex == ')') {
                bracketAmount--;
                if (bracketAmount == 0) {
                    return i + 1;
                }
            }

        }
        return 0;
    }
}
