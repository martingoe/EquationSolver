package com.cubearrow.model.operations;

import com.cubearrow.model.tree.Number;
import com.cubearrow.model.tree.Variable;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class OperationTest {
    @Test
    public void simpleFromStringOperation() {
        Addition expectedAddition = new Addition(new Variable('x'), new Number(4f));
        Operation operation = Operation.fromString("x+4", null);
        assert expectedAddition.equals(operation);
    }

    @Test
    public void nestedFromStringOperation() {
        Multiplication expectedMultiplication = new Multiplication(
                new Addition(new Number(1f), new Number(6f)),
                new Variable('x')
        );
        Operation operation = Operation.fromString("(1+6)*x", null);
        assert expectedMultiplication.equals(operation);
    }

    @Test
    public void toStringTest() {
        Addition addition = new Addition(new Number(6.4f), new Variable('x'));
        assertEquals("The Operation.toString() method does not equal the expected String", addition.toString(), "(6.4+x)");
    }

    @Test
    public void getResult() {
        assertEquals(new Multiplication(new Number(4f), new Number(2f)).getResult().getValue(), 8f, 0);
        assertNull(new Multiplication(new Variable('x'), new Number(2f)).getResult());
        assertEquals(new Addition(new Number(4f), new Number(2f)).getResult().getValue(), 6f, 0);
    }
}
