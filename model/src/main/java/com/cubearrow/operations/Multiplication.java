package main.java.com.cubearrow.operations;

import main.java.com.cubearrow.operations.utils.MultiplicationLikeOperationUtils;
import main.java.com.cubearrow.tree.Node;
import main.java.com.cubearrow.tree.Number;

public class Multiplication extends Operation {

    final Class OPPOSITE_OPERATION = Division.class;
    public final static String OPERATION_STRING = "*";

    public Multiplication(Node left, Node right) {
        super(left, right);
    }


    @Override
    public Node simplify() {
        return MultiplicationLikeOperationUtils.distributiveLaw(this);
    }


    @Override
    public Number getResultFromNumbers(Number n1, Number n2) {
        return new Number(n1.getNumber() * n2.getNumber());
    }

    @Override
    public Node applyToNode(Node node) {
        return MultiplicationLikeOperationUtils.applyToNode(node, this);
    }
}
