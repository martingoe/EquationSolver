package com.cubearrow.view.utils;

import com.cubearrow.model.tree.Node;
import com.cubearrow.model.tree.Variable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class VariableUtilities {
    public static List<Character> getVariableCharSet(List<Variable> variables) {
        ArrayList<Character> resultList = new ArrayList<>();


        variables.forEach(variable -> {
            char variableName = variable.getVariableName();
            if (!resultList.contains(variableName)) {
                resultList.add(variableName);
            }
        });

        return resultList;
    }
}
