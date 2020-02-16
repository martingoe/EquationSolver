package main.java.com.cubearrow.tree.utils;

import main.java.com.cubearrow.equation.Equation;
import main.java.com.cubearrow.operations.Operation;
import main.java.com.cubearrow.tree.Node;
import main.java.com.cubearrow.tree.Number;
import main.java.com.cubearrow.tree.Variable;

public class NodeUtilities {
    public static boolean ifNodeEquals(Node actual, Node expected) {
        if (actual instanceof Operation && expected instanceof Operation || actual instanceof Equation && expected instanceof Equation)
            return ifNodeEquals(actual.getRight(), expected.getRight()) && ifNodeEquals(actual.getLeft(), expected.getLeft());
        else if (actual instanceof Variable && expected instanceof Variable)
            return ((Variable) actual).getVariableName() == ((Variable) expected).getVariableName();
        else if (actual instanceof Number && expected instanceof Number)
            return ((Number) actual).getNumber().equals(((Number) expected).getNumber());
        return false;
    }
}
