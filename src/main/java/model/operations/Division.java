package model.operations;

import model.operations.utils.MultiplicationLikeOperationUtils;
import model.tree.Node;
import model.tree.Number;
import model.tree.utils.NodeUtilities;

public class Division extends Operation {

    public Division(Node left, Node right) {
        super(left, right);
    }

    @Override
    public Node simplify() {
        Node result = MultiplicationLikeOperationUtils.distributiveLaw(this);
        if (NodeUtilities.ifNodeEquals(this.getRight(), this.getLeft())){
            return new Number(1f);
        }
        return result;
    }

    @Override
    public Number getResultFromNumbers(Number n1, Number n2) {
        return new Number(n1.getNumber() / n2.getNumber());
    }

    @Override
    public Node applyToNode(Node node) {
        return MultiplicationLikeOperationUtils.applyToNode(node, this);
    }
}
