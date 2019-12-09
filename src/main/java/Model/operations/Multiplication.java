package Model.operations;

import com.sun.source.tree.BreakTree;

public class Multiplication implements Operation {
    Object[] elements;

    public Multiplication(Object[] elements) {
        this.elements = elements;
    }


    public float getResult() {
        return this.getResult(this.elements);
    }

    public float operationFunction(float x1, float x2) {
        return x1 * x2;
    }
}
