package com.cubearrow.model.tree.nodes.operations;

import com.cubearrow.model.tree.Node;
import com.cubearrow.model.tree.nodes.Number;
import com.cubearrow.model.tree.nodes.Operation;

public class Tan extends Operation {
    public static final String OPERATION_STRING = "tan";
    public static final boolean USE_RADIANS = true;

    public Tan(String string, Node parent) {
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
        return "tan(%s)".formatted(this.getLeft().toString());
    }

    @Override
    public Number getResultFromNumbers() {
        double leftValue = (double) this.getLeft().getValue();
        if (USE_RADIANS) {
            return new Number(Math.tan(leftValue));
        } else {
            return new Number(Math.tan(Math.toDegrees(leftValue)));
        }
    }
}
