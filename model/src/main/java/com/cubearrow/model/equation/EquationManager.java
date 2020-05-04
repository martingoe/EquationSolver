package com.cubearrow.model.equation;

import com.cubearrow.model.rewriting.EquationRewriter;

public class EquationManager {
    private final EquationRewriter equationRewriter;

    public EquationManager() {
        this.equationRewriter = new EquationRewriter();
    }

    Equation solveEquation(final Equation equation) {
        return equation.simplify(equationRewriter);
    }
}
