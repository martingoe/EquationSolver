package main.java.com.cubearrow.controller;

import main.java.com.cubearrow.equation.Equation;
import main.java.com.cubearrow.equation.EquationInitializer;
import main.java.com.cubearrow.tree.utils.NodeUtilities;
import main.java.com.cubearrow.view.Interaction;

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
