package com.cubearrow.model.tree.nodes.operations;

import com.cubearrow.model.problem.Problem;
import com.cubearrow.model.tree.nodes.Operation;
import com.cubearrow.model.tree.Node;
import com.cubearrow.model.tree.nodes.Number;

public class Exponentiation extends Operation {

    public final static char OPERATION_STRING = '^';

    public Exponentiation(String string, Node parent) {
        super(string, OPERATION_STRING, parent);
    }

    public Exponentiation(Node left, Node right, Node parent) {
        super(left, right, parent);
    }

    public Exponentiation() {
    }


    public Number getResultFromNumbers(Problem.ProblemConfig problemConfig) {
        return new Number(Math.pow((double) this.getLeft().getValue(), (double) this.getRight().getValue()), this.getParent());
    }
}
