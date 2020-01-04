package model.tree;

public class Variable extends Node {
    char variableName;


    public Variable(char variableName) {
        super(null, null);
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
}
