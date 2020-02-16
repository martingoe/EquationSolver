package main.java.com.cubearrow.operations;

import main.java.com.cubearrow.tree.Node;
import main.java.com.cubearrow.tree.Number;

public class Addition extends Operation {
    public final static String OPERATION_STRING = "+";
    final Class OPPOSITE_OPERATION = Subtraction.class;

    public Addition(Node left, Node right) {
        super(left, right);
    }


    @Override
    public Node simplify() {
        return this;
    }

    @Override
    public Number getResultFromNumbers(Number n1, Number n2) {
        return new Number(n1.getNumber() + n2.getNumber());
    }

    @Override
    public Node applyToNode(Node node) {
        setLeft(node);
        return this;
    }
}
