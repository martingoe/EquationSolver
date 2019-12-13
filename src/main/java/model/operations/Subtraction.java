package model.operations;

public class Subtraction extends Operation {
    public Subtraction(Object[] elements) {
        super(elements);
    }

    @Override
    public float operationFunction(float x1, float x2) {
        return x1 - x2;
    }
}
