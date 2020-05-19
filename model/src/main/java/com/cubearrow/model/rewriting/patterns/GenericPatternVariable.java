package com.cubearrow.model.rewriting.patterns;


import com.cubearrow.model.tree.Node;

public class GenericPatternVariable extends Node implements GenericPattern{
    private final int patternIndex;

    public GenericPatternVariable(int patternIndex, Node parent) {
        super(null, null, parent);
        this.patternIndex = patternIndex;
    }

    public int getPatternIndex() {
        return patternIndex;
    }

    @Override
    public String toString() {
        return "$var" + patternIndex;
    }
}
