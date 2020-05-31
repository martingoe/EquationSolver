package com.cubearrow.model.tree.nodes.operations;

import com.cubearrow.model.problem.Problem;
import com.cubearrow.model.tree.nodes.Operation;
import com.cubearrow.model.tree.Node;
import com.cubearrow.model.tree.nodes.Number;

public class Division extends Operation {

    public final static char OPERATION_STRING = '/';

    public Division(String string, Node parent) {
        super(string, OPERATION_STRING, parent);
    }

    public Division(Node left, Node right, Node parent) {
        super(left, right, parent);
    }

    public Division() {
    }

    public Division(Node left, Node right) {
        super(left, right, null);
    }


    @Override
    public Number getResultFromNumbers(Problem.ProblemConfig problemConfig) {
        return new Number((double) this.getLeft().getValue() / (double) this.getRight().getValue(), this.getParent());
    }

}
