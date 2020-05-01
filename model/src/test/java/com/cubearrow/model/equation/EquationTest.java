package com.cubearrow.model.equation;

import com.cubearrow.model.operations.Addition;
import com.cubearrow.model.tree.Number;
import com.cubearrow.model.tree.Variable;
import org.junit.Test;

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
