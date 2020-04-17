package com.cubearrow.model.tree;

import org.junit.Test;

public class NumberTest {

    @Test
    public void fromString(){
        assert Number.fromString("4").getNumber().equals(4f);
        assert Number.fromString(".2").getNumber().equals(.2f);
        assert Number.fromString("4.6").getNumber().equals(4.6f);
    }
}
