package com.cubearrow.model.rewriting.patterns;


import com.cubearrow.model.tree.Node;

public class GenericPatternNumber extends Node implements GenericPattern{
    private final int patternIndex;

    public GenericPatternNumber(int patternIndex, Node parent) {
        super(null, null, parent);
        this.patternIndex = patternIndex;
    }

    public int getPatternIndex() {
        return patternIndex;
    }
}
