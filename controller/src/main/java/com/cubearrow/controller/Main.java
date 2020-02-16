package com.cubearrow.controller;

import com.cubearrow.model.equation.Equation;
import com.cubearrow.model.equation.EquationInitializer;
import com.cubearrow.model.tree.utils.NodeUtilities;
import com.cubearrow.view.Interaction;

public class Main {
    public static void main(String[] args) {


        Equation test = EquationInitializer.parseEquation("((4-2)+x)/x=y");
        Equation previous = null;
        while (!NodeUtilities.ifNodeEquals(test, previous)) {
            try {
                previous = (Equation) test.clone();
            } catch (CloneNotSupportedException e) {
                e.printStackTrace();
            }
            test.simplify();
        }
        new Interaction(test).askUserForVariableToIsolate();

        System.out.println(test.toString());
    }
}
