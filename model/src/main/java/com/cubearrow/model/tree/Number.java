package com.cubearrow.model.tree;

public class Number extends Node<Float> {


    public Number(Float n, Node parent) {
        super(parent, n);
    }

    public Number(float value) {
        super(value);
    }

    public Float getValue() {
        return value;
    }

    public void setValue(Float value) {
        this.value = value;
    }

    public void setToNegative() {
        this.setValue(-value);
    }

    @Override
    public String toString(){
        if(value % 1 == 0) {
            return String.valueOf(value.intValue());
        }
        return String.valueOf(value);
    }


    public static Number fromString(String numberString, Node parent) {
        try {
            return new Number(Float.valueOf(numberString), parent);
        } catch (NumberFormatException e) {
            return null;
        }
    }
    public static Number fromString(String numberString){
        return fromString(numberString, null);
    }

}
