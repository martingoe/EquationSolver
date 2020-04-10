package com.cubearrow.model.regex;

import com.cubearrow.model.operations.Operation;
import org.reflections.Reflections;

import java.util.Arrays;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.Set;
import java.util.function.IntFunction;

public class OperationSelector {
    private LinkedHashMap<String, Class<Operation>> operationHashMap;

    public LinkedHashMap<String, Class<Operation>> getOperationHashMap() {
        return operationHashMap;
    }

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

    public Class<Operation>[] sortClassesByPriority(Class<Operation>[] classes) {
        return Arrays.stream(classes).sorted(Comparator.comparingInt((Class<Operation> o) -> {
            try {
                return (o.getDeclaredField("PRIORITY").getInt(o)) * -1;
            } catch (IllegalAccessException | NoSuchFieldException e) {
                e.printStackTrace();
                return -1;
            }
        })).toArray((IntFunction<Class<Operation>[]>) Class[]::new);
    }

    public Class<Operation> getOperationFromOperationString(String operation) {
        return this.operationHashMap.get(operation);
    }
}
