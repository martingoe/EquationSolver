package com.cubearrow.model.utils;

import com.cubearrow.model.tree.nodes.operations.*;
import com.cubearrow.model.utils.regex.OperationSelector;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;

public class OperationSelectorTest {
    OperationSelector operationSelector;

    @Before
    public void setUp() {
        operationSelector = new OperationSelector();
    }

    @Test
    public void getOperationHashMap() {
        HashMap<String, Class> expectedResult = new HashMap<>();
        expectedResult.put("+", Addition.class);
        expectedResult.put("-", Subtraction.class);
        expectedResult.put("*", Multiplication.class);
        expectedResult.put("/", Division.class);
        expectedResult.put("^", Exponentiation.class);
        expectedResult.put("sin", Sin.class);
        expectedResult.put("cos", Cos.class);
        expectedResult.put("tan", Tan.class);

        Assert.assertEquals(expectedResult, operationSelector.getOperationHashMap());
    }

    @Test
    public void getOperationFromOperationString() {
        Assert.assertEquals(Addition.class,
                operationSelector.getOperationFromOperationString("+"));
        Assert.assertEquals(Subtraction.class, operationSelector.getOperationFromOperationString("-"));
    }
}
