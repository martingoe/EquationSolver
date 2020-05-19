package com.cubearrow.model.tree.nodes.operations;

import com.cubearrow.model.tree.nodes.Operation;
import com.cubearrow.model.tree.Node;
import com.cubearrow.model.tree.nodes.Number;

public class Subtraction extends Operation {
    public final static char OPERATION_STRING = '-';

    public Subtraction(String string, Node parent) {
        super(string, OPERATION_STRING, parent);
    }

    public Subtraction(Node left, Node right, Node parent) {
        super(left, right, parent);
    }

    public Subtraction() {
    }



    public Subtraction(Node left, Node right) {
        super(left, right, null);
    }

    @Override
    public Number getResultFromNumbers() {
        return new Number((double) this.getLeft().getValue() - (double) this.getRight().getValue(), this.getParent());
    }

}
