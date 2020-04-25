package com.cubearrow.model.operations;

import com.cubearrow.model.tree.Node;
import com.cubearrow.model.tree.Number;

public class Subtraction extends Operation {
    public final static int PRIORITY = 1;
    public final static String OPERATION_STRING = "-";
    final java.lang.Class OPPOSITE_OPERATION = Addition.class;

    public Subtraction(Node left, Node right, Node parent) {
        super(left, right, parent);
    }

    public Subtraction() {
    }

    public Subtraction(Node left, Node right) {
        super(left, right, null);
    }

    @Override
    public Number getResultFromNumbers(Number n1, Number n2) {
        return new Number(n1.getValue() - n2.getValue(), this.getParent());
    }

    @Override
    public Node applyToNode(Node node) {
        setLeft(node);
        return this;
    }
}
