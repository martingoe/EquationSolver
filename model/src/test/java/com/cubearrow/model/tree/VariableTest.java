package com.cubearrow.model.tree;

import com.cubearrow.model.tree.nodes.Variable;
import org.junit.Test;

public class VariableTest {
    @Test
    public void fromString(){
        assert Variable.fromString("x").getValue() == 'x';
        assert Variable.fromString("xy") == null;
        assert Variable.fromString("") == null;
    }
}
