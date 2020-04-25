package com.cubearrow.model.equation;

import com.cubearrow.model.operations.Addition;
import com.cubearrow.model.operations.Division;
import com.cubearrow.model.operations.Multiplication;
import com.cubearrow.model.operations.Subtraction;
import com.cubearrow.model.tree.Number;
import com.cubearrow.model.tree.Variable;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class EquationTest {
    @Test
    public void testToString() {
        Equation equation = new Equation(
                new Addition(new Variable('x'), new Number(5f)),
                new Variable('x')
        );
        assertEquals("(x+5)=x", equation.toString());
    }

    @Test
    public void additionApplicationToNodes() {
        Equation equation = new Equation(
                new Addition(new Variable('x'), new Number(5f)),
                new Variable('x')
        );
        Addition addition = new Addition(null, new Number(2.4f));
        equation.applyOperationToNodes(addition, new ArrayList<>());
        Equation expectedEquation = new Equation(
                new Addition(
                        new Addition(new Variable('x'), new Number(5f)),
                        new Number(2.4f)
                ),
                new Addition(new Variable('x'), new Number(2.4f))
        );

        assert expectedEquation.equals(equation);
    }

    @Test
    public void subtractionApplicationToNodes() {
        Equation equation = new Equation(
                new Addition(new Variable('x'), new Number(5f)),
                new Variable('x')
        );
        Subtraction subtraction = new Subtraction(null, new Number(2.4f));
        equation.applyOperationToNodes(subtraction, new ArrayList<>());
        Equation expectedEquation = new Equation(
                new Subtraction(
                        new Addition(new Variable('x'), new Number(5f)),
                        new Number(2.4f)
                ),
                new Subtraction(new Variable('x'), new Number(2.4f))
        );

        assert expectedEquation.equals(equation);
    }

    @Test
    public void multiplicationApplicationToNodes() {
        Equation equation = new Equation(
                new Addition(new Variable('x'), new Number(5f)),
                new Variable('x')
        );
        Multiplication multiplication = new Multiplication(null, new Number(2.4f));
        equation.applyOperationToNodes(multiplication, new ArrayList<>());

        Equation expectedEquation = new Equation(
                new Addition(
                        new Multiplication(new Variable('x'), new Number(2.4f)),
                        new Multiplication(new Number(5f), new Number(2.4f))
                ),
                new Multiplication(new Variable('x'), new Number(2.4f))
        );

        assert expectedEquation.equals(equation);
    }

    @Test
    public void divisionApplicationToNode() {
        Equation equation = new Equation(
                new Addition(new Variable('x'), new Number(5f)),
                new Variable('x')
        );
        Division division = new Division(null, new Number(2.4f));
        equation.applyOperationToNodes(division, new ArrayList<>());

        Equation expectedEquation = new Equation(
                new Addition(
                        new Division(new Variable('x'), new Number(2.4f)),
                        new Division(new Number(5f), new Number(2.4f))
                ),
                new Division(new Variable('x'), new Number(2.4f))
        );

        assert expectedEquation.equals(equation);
    }

    @Test
    public void getSimpleVariables() {
        Equation equation = new Equation();
        Variable var1 = new Variable('x', equation);
        Variable var2 = new Variable('y', equation);
        equation.setLeft(var1);
        equation.setRight(var2);

        List<Variable> expected = Arrays.asList(var1, var2);
        List<Variable> actual = equation.getVariables();
        assert expected.equals(actual);
        for (int i = 0; i < actual.size(); i++) {
            assert actual.get(i).getParent().equals(expected.get(i).getParent());
        }
    }


    @Test
    public void getNestedVariables() {
        Equation equation = new Equation();
        Addition addition = new Addition(null, new Number(3f));
        Variable var1 = new Variable('x', addition);
        Variable var2 = new Variable('y', equation);
        addition.setLeft(var1);
        equation.setLeft(addition);
        equation.setRight(var2);

        List<Variable> expected = Arrays.asList(var1, var2);
        List<Variable> actual = equation.getVariables();
        assert expected.equals(actual);
        for (int i = 0; i < actual.size(); i++) {
            assert actual.get(i).getParent().equals(expected.get(i).getParent());
        }
    }
}
