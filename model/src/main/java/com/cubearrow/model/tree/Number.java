package com.cubearrow.model.tree;

public class Number extends Node {

    private Float number;

    public Number(Float n, Node parent) {
        super(null, null, parent);
        this.number = n;
    }

    public Float getNumber() {
        return number;
    }

    public void setNumber(Float number) {
        this.number = number;
    }

    public void setToNegative() {
        this.setNumber(-number);
    }

    @Override
    public String toString(){
        return String.valueOf(number);
    }


    public static Number fromString(String numberString, Node parent) {
        try {
            return new Number(Float.valueOf(numberString), parent);
        } catch (NumberFormatException e) {
            return null;
        }
    }

}
