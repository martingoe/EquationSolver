package model.tree.utils;

import model.operations.Operation;
import model.tree.Node;
import model.tree.Number;
import model.tree.Variable;

public class NodeUtilities {
    public static boolean ifNodeEquals(Node actual, Node expected) {
        if (actual instanceof Operation && expected instanceof Operation)
            return ifNodeEquals(actual, expected);
        else if (actual instanceof Variable && expected instanceof Variable)
            return ((Variable) actual).getVariableName() == ((Variable) expected).getVariableName();
        else if (actual instanceof Number && expected instanceof Number)
            return ((Number) actual).getNumber().equals(((Number) expected).getNumber());
        return false;
    }
}
