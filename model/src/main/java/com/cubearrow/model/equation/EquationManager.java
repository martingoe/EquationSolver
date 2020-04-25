package com.cubearrow.model.equation;

import com.cubearrow.model.rewriting.EquationRewriter;

public class EquationManager {
    EquationRewriter equationRewriter = new EquationRewriter();

    Equation solveEquation(Equation equation){
        return equation.simplify(equationRewriter);
    }
    Equation solveEquation(Equation equation, char variableToIsolate){
        return equation.simplify(equationRewriter);
    }
}
