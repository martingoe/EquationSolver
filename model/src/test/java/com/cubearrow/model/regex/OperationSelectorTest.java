package com.cubearrow.model.regex;

import com.cubearrow.model.operations.*;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;

@SuppressWarnings("unchecked")
public class OperationSelectorTest {
    OperationSelector operationSelector;

    @Before
    public void setUp() {
        operationSelector = new OperationSelector();
    }

    @Test
    public void sortClassesByPriority(){
        Class[] expectedClasses = {Multiplication.class, Division.class, Addition.class, Subtraction.class};
        Class[] classes = {Addition.class, Subtraction.class, Multiplication.class, Division.class};
        classes = OperationSelector.sortClassesByPriority(classes);
        Assert.assertArrayEquals(expectedClasses, classes);
    }

    @Test
    public void getOperationHashMap() {
        HashMap<String, Class> expectedResult = new HashMap<>();
        expectedResult.put("+", Addition.class);
        expectedResult.put("-", Subtraction.class);
        expectedResult.put("*", Multiplication.class);
        expectedResult.put("/", Division.class);

        Assert.assertEquals(expectedResult, operationSelector.getOperationHashMap());
    }

    @Test
    public void getOperationFromOperationString() {
        Assert.assertEquals(Addition.class,
                operationSelector.getOperationFromOperationString("+"));
        Assert.assertEquals(Subtraction.class, operationSelector.getOperationFromOperationString("-"));
    }
}
