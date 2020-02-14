package com.cubearrow.model.equation;

import com.cubearrow.model.tree.Node;

public class EquationPrinter {
    public static String equationToString(Equation equation){
        return printNode(equation.getLeft()) + "=" + printNode(equation.getRight());
    }

    static private String printNode(Node node){
        return node.toString();
    }
}
