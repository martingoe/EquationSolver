package com.cubearrow.model.rewriting.patterns;


import com.cubearrow.model.tree.Node;

public class GenericPatternOperation extends Node {
    public int getPatternIndex() {
        return patternOperationIndex;
    }

    private final int patternOperationIndex;

    public GenericPatternOperation(int patternOperationIndex, Node parent) {
        super(null, null, parent);
        this.patternOperationIndex = patternOperationIndex;
    }
}
