package model.operations;

import model.operations.utils.MultiplicationLikeOperationUtils;
import model.tree.Node;
import model.tree.Number;

public class Multiplication extends Operation {

    final Class OPPOSITE_OPERATION = Division.class;

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
