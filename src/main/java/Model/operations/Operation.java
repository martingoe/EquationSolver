package Model.operations;

public interface Operation {
    float operationFunction(float x1, float x2);

    /**
     * Validates the input elements. If an element is is a operation, it will add the result of that operation
     *
     * @param elements The input elements
     * @return The validated result
     */
    default Object[] validateObjects(Object[] elements) {
        int lengthOfObjects = elements.length;
        Object[] validatedObjects = new Object[lengthOfObjects];
        for (int i = 0; i < lengthOfObjects - 1; i++) {
            Object element = elements[i];
            if (element instanceof Operation) {
                validatedObjects[i] = ((Operation) element).getResult(elements);
                continue;
            }
            validatedObjects[i] = elements[i];
        }
        return validatedObjects;
    }

    default Float getResult(Object[] elements) {
        Object[] validatedElements = validateObjects(elements);
        float result = (float) validatedElements[0];
        for (int i = 1; i < validatedElements.length - 1; i++) {
            result = operationFunction(result, (Float) validatedElements[i]);
        }
        return result;
    }
}
