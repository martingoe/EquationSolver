package com.cubearrow.model.operations;

import com.cubearrow.model.tree.Node;
import com.cubearrow.model.tree.Number;
import com.cubearrow.model.tree.Variable;

public class Multiplication extends Operation {
    public static final char OPERATION_STRING = '*';

    public Multiplication(String string, Node parent) {
        super(string, OPERATION_STRING, parent);
    }

    public Multiplication(Node left, Node right, Node parent) {
        super(left, right, parent);
    }

    public Multiplication(Node left, Node right) {
        super(left, right, null);
    }

    public Multiplication() {
    }


    @Override
    public String toString() {
        if(this.getLeft() instanceof Variable || this.getRight() instanceof Variable){
            return "(%s%s)".formatted(this.getLeft().toString(), this.getRight().toString());
        }

        return super.toString();
    }

    @Override
    public Number getResultFromNumbers(Number n1, Number n2) {
        return new Number(n1.getValue() * n2.getValue(), this.getParent());
    }

}
