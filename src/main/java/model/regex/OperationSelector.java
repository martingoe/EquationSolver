package model.regex;

import model.operations.*;

import java.util.HashMap;

public class OperationSelector<T> {
    static Class getOperationFromOperationString(String operation) {
        HashMap<String, Class> operationHashMap = new HashMap<>();
        operationHashMap.put("+", Addition.class);
        operationHashMap.put("-", Subtraction.class);
        operationHashMap.put("*", Multiplication.class);
        operationHashMap.put("/", Division.class);

        return operationHashMap.get(operation);
    }
}
