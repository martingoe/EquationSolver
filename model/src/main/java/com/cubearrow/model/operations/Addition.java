package com.cubearrow.model.operations;


import com.cubearrow.model.operations.utils.MultiplicationLikeOperationUtils;
import com.cubearrow.model.tree.Node;
import com.cubearrow.model.tree.Number;
import com.cubearrow.model.tree.Variable;

public class Addition extends Operation {
    public static final int PRIORITY = 1;
    public final static String OPERATION_STRING = "+";
    final Class OPPOSITE_OPERATION = Subtraction.class;

    public Addition() {
    }

    public Addition(Node left, Node right, Node parent) {
        super(left, right, parent);
    }

    public Addition(Node left, Node right) {
        super(left, right, null);
    }


    @Override
    public Node simplify() {
        if (this.getLeft() instanceof Number left && this.getRight() instanceof Number right) {
            return getResultFromNumbers(right, left);
        }

        return simplifyIfMultiplication();
    }

    private Node simplifyIfMultiplication() {
        Node left = this.getLeft();
        if (left instanceof Variable) {
            left = new Multiplication(new Number(1f, null), left, null);
        }
        Node right = this.getRight();
        if (right instanceof Variable) {
            right = new Multiplication(new Number(1f, null), right, null);
        }

        if (left instanceof Multiplication && right instanceof Multiplication) {
            Node simplifiedAddition = MultiplicationLikeOperationUtils.simplifyAddition(new Addition(left, right, null), this.getParent());
            if (simplifiedAddition != null) {
                return simplifiedAddition;
            }
        }
        return this;
    }

    @Override
    public Number getResultFromNumbers(Number n1, Number n2) {
        return new Number(n1.getNumber() + n2.getNumber(), this.getParent());
    }

    @Override
    public Node applyToNode(Node node) {
        setLeft(node);
        return this;
    }
}
