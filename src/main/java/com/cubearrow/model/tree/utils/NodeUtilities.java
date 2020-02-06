package com.cubearrow.model.tree.utils;

import com.cubearrow.model.equation.Equation;
import com.cubearrow.model.operations.Operation;
import com.cubearrow.model.tree.Node;
import com.cubearrow.model.tree.Number;
import com.cubearrow.model.tree.Variable;

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
