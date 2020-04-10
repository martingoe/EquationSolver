package com.cubearrow.model.operations;

import com.cubearrow.model.tree.Node;
import com.cubearrow.model.tree.Number;

public class Subtraction extends Operation {
    public final static int PRIORITY = 1;
    public final static String OPERATION_STRING = "-";
    final Class OPPOSITE_OPERATION = Addition.class;

    public Subtraction(Node left, Node right, Node parent) {
        super(left, right, parent);
    }

    public Subtraction() {
    }

    @Override
    public Node simplify() {
        return this;
    }

    @Override
    public Number getResultFromNumbers(Number n1, Number n2) {
        return new Number(n1.getNumber() - n2.getNumber(), this.getParent());
    }

    @Override
    public Node applyToNode(Node node) {
        setLeft(node);
        return this;
    }
}
