package com.cubearrow.model.regex;

import com.cubearrow.model.operations.*;

import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
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
     * Sorts the provided classes by the priority field and return it in an Array of Classes
     *
     * @param classes The classes to be sorted
     * @return Returns the sorted array
     */
    public static Class<Operation>[] sortClassesByPriority(Class<Operation>[] classes) {
        return Arrays.stream(classes).sorted(Comparator.comparingInt((Class<Operation> o) -> {
            try {
                return (o.getDeclaredField("PRIORITY").getInt(o)) * -1;
            } catch (IllegalAccessException | NoSuchFieldException e) {
                e.printStackTrace();
                return -1;
            }
        })).toArray((IntFunction<Class<Operation>[]>) Class[]::new);
    }

    /**
     * Returns the {@link LinkedHashMap} that represents the operations and their operation string
     *
     * @return Return the {@link LinkedHashMap}
     */
    public HashMap<String, Class<? extends Operation>> getOperationHashMap() {
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
