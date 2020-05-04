package com.cubearrow.view.utils;

import com.cubearrow.model.tree.Variable;

import java.util.ArrayList;
import java.util.List;

public final class VariableUtil {
    public static List<Character> getVariableCharSet(List<Variable> variables) {
        ArrayList<Character> resultList = new ArrayList<>();


        variables.forEach(variable -> {
            char variableName = variable.getValue();
            if (!resultList.contains(variableName)) {
                resultList.add(variableName);
            }
        });

        return resultList;
    }
}
