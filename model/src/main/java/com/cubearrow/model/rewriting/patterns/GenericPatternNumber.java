package com.cubearrow.model.rewriting.patterns;


import com.cubearrow.model.tree.Node;

public class GenericPatternNumber extends Node {
    private final int patternNumberIndex;

    public GenericPatternNumber(int patternNumberIndex, Node parent) {
        super(null, null, parent);
        this.patternNumberIndex = patternNumberIndex;
    }

    public int getPatternIndex() {
        return patternNumberIndex;
    }
}
