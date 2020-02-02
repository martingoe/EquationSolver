package controller;

import model.equation.Equation;
import model.equation.EquationInitializer;
import model.operations.Addition;
import model.operations.Operation;
import model.tree.Number;
import model.tree.Variable;

public class Main {
    public static void main(String[] args) {


        Equation test = EquationInitializer.parseEquation("((4-2)+x)/x=x");
        test.simplify();
        test.simplify();

        System.out.println("DEBUG FUCKERS");
    }
}
