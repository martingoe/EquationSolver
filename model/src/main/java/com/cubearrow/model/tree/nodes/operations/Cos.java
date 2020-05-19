package com.cubearrow.model.tree.nodes.operations;

import com.cubearrow.model.tree.nodes.Number;
import com.cubearrow.model.tree.nodes.Operation;
import com.cubearrow.model.tree.Node;

public class Cos extends Operation {
    public static final String OPERATION_STRING = "cos";
    public static final boolean USE_RADIANS = true;
    public Cos(String string, Node parent) {
        super(null, null, parent);
        this.setLeft(Node.fromString(string.substring(4, string.length() - 1), this));
    }

    @Override
    public Node getResult() {
        if (this.getLeft() instanceof Number) {
            return getResultFromNumbers();
        }
        return this;
    }

    @Override
    public String toString() {
        return "cos(%s)".formatted(this.getLeft().toString());
    }

    @Override
    public Number getResultFromNumbers() {
        double leftValue = (double) this.getLeft().getValue();
        if(USE_RADIANS) {
            return new Number(Math.cos(leftValue));
        }else {
            return new Number(Math.cos(Math.toDegrees(leftValue)));
        }
    }
}
