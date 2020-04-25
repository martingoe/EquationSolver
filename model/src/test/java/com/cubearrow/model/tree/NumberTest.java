package com.cubearrow.model.tree;

import org.junit.Test;

public class NumberTest {

    @Test
    public void fromString(){
        assert Number.fromString("4").getValue().equals(4f);
        assert Number.fromString(".2").getValue().equals(.2f);
        assert Number.fromString("4.6").getValue().equals(4.6f);
    }
}
