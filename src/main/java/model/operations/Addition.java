package model.operations;

public class Addition extends Operation {
    public Addition(Object[] elements) {
        super(elements);
    }

    @Override
    public float operationFunction(float x1, float x2) {
        return x1 + x2;
    }
}
