package com.cubearrow.model.rewriting;

import com.cubearrow.model.equation.Equation;
import com.cubearrow.model.operations.Operation;
import com.cubearrow.model.tree.Node;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

public class EquationRewriter {
    Set<Rule> operationRuleSets = new HashSet<>();
    Set<Rule> equationRuleSets = new HashSet<>();

    public EquationRewriter() {
        initializeOperationRuleSet();
        initializeEquationRuleSet();
    }

    private void initializeOperationRuleSet() {
        try (FileReader fileReader = new FileReader("model/src/main/resources/rulesets/OperationRuleSets.txt")) {
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            bufferedReader.lines().forEach(l -> addLineFromString(l, operationRuleSets));
            bufferedReader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void initializeEquationRuleSet() {
        try (FileReader fileReader = new FileReader("model/src/main/resources/rulesets/EquationRuleSets.txt")) {
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            bufferedReader.lines().forEach((l) -> addLineFromString(l, equationRuleSets));
            bufferedReader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void addLineFromString(String line, Set<Rule> ruleSetToAddTo) {
        if (line.startsWith("//") || line.isBlank()) {
            return;
        }
        String[] splitLine = line.split(";");
        ruleSetToAddTo.add(new Rule(splitLine[0], splitLine[1]));
    }

    public Node applyRulesToOperation(Operation operation) {
        for (Rule rule : operationRuleSets) {
            Node result = rule.applyPattern(operation);
            if (result != null) {
                return result;
            }
        }
        return operation;
    }

    public Equation applyRulesToEquation(Equation equation) {
        for (Rule rule : equationRuleSets) {
            Node result = rule.applyPattern(equation);
            if (result != null) {
                return (Equation) result;
            }
        }
        return equation;
    }
}
