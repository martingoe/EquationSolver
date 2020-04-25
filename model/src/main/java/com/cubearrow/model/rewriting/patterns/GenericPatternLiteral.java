package com.cubearrow.model.rewriting.patterns;

import com.cubearrow.model.tree.Node;

public class GenericPatternLiteral extends Node {
    private final int patternIndex;

    public GenericPatternLiteral(int patternIndex, Node parent) {
        super(null, null, parent);
        this.patternIndex = patternIndex;
    }

    public int getPatternIndex() {
        return patternIndex;
    }
}
