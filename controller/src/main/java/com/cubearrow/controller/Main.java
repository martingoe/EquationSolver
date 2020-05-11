package com.cubearrow.controller;

import com.cubearrow.model.equation.Equation;
import com.cubearrow.model.equation.EquationInitializer;
import com.cubearrow.model.rewriting.EquationRewriter;
import com.cubearrow.view.Interaction;

public class Main {
    public static void main(String[] args) {
//        Equation test = new EquationInitializer("((3/4)*x)+x=2+x").parseEquation();

        Equation test = new EquationInitializer("(4*4)*x=(2x)+5").parseEquation();
        Interaction interaction = new Interaction(test);
        EquationRewriter equationRewriter = new EquationRewriter();
        for (int j = 0; j < 20; j++) {
            test = test.simplify(equationRewriter);
        }
        interaction.displayResult(test);
    }
}
