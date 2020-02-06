package com.cubearrow.model.tree;

public class Number extends Node {

    private Float number;

    public Number(Float n) {
        super(null, null);
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

}
