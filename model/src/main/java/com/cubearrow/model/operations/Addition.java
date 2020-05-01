package com.cubearrow.model.operations;

import com.cubearrow.model.tree.Node;
import com.cubearrow.model.tree.Number;

public class Addition extends Operation {
    public static final int PRIORITY = 1;
    public final static String OPERATION_STRING = "+";
    final java.lang.Class OPPOSITE_OPERATION = Subtraction.class;

    public Addition() {
    }

    public Addition(Node left, Node right, Node parent) {
        super(left, right, parent);
    }

    public Addition(Node left, Node right) {
        super(left, right, null);
    }


    @Override
    public Number getResultFromNumbers(Number n1, Number n2) {
        return new Number(n1.getValue() + n2.getValue(), this.getParent());
    }

}
