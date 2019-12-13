package model.operations;

public class Operation {
    Object[] elements;

    float operationFunction(float x1, float x2){
        return 0f;
    }
    public float getResult() {
        return this.getResult(this.elements);
    }

    public Operation(Object[] elements) {
        this.elements = elements;
    }

    /**
     * Validates the input elements. If an element is is a operation, it will add the result of that operation
     *
     * @param elements The input elements
     * @return The validated result
     */
    Object[] validateObjects(Object[] elements) {
        int lengthOfObjects = elements.length;
        Object[] validatedObjects = new Object[lengthOfObjects];
        for (int i = 0; i < lengthOfObjects; i++) {
            Object element = elements[i];
            if (element instanceof Operation) {
                validatedObjects[i] = ((Operation) element).getResult();
            }
            else {
                validatedObjects[i] = elements[i];
            }
        }
        return validatedObjects;
    }

    Float getResult(Object[] elements) {
        Object[] validatedElements = validateObjects(elements);
        float result = (float) validatedElements[0];
        for (int i = 1; i < validatedElements.length; i++) {
            result = operationFunction(result, (Float) validatedElements[i]);
        }
        return result;
    }
}
