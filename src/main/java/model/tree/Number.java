package model.tree;

public class Number extends Node{

    public Float getNumber() {
        return number;
    }

    public void setNumber(Float number) {
        this.number = number;
    }

    private Float number;

    public Number(Float n) {
        super(null, null);
        this.number = n;
    }
}
