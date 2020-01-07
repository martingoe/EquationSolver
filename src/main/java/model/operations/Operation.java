package model.operations;


import model.tree.Node;
import model.tree.Number;

public class Operation extends Node {
    public Operation(Node right, Node left) {
        super(right, left);
    }


    public Node simplify(){
        return null;
    }

    public Number getResult() {
        return getResultFromNumbers((Number) this.getLeft(), (Number) this.getRight());
    }
    public Number getResultFromNumbers(Number n1, Number n2) {
        return null;
    }

}
