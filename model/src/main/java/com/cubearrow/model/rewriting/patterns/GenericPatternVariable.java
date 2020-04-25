package com.cubearrow.model.rewriting.patterns;


import com.cubearrow.model.tree.Node;

public class GenericPatternVariable extends Node {
    public int getPatternIndex() {
        return patternVariableIndex;
    }

    private final int patternVariableIndex;

    public GenericPatternVariable(int patternVariableIndex, Node parent) {
        super(null, null, parent);
        this.patternVariableIndex = patternVariableIndex;
    }
}
