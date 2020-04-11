package com.cubearrow.model.regex;

import com.cubearrow.model.operations.Operation;
import org.reflections.Reflections;

import java.util.Arrays;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.Set;
import java.util.function.IntFunction;

public class OperationSelector {
    private final LinkedHashMap<String, Class<Operation>> operationHashMap;

    /**
     * Returns the {@link LinkedHashMap} that represents the operations and their operation string
     * @return Return the {@link LinkedHashMap}
     */
    public LinkedHashMap<String, Class<Operation>> getOperationHashMap() {
        return operationHashMap;
    }

    /**
     * Initializes an OperationSelector by adding all of the subclasses of {@link Operation} to a HashMap
     */
    public OperationSelector() {
        LinkedHashMap<String, Class<Operation>> operationHashMap = new LinkedHashMap<>();
        Reflections reflection = new Reflections("com.cubearrow.model.operations");
        Set<Class<? extends Operation>> operationClasses = reflection.getSubTypesOf(Operation.class);
        operationClasses.forEach(operationClass -> {
            try {
                operationHashMap.put((String) operationClass.getDeclaredField("OPERATION_STRING").get(null), (Class<Operation>) operationClass);
            } catch (NoSuchFieldException | IllegalAccessException e) {
                e.printStackTrace();
            }
        });
        this.operationHashMap = operationHashMap;
    }

    /**
     * Sorts the provided classes by the priority field and return it in an Array of Classes
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
     * Returns the Class that is represented by an operationString like "+"
     * @param operation The operation String to search by
     * @return the representing Class
     */
    public Class<Operation> getOperationFromOperationString(String operation) {
        return this.operationHashMap.get(operation);
    }
}
