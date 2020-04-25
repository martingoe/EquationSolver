package com.cubearrow.model.rewriting;

import com.cubearrow.model.operations.Operation;
import com.cubearrow.model.tree.Node;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class EquationRewriter {
    Set<Rule> ruleSet = new HashSet<>();

    public EquationRewriter() {
        initializeRuleSet();
    }

    private void initializeRuleSet() {
        try (FileReader fileReader = new FileReader("model/src/main/resources/Rulesets.txt")) {
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            bufferedReader.lines().forEach(this::addLineFromString);
            bufferedReader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void addLineFromString(String line) {
        if (line.startsWith("//") || line.isBlank()) {
            return;
        }
        String[] splitLine = line.split(";");
        ruleSet.add(new Rule(splitLine[0], splitLine[1]));
    }

    public Node applyRulesToOperation(Operation operation) {
        for (Rule rule : ruleSet) {
            Node result = rule.applyPattern(operation);
            if (result != null) {
                return result;
            }
        }
        return operation;
    }
}
