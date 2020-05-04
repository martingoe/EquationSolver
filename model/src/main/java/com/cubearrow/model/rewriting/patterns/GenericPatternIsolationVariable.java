package com.cubearrow.model.rewriting.patterns;

import com.cubearrow.model.tree.Node;

public class GenericPatternIsolationVariable extends Node implements GenericPattern{
    private final int patternIndex;

    public GenericPatternIsolationVariable(int patternIndex, Node parent) {
        super(null, null, parent);
        this.patternIndex = patternIndex;
    }

    @Override
    public int getPatternIndex() {
        return patternIndex;
    }
}
