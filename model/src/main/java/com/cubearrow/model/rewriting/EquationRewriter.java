package com.cubearrow.model.rewriting;

import com.cubearrow.model.tree.nodes.Equation;
import com.cubearrow.model.tree.nodes.Operation;
import com.cubearrow.model.tree.Node;
import com.cubearrow.model.problem.Problem;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class EquationRewriter {
    List<Rule> operationRuleSets = new ArrayList<>();
    List<Rule> equationRuleSets = new ArrayList<>();

    public EquationRewriter() {
        initializeOperationRuleSet();
        initializeEquationRuleSet();
    }

    private void initializeOperationRuleSet() {
        try (BufferedReader bufferedReader = Files.newBufferedReader(Paths.get("model/src/main/resources/rulesets/OperationRuleSets.txt"))) {
            bufferedReader.lines().forEach(l -> addLineFromString(l, operationRuleSets));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void initializeEquationRuleSet() {
        try (BufferedReader bufferedReader = Files.newBufferedReader(Paths.get("model/src/main/resources/rulesets/EquationRuleSets.txt"))) {
            bufferedReader.lines().forEach((l) -> addLineFromString(l, equationRuleSets));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void addLineFromString(String line, List<Rule> ruleSetToAddTo) {
        if (line.startsWith("//") || line.isBlank()) {
            return;
        }
        String[] splitLine = line.split(";");
        ruleSetToAddTo.add(new Rule(splitLine[0], splitLine[1]));
    }

    public Node applyRulesToOperation(Operation operation, Problem problem) {
        for (Rule rule : operationRuleSets) {
            Node result = rule.applyPattern(operation, problem);
            if (result != null) {
                return result;
            }
        }
        return operation;
    }

    public Equation applyRulesToEquation(Equation equation, Problem problem) {
        for (Rule rule : equationRuleSets) {
            Node result = rule.applyPattern(equation, problem);
            if (result != null) {
                return (Equation) result;
            }
        }
        return equation;
    }
}
