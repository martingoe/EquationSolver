package com.cubearrow.model.operations;

import com.cubearrow.model.operations.utils.MultiplicationLikeOperationUtils;
import com.cubearrow.model.tree.Node;
import com.cubearrow.model.tree.Number;

public class Multiplication extends Operation {
    public static final int PRIORITY = 2;
    public static final String OPERATION_STRING = "*";
    final Class OPPOSITE_OPERATION = Division.class;

    public Multiplication(Node left, Node right, Node parent) {
        super(left, right, parent);
    }
    public Multiplication(Node left, Node right) {
        super(left, right, null);
    }

    public Multiplication() {
    }


    @Override
    public Number getResultFromNumbers(Number n1, Number n2) {
        return new Number(n1.getValue() * n2.getValue(), this.getParent());
    }

}
