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

    @Override
    public Node simplify() {
        if (NodeUtilities.ifNodeEquals(this.getRight(), this.getLeft())){
            return new Number(1f, this.getParent());
        }
        return this;
    }

    @Override
    public Number getResultFromNumbers(Number n1, Number n2) {
        return new Number(n1.getNumber() / n2.getNumber(), this.getParent());
    }

    @Override
    public Node applyToNode(Node node) {
        return MultiplicationLikeOperationUtils.applyToNode(node, this);
    }
}
