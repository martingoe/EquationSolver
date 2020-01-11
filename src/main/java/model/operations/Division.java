package model.operations;

import model.tree.Node;
import model.tree.Number;

public class Division extends Operation {


    public Division(Node left, Node right) {
        super(left, right);
    }

    @Override
    public Node simplify() {
        return null;
    }

    @Override
    public Number getResultFromNumbers(Number n1, Number n2) {
        return new Number(n1.getNumber() / n2.getNumber());
    }

    @Override
    public Node applyToNode(Node node) {
        return null;
    }
}
