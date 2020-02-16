package main.java.com.cubearrow.equation;

import main.java.com.cubearrow.operations.Operation;
import main.java.com.cubearrow.regex.OperationSelector;
import main.java.com.cubearrow.regex.RegExUtilities;
import main.java.com.cubearrow.tree.Node;
import main.java.com.cubearrow.tree.Number;
import main.java.com.cubearrow.tree.Variable;

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
        return new Equation(parseNode(split[0]), parseNode(split[1]));
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
    @SuppressWarnings("unchecked")
    private static Operation parseOperation(String operation) {

        int startingIndex = 0;
        if (operation.startsWith("(")) {
            startingIndex = getLastIndexOfFirstBrackets(operation);
        }

        int operationIndex = RegExUtilities.getFirstSubstring(operation, OPERATION_REGEX, startingIndex);
        Class<Operation> operationClass = OperationSelector.getOperationFromOperationString(String.valueOf(operation.charAt(operationIndex)));

        Node left = parseNode(removeBracketsFromOperationIfNecessary(operation.substring(0, operationIndex)));
        Node right = parseNode(
                removeBracketsFromOperationIfNecessary(operation.substring(operationIndex + 1)));

        try {
            return operationClass.getDeclaredConstructor(Node.class, Node.class).newInstance(left, right);
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Removes the brackets from a String that is to be parsed to an {@link Operation}
     *
     * @param operationString The operationString whose brackets should be removed
     * @return Returns the new String that contains the Operation
     */
    private static String removeBracketsFromOperationIfNecessary(String operationString) {
        if (operationString.startsWith("(")) {
            return operationString.substring(1, operationString.length() - 1);
        }
        return operationString;
    }

    /**
     * Parses a {@link Node} from a {@link String} by trying to parse every option of a {@link Node}
     *
     * @param string The {@link String} that contains the {@link Node}
     * @return Returns the parsed Node
     */
    private static Node parseNode(String string) {
        Number n = parseNumber(string);
        if (n != null) {
            return n;
        }

        Variable v = parseVariable(string);
        if (v != null) {
            return v;
        }

        return parseOperation(string);
    }

    /**
     * Gets the index of the closing bracket of the first starting bracket.
     * Example:
     * ((4+2)*3)-(3+2)
     * Returns: 8
     *
     * @param equationPart The {@link String} that contains the part of the {@link Equation} with the brackets
     * @return Returns the index of the closing bracket
     */
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
        return -1;
    }
}
