package com.cubearrow.model.tree.nodes;

import com.cubearrow.model.tree.Node;

public class Number extends Node<Double> {


    public Number(Double n, Node parent) {
        super(parent, n);
    }

    public Number(double value) {
        super(value);
    }

    public static Number fromString(String numberString, Node parent) {
        try {
            return switch (numberString) {
                case "pi", "PI" -> new Number(Math.PI, parent);
                case "e", "E" -> new Number(Math.E, parent);
                case "tau", "Tau" -> new Number(Math.PI * 2, parent);
                default -> new Number(Double.valueOf(numberString), parent);
            };
        } catch (NumberFormatException e) {
            return null;
        }
    }

    public static Number fromString(String numberString) {
        return fromString(numberString, null);
    }


    public Double getValue() {
        return super.getValue();
    }

    public void setValue(Double value) {
        super.setValue(value);
    }

    public void setToNegative() {
        super.setValue(-super.getValue());
    }

    @Override
    public String toString() {
        if (super.getValue() % 1 == 0) {
            return String.valueOf(super.getValue().intValue());
        }

        return String.valueOf(super.getValue());
    }

}
