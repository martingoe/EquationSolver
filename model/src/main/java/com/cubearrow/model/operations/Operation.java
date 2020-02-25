package com.cubearrow.model.operations;


import com.cubearrow.model.tree.Node;
import com.cubearrow.model.tree.Number;

public abstract class Operation extends Node {

    public Operation(Node left, Node right, Node parent) {
        super(left, right, parent);
    }


    public Operation() {
        super(null, null, null);
    }

    public abstract Node simplify();

    public Number getResult() {
        return getResultFromNumbers((Number) this.getLeft(), (Number) this.getRight());
    }
    public abstract Number getResultFromNumbers(Number n1, Number n2);

    public abstract Node applyToNode(Node node);

    @Override
    public String toString(){
        try {
            return String.format("(%s%s%s)", this.getLeft().toString(), this.getClass().getDeclaredField("OPERATION_STRING").get(this), this.getRight().toString());
        } catch (NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
            return null;
        }
    }
}
