package com.cubearrow.model.tree;

import org.junit.Test;

public class VariableTest {
    @Test
    public void fromString(){
        assert Variable.fromString("x").getVariableName() == 'x';
        assert Variable.fromString("xy") == null;
        assert Variable.fromString("") == null;
    }
}
