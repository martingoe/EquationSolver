package com.cubearrow.controller;

import com.cubearrow.model.equation.Equation;
import com.cubearrow.model.equation.EquationInitializer;
import com.cubearrow.model.tree.utils.NodeUtilities;
import com.cubearrow.view.Interaction;

import java.lang.reflect.InvocationTargetException;

public class Main {
    public static void main(String[] args) {


        Equation test = null;

        try {
            test = new EquationInitializer("((4-2)+x)/x=y").parseEquation();
        } catch (NoSuchMethodException | InstantiationException | IllegalAccessException | InvocationTargetException e) {
            new Interaction().printError(e.toString());
            e.printStackTrace();
        }

        Interaction interaction = new Interaction(test);
        char c = interaction.askUserForVariableToIsolate();
        Equation previous = null;
        while (!NodeUtilities.ifNodeEquals(test, previous)) {
            try {
                previous = (Equation) test.clone();
            } catch (CloneNotSupportedException e) {
                e.printStackTrace();
            }
            test.simplify();
        }
        interaction.displayResult(test);


    }
}
