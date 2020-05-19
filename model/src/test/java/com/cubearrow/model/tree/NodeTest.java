package com.cubearrow.model.tree;

import com.cubearrow.model.tree.nodes.Equation;
import com.cubearrow.model.tree.nodes.Number;
import com.cubearrow.model.tree.nodes.Variable;
import com.cubearrow.model.tree.nodes.operations.Addition;
import org.junit.Test;

public class NodeTest {

    @Test
    public void equals() {
        Equation equation = new Equation();
        Variable n1 = new Variable('x', equation);
        Variable n2 = new Variable('x', equation);
        assert n1.equals(n2);


        Addition a1 = new Addition(new Number(1f), new Variable('x'));
        Addition a2 = new Addition(new Number(1f), new Variable('x'));
        assert a1.equals(a2);
    }

}
