import model.equation.EquationInitializer;
import model.operations.Addition;
import model.operations.Multiplication;
import model.operations.Operation;
import model.equation.Equation;
import model.tree.Node;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        List list = new ArrayList<>() {{
            add(5f);
            add(new Addition(new ArrayList() {{
                add(6f);
                add(2f);
            }}));
        }};

        List x = new ArrayList<Node>(){{
            add(new Multiplication(list));
        }};
        Operation node = (Operation) new Equation(x).getChildrenNodes().get(0);
        System.out.println(node.getResult());

        new EquationInitializer("Hello=World").getChildrenNodes();
    }
}
