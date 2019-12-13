package model.operations;

public class Multiplication extends Operation {

    public Multiplication(Object[] elements) {
        super(elements);
    }


    @Override
    public float operationFunction(float x1, float x2) {
        return x1 * x2;
    }
}
