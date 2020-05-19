package com.cubearrow.model.tree.nodes.operations;

import com.cubearrow.model.tree.nodes.Operation;
import com.cubearrow.model.tree.Node;
import com.cubearrow.model.tree.nodes.Number;
import com.cubearrow.model.tree.nodes.Variable;

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
    public Number getResultFromNumbers() {
        return new Number((double) this.getLeft().getValue() * (double) this.getRight().getValue(), this.getParent());
    }

}
