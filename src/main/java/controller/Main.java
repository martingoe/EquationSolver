package controller;

import model.equation.Equation;
import model.equation.EquationInitializer;
import model.operations.Addition;
import model.operations.Operation;
import model.tree.Number;
import model.tree.Variable;

public class Main {
    public static void main(String[] args) {

        Equation node = new Equation(new Addition(new Number(4f), new Number(3f)), new Variable('x'));
        System.out.println(node.getVariables());

        Equation test = new EquationInitializer().parseEquation("((4-2)+x)*x=x");
        test.setLeft(test.simplifySimpleOperations((Operation) test.getLeft()));
        test.setLeft(test.simplifyOperation((Operation) test.getLeft()));

        System.out.println("DEBUG FUCKERS");
    }
}
