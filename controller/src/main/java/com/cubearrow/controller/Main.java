package com.cubearrow.controller;

import com.cubearrow.model.equation.Equation;
import com.cubearrow.model.equation.EquationInitializer;
import com.cubearrow.model.operations.Operation;
import com.cubearrow.model.rewriting.EquationRewriter;
import com.cubearrow.model.tree.Variable;
import com.cubearrow.view.Interaction;

public class Main {
    public static void main(String[] args) {
        Equation test = new EquationInitializer("((3/4)*x)+x=2+x").parseEquation();

        Interaction interaction = new Interaction(test);
        test.setVariableToIsolate(interaction.askUserForVariableToIsolate());
        EquationRewriter equationRewriter = new EquationRewriter();
        for (int i = 0; i < 20; i++) {
            test = test.simplify(equationRewriter);
        }
        interaction.displayResult(test);
        //OperationSelector.sortClassesByPriority(operationSelector.getOperationHashMap().values().toArray(new Class[0]));

        System.out.println("DEBUG");
    }
}
