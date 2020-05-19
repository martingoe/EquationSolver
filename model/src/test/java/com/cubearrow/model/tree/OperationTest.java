package com.cubearrow.model.tree;

import com.cubearrow.model.tree.nodes.Number;
import com.cubearrow.model.tree.nodes.Operation;
import com.cubearrow.model.tree.nodes.Variable;
import com.cubearrow.model.tree.nodes.operations.Addition;
import com.cubearrow.model.tree.nodes.operations.Multiplication;
import org.junit.Test;

import static org.junit.Assert.*;

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
        Addition addition = new Addition(new Number(6.4), new Variable('x'));
        assertEquals("The Operation.toString() method does not equal the expected String", addition.toString(), "(6.4+x)");
    }

    @Test
    public void getResult() {
        assertEquals((double) new Multiplication(new Number(4.0), new Number(2.0)).getResult().getValue(), 8.0, 0);

        assertEquals(6.0, (double) new Addition(new Number(4.0), new Number(2.0)).getResult().getValue(), 0);
    }
}
