package com.cubearrow.model.tree.nodes;

import com.cubearrow.model.tree.Node;

public class Variable extends Node<Character> {


    public Variable(char value, Node parent) {
        super(parent, value);
    }

    public Variable(char value) {
        super(value);
    }

    @Override
    public String toString() {
        return String.valueOf(this.getValue());
    }

    public static Variable fromString(String numberString, Node parent) {
        if (numberString.length() > 1) {
            return null;
        }

        if (numberString.matches("[a-z]")) {
            return new Variable(numberString.toCharArray()[0], parent);
        }
        return null;
    }

    public static Variable fromString(String numberString){
        return fromString(numberString, null);
    }
}
