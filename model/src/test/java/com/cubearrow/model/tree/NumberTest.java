package com.cubearrow.model.tree;

import com.cubearrow.model.tree.nodes.Number;
import org.junit.Test;

public class NumberTest {

    @Test
    public void fromString(){
        assert Number.fromString("4").getValue().equals(4.0);
        assert Number.fromString(".2").getValue().equals(.2);
        assert Number.fromString("4.6").getValue().equals(4.6);
    }
}
