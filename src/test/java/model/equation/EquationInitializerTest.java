package model.equation;


import model.operations.Addition;
import model.operations.Division;
import model.operations.Multiplication;
import model.operations.Operation;
import model.tree.Node;
import model.tree.Number;
import model.tree.Variable;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class EquationInitializerTest {

    @Test
    public void parseEquation() {
        assertEquationEquals(EquationInitializer.parseEquation("2=4"),
                new Equation(new Number(2f), new Number(4f)));

        Equation expected = new Equation(
                        new Addition(
                                new Variable('x'),
                                new Number(4f)),
                        new Multiplication(
                                new Number(2f),
                                new Number(7f)));
        assertEquationEquals(EquationInitializer.parseEquation("x+4=2*7"), expected);


        expected = new Equation(
                new Variable('x'),
                new Division(
                        new Number(5f),
                        new Addition(
                                new Addition(
                                        new Variable('x'),
                                        new Number(6f)
                                ),
                                new Variable('x')
                        )
                ));
        assertEquationEquals(EquationInitializer.parseEquation("x=5/((x+6)+x)"), expected);
    }

    public void assertEquationEquals(Node actual, Node expected) {
        Node actualRight = actual.getRight();
        Node actualLeft = actual.getLeft();

        Node expectedRight = expected.getRight();
        Node expectedLeft = expected.getLeft();

        assertEquals(actualRight.getClass(), expectedRight.getClass());
        assertEquals(actualLeft.getClass(), expectedLeft.getClass());

        assertNodeEquals(actualRight, expectedRight);
        assertNodeEquals(actualLeft, expectedLeft);
    }


    private void assertNodeEquals(Node actual, Node expected) {
        if (actual instanceof Operation)
            assertEquationEquals(actual, expected);
        else if (actual instanceof Variable)
            assertEquals(((Variable) actual).getVariableName(), ((Variable) expected).getVariableName());
        else if (actual instanceof Number)
            assertEquals(((Number) actual).getNumber(), ((Number) expected).getNumber());
    }

}
