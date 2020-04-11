package com.cubearrow.model.tree;

public class Variable extends Node {
    char variableName;


    public Variable(char variableName, Node parent) {
        super(null, null, parent);
        this.variableName = variableName;
    }

    public Variable(char variableName) {
        super(null, null, null);
        this.variableName = variableName;
    }

    public char getVariableName() {
        return variableName;
    }

    public void setVariableName(char variableName) {
        this.variableName = variableName;
    }

    @Override
    public String toString() {
        return String.valueOf(variableName);
    }

    public static Variable fromString(String numberString, Node parent) {
        if (numberString.length() > 1) return null;

        if (numberString.matches("[a-z]")) {
            return new Variable(numberString.toCharArray()[0], parent);
        }
        return null;
    }

    public static Variable fromString(String numberString){
        return fromString(numberString, null);
    }
}
