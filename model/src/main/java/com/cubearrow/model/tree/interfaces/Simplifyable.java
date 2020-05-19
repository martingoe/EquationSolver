package com.cubearrow.model.tree.interfaces;

import com.cubearrow.model.rewriting.EquationRewriter;
import com.cubearrow.model.tree.Node;
import com.cubearrow.model.problem.Problem;

public interface Simplifyable {
    Node simplify(EquationRewriter equationRewriter, Problem problem);
}
