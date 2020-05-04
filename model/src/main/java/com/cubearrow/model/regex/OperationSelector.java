package com.cubearrow.model.regex;

import com.cubearrow.model.operations.*;

import java.util.*;
import java.util.function.IntFunction;

public class OperationSelector {
    private final static Class<? extends Operation>[] operationClasses = new Class[]{Addition.class, Division.class, Multiplication.class, Subtraction.class, Exponentiation.class};
    private final Map<String, Class<? extends Operation>> operationHashMap = new HashMap<>();

    /**
     * Initializes an OperationSelector by adding all of the subclasses of {@link Operation} to a HashMap
     */
    public OperationSelector() {
        for (Class<? extends Operation> operationClass : operationClasses) {
            try {
                operationHashMap.put((String) operationClass.getDeclaredField("OPERATION_STRING").get(null), operationClass);
            } catch (NoSuchFieldException | IllegalAccessException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Returns the {@link LinkedHashMap} that represents the operations and their operation string
     *
     * @return Return the {@link LinkedHashMap}
     */
    public Map<String, Class<? extends Operation>> getOperationHashMap() {
        return operationHashMap;
    }

    /**
     * Returns the Class that is represented by an operationString like "+"
     *
     * @param operation The operation String to search by
     * @return the representing Class
     */
    public Class<? extends Operation> getOperationFromOperationString(String operation) {
        return this.operationHashMap.get(operation);
    }
}
