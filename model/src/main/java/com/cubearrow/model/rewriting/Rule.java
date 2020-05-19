package com.cubearrow.model.rewriting;

import com.cubearrow.model.tree.nodes.Equation;
import com.cubearrow.model.tree.nodes.Operation;
import com.cubearrow.model.rewriting.patterns.*;
import com.cubearrow.model.tree.Node;
import com.cubearrow.model.tree.nodes.Number;
import com.cubearrow.model.tree.nodes.Variable;
import com.cubearrow.model.problem.Problem;

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

    Node applyPattern(Node operation, Problem problem) {
        Map<String, Node> patternVariableHashMap = new HashMap<>();
        if (matchesPatternVariables(operation, rootNode, patternVariableHashMap, problem)) {
            return useReplacement(operation, patternVariableHashMap);
        }
        return null;
    }

    private boolean matchesPatternVariables(Node node, Node pattern, Map<String, Node> patternVariableHashMap, Problem problem) {
        if (node instanceof Operation && pattern instanceof Operation ||
                node instanceof Equation && pattern instanceof Equation) {
            return node.getClass() == pattern.getClass() &&
                    matchesPatternVariables(node.getLeft(), pattern.getLeft(), patternVariableHashMap, problem) &&
                    matchesPatternVariables(node.getRight(), pattern.getRight(), patternVariableHashMap, problem);
        }

        if (pattern instanceof GenericPatternVariable genericPatternVariable && node instanceof Variable) {
            String key = "var" + genericPatternVariable.getPatternIndex();
            return addToPatternVariablesAndGetMatch(node, patternVariableHashMap, key);
        }
        if(pattern instanceof GenericPatternIsolationVariable genericPatternIsolationVariable && node instanceof Variable variableNode){
            if(problem.getProblemConfig().variableToIsolate == variableNode.getValue()){
                String key = "ivar" + genericPatternIsolationVariable.getPatternIndex();
                return addToPatternVariablesAndGetMatch(node, patternVariableHashMap, key);
            }
        }
        if (pattern instanceof GenericPatternNumber genericPatternNumber && node instanceof Number) {
            String key = "nu" + genericPatternNumber.getPatternIndex();
            return addToPatternVariablesAndGetMatch(node, patternVariableHashMap, key);
        }
        if (pattern instanceof GenericPatternOperation genericPatternOperation && node instanceof Operation) {
            String key = "op" + genericPatternOperation.getPatternIndex();
            return addToPatternVariablesAndGetMatch(node, patternVariableHashMap, key);
        }
        if (pattern instanceof GenericPatternLiteral patternLiteral) {
            String key = String.valueOf(patternLiteral.getPatternIndex());
            return addToPatternVariablesAndGetMatch(node, patternVariableHashMap, key);
        }

        return node.equals(pattern);
    }

    private boolean addToPatternVariablesAndGetMatch(Node node, Map<String, Node> patternVariableHashMap, String key) {
        if (patternVariableHashMap.containsKey(key)) {
            return node.equals(patternVariableHashMap.get(key));
        }
        patternVariableHashMap.put(key, node);
        return true;
    }

    private Node useReplacement(Node operation, Map<String, Node> patternVariableHashMap) {
        String result = this.replacement;
        for (Map.Entry<String, Node> entry : patternVariableHashMap.entrySet()) {
            String string = entry.getKey();
            Node node = entry.getValue();
            result = result.replace("$" + string, node.toString());
        }
        return Node.fromString(result, operation.getParent());
    }
}
