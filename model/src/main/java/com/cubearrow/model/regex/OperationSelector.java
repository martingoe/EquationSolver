package com.cubearrow.model.regex;

import com.cubearrow.model.operations.Operation;
import org.reflections.Reflections;

import java.util.HashMap;
import java.util.Set;

public class OperationSelector {
    private HashMap<String, Class> operationHashMap;
    public OperationSelector() {
        HashMap<String, Class> operationHashMap = new HashMap<>();
        Reflections reflection = new Reflections("com.cubearrow.model.operations");
        Set<Class<? extends Operation>> operationClasses = reflection.getSubTypesOf(Operation.class);
        operationClasses.forEach(operationClass -> {
            try {
                operationHashMap.put((String) operationClass.getDeclaredField("OPERATION_STRING").get(null), operationClass);
            } catch (NoSuchFieldException | IllegalAccessException e) {
                e.printStackTrace();
            }
        });
        this.operationHashMap = operationHashMap;
    }

    public Class getOperationFromOperationString(String operation) {
        return this.operationHashMap.get(operation);
    }
}
