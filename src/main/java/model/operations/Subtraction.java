package model.operations;

import model.tree.Node;
import model.tree.Number;

public class Subtraction extends Node implements Operation {

    final Class OPPOSITE_OPERATION = Addition.class;

    public Subtraction(Node right, Node left) {
        super(right, left);
    }


    @Override
    public void simplify() {

    }

    @Override
    public Number getResultFromNumbers(Number n1, Number n2) {
        return new Number(n1.getNumber() - n2.getNumber());
    }
}
