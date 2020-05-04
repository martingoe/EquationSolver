package com.cubearrow.model.rewriting;

import com.cubearrow.model.equation.Equation;
import com.cubearrow.model.operations.Operation;
import com.cubearrow.model.rewriting.patterns.*;
import com.cubearrow.model.tree.Node;
import com.cubearrow.model.tree.Number;
import com.cubearrow.model.tree.Variable;

import java.util.HashMap;
import java.util.Map;

public class Rule {
    private final Node rootNode;
    String pattern;
    String replacement;

    public Rule(String pattern, String replacement) {
        this.pattern = pattern;
        this.replacement = replacement;

        this.rootNode = Node.fromString(pattern, null);
    }

    Node applyPattern(Node operation, Equation equation) {
        HashMap<String, Node> patternVariableHashMap = new HashMap<>();
        if (matchesPatternVariables(operation, rootNode, patternVariableHashMap, equation)) {
            return useReplacement(operation, patternVariableHashMap);
        }
        return null;
    }

    private boolean matchesPatternVariables(Node node, Node pattern, HashMap<String, Node> patternVariableHashMap, Equation equation) {
        if (node instanceof Operation && pattern instanceof Operation ||
                node instanceof Equation && pattern instanceof Equation) {
            return node.getClass() == pattern.getClass() &&
                    matchesPatternVariables(node.getLeft(), pattern.getLeft(), patternVariableHashMap, equation) &&
                    matchesPatternVariables(node.getRight(), pattern.getRight(), patternVariableHashMap, equation);
        }

        if (pattern instanceof GenericPatternVariable genericPatternVariable && node instanceof Variable) {
            String key = "var" + genericPatternVariable.getPatternIndex();
            return AddToPatternVariablesAndGetMatch(node, patternVariableHashMap, key);
        }
        if(pattern instanceof GenericPatternIsolationVariable genericPatternIsolationVariable && node instanceof Variable variableNode){
            if(equation.getVariableToIsolate() == variableNode.getValue()){
                String key = "ivar" + genericPatternIsolationVariable.getPatternIndex();
                return AddToPatternVariablesAndGetMatch(node, patternVariableHashMap, key);
            }
        }
        if (pattern instanceof GenericPatternNumber genericPatternNumber && node instanceof Number) {
            String key = "nu" + genericPatternNumber.getPatternIndex();
            return AddToPatternVariablesAndGetMatch(node, patternVariableHashMap, key);
        }
        if (pattern instanceof GenericPatternOperation genericPatternOperation && node instanceof Operation) {
            String key = "op" + genericPatternOperation.getPatternIndex();
            return AddToPatternVariablesAndGetMatch(node, patternVariableHashMap, key);
        }
        if (pattern instanceof GenericPatternLiteral patternLiteral) {
            String key = String.valueOf(patternLiteral.getPatternIndex());
            return AddToPatternVariablesAndGetMatch(node, patternVariableHashMap, key);
        }

        return node.equals(pattern);
    }

    private boolean AddToPatternVariablesAndGetMatch(Node node, HashMap<String, Node> patternVariableHashMap, String key) {
        if (patternVariableHashMap.containsKey(key)) {
            return node.equals(patternVariableHashMap.get(key));
        }
        patternVariableHashMap.put(key, node);
        return true;
    }

    private Node useReplacement(Node operation, HashMap<String, Node> patternVariableHashMap) {
        String result = this.replacement;
        for (Map.Entry<String, Node> entry : patternVariableHashMap.entrySet()) {
            String string = entry.getKey();
            Node node = entry.getValue();
            result = result.replace("$" + string, node.toString());
        }
        return Node.fromString(result, operation.getParent());
    }
}
