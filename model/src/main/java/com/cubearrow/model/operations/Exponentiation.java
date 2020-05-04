package com.cubearrow.model.operations;

import com.cubearrow.model.tree.Node;
import com.cubearrow.model.tree.Number;

public class Exponentiation extends Operation {

    public final static String OPERATION_STRING = "^";

    public Exponentiation(Node left, Node right, Node parent) {
        super(left, right, parent);
    }

    public Exponentiation() {
    }



    @Override
    public Number getResultFromNumbers(Number n1, Number n2) {
        return new Number((float) Math.pow(n1.getValue(), n2.getValue()), this.getParent());
    }
}
