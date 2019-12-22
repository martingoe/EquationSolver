package model.operations;

import model.tree.Node;
import model.tree.Variable;

import java.util.List;

public class Operation extends Node {

    public Operation(List<Node> elements) {
        super(elements);

    }

    float operationFunction(Object[] objects) {
        return 0f;
    }

    public float getResult() {
        return this.getResult(this.getChildrenNodes());
    }

    /**
     * Validates the input elements. If an element is is a operation, it will add the result of that operation
     *
     * @param elements The input elements
     * @return The validated result
     */
    Object[] validateObjects(List<Node> elements) {
        int lengthOfObjects = elements.size();
        Object[] validatedObjects = new Object[lengthOfObjects];
        for (int i = 0; i < lengthOfObjects; i++) {
            Object element = elements.get(i);
            if (element instanceof Variable) {
                return null;
            } else if(element instanceof Operation){
                validatedObjects[i] = ((Operation) element).getResult();
            } else {
                validatedObjects[i] = element;
            }
        }
        return validatedObjects;
    }

    Float getResult(List elements) {
        Object[] validatedElements = validateObjects(elements);
        return validatedElements != null ? operationFunction(validatedElements) : null;
    }
}
