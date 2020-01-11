package model.operations;

import model.tree.Node;
import model.tree.Number;

public class Subtraction extends Operation {

    final Class OPPOSITE_OPERATION = Addition.class;

    public Subtraction(Node left, Node right) {
        super(left, right);
    }


    @Override
    public Node simplify() {
        return null;
    }

    @Override
    public Number getResultFromNumbers(Number n1, Number n2){
        return new Number(n1.getNumber() - n2.getNumber());
    }

    @Override
    public Node applyToNode(Node node) {
        return null;
    }


}
