package model.operations;

import model.tree.Node;
import model.tree.Number;

import java.util.List;

public class Division extends Node implements Operation {


    public Division(Node right, Node left) {
        super(right, left);
    }

    @Override
    public void simplify() {

    }

    @Override
    public Number getResultFromNumbers(Number n1, Number n2) {
        return new Number(n1.getNumber() / n2.getNumber());
    }
}
