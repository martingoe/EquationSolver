package com.cubearrow.controller;

import com.cubearrow.model.equation.Equation;
import com.cubearrow.model.equation.EquationInitializer;
import com.cubearrow.model.regex.OperationSelector;
import com.cubearrow.view.Interaction;

public class Main {
    public static void main(String[] args) {
        Equation test = new EquationInitializer("((3/4)*x)+x=y").parseEquation();
        Interaction interaction = new Interaction(test);
        interaction.askUserForVariableToIsolate();
        Equation previous = null;
        while (!test.equals(previous)) {
            previous = (Equation) test.clone();
            test.simplify();
        }
        interaction.displayResult(test);
        OperationSelector.sortClassesByPriority(new OperationSelector().getOperationHashMap().values().toArray(new Class[0]));

        System.out.println("DEBUG");
    }
}
