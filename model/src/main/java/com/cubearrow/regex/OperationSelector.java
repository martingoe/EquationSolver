package main.java.com.cubearrow.regex;

import main.java.com.cubearrow.operations.Operation;
import org.reflections.Reflections;

import java.util.HashMap;
import java.util.Set;

public class OperationSelector {


    public static Class getOperationFromOperationString(String operation) {
        HashMap<String, Class> operationHashMap = new HashMap<>();
        Reflections reflection = new Reflections("main.java.com.cubearrow.operations");
        Set<Class<? extends Operation>> operationClasses = reflection.getSubTypesOf(Operation.class);
        operationClasses.forEach(operationClass -> {
            try {
                operationHashMap.put((String) operationClass.getDeclaredField("OPERATION_STRING").get(null), operationClass);
            } catch (NoSuchFieldException | IllegalAccessException e) {
                e.printStackTrace();
            }
        });


        return operationHashMap.get(operation);
    }
}
