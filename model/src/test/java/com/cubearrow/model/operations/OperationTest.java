package com.cubearrow.model.operations;

import com.cubearrow.model.tree.Number;
import com.cubearrow.model.tree.Variable;
import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class OperationTest {
    @Test
    public void fromString() {
        Addition expectedAddition = new Addition(new Variable('x'), new Number(4f));
        Operation operation = Operation.fromString("x+4", null);
        assert expectedAddition.equals(operation);


        Multiplication expectedMultiplication = new Multiplication(
                new Addition(new Number(1f), new Number(6f)),
                new Variable('x')
        );
        operation = Operation.fromString("(1+6)*x", null);
        assert expectedMultiplication.equals(operation);
    }

    @Test
    public void toStringTest() {
        Addition addition = new Addition(new Number(6.4f), new Variable('x'));
        assertEquals(addition.toString(), "(6.4+x)");
    }
}
