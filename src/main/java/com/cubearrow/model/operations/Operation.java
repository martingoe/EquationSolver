package com.cubearrow.model.operations;


import com.cubearrow.model.tree.Node;
import com.cubearrow.model.tree.Number;

public abstract class Operation extends Node {

    public Operation(Node left, Node right) {
        super(left, right);
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
            return String.format("(%s%s%s)", this.getLeft().toString(), this.getClass().getDeclaredField("operationString").get(this), this.getRight().toString());
        } catch (NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
            return null;
        }
    }
}
