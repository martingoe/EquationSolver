package com.cubearrow.model.problem;

import com.cubearrow.model.rewriting.EquationRewriter;
import com.cubearrow.model.tree.Node;

public class ProblemManager {
    EquationRewriter equationRewriter;

    public ProblemManager() {
        this.equationRewriter = new EquationRewriter();
    }

    public Node solveProblem(final Problem problem) {
        return problem.simplify(this.equationRewriter);
    }

}
