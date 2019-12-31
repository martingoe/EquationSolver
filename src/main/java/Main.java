import model.operations.Addition;
import model.operations.Multiplication;
import model.operations.Operation;
import model.equation.Equation;
import model.tree.Node;
import model.tree.Number;
import model.tree.Variable;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        Equation node = (Equation) new Equation(new Addition(new Number(4f), new Number(3f)), new Variable('x'));
        System.out.println(node.getVariables());

    }
}
