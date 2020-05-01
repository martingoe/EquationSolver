package com.cubearrow.model.operations;

import com.cubearrow.model.operations.utils.MultiplicationLikeOperationUtils;
import com.cubearrow.model.tree.Node;
import com.cubearrow.model.tree.Number;

public class Division extends Operation {
    public final static int PRIORITY = 2;
    public final static String OPERATION_STRING = "/";

    public Division(Node left, Node right, Node parent) {
        super(left, right, parent);
    }

    public Division() {
    }

    public Division(Node left, Node right) {
        super(left, right, null);
    }

    @Override
    public Number getResultFromNumbers(Number n1, Number n2) {
        return new Number(n1.getValue() / n2.getValue(), this.getParent());
    }

}
