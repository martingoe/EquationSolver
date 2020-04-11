package com.cubearrow.model.operations;

import com.cubearrow.model.operations.utils.MultiplicationLikeOperationUtils;
import com.cubearrow.model.tree.Node;
import com.cubearrow.model.tree.Number;

public class Multiplication extends Operation {
    public final static int PRIORITY = 2;
    public final static String OPERATION_STRING = "*";
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
    public Node simplify() {
        return MultiplicationLikeOperationUtils.distributiveLaw(this);
    }


    @Override
    public Number getResultFromNumbers(Number n1, Number n2) {
        return new Number(n1.getNumber() * n2.getNumber(), this.getParent());
    }

    @Override
    public Node applyToNode(Node node) {
        return MultiplicationLikeOperationUtils.applyToNode(node, this);
    }
}
