package model.tree;

import model.operations.Operation;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@SuppressWarnings("rawtypes")
public class EquationTree extends Node {

    /**
     * Initializes an {@link EquationTree}. This is what saves an equation
     * @param childrenNodes The nodes that are contained in the equation. Usually 2 elements long
     */
    public EquationTree(List<Node> childrenNodes) {
        super(childrenNodes);
    }


    /**
     * Applies an operation on every children node of the equation
     *
     * @param operationClass          the {@link java.lang.Class} of the {@link Operation} that is supposed to be applied
     * @param secondObjectOfOperation The secondObjectOfOperation that is applied to the {@link Operation}
     * @param nodesToExclude          A {@link java.util.List} of {@link Node} that should be excluded from the change
     */
    @SuppressWarnings("unchecked")
    public void applyOperationToNodes(Class operationClass, Object secondObjectOfOperation, List<Node> nodesToExclude) {
        // Initializes a List of new the new Nodes that are included in the parent Node
        List<Node> newChildrenNodes = new ArrayList();

        for (int i = 0; i < childrenNodes.size(); i++) {
            Object childrenNode = childrenNodes.get(i);
            // Continues with the next iteration if the children node should be excluded
            if (nodesToExclude.contains(childrenNode)) continue;
            // Insert a new operation with the new node as the first parameter
            try {
                List elements = new ArrayList<>() {{
                    add(childrenNode);
                    add(secondObjectOfOperation);
                }};
                // Add the new element to the List of new Nodes
                newChildrenNodes.add((Node) operationClass.getDeclaredConstructor(List.class).newInstance(elements));
            } catch (InstantiationException | IllegalAccessException | NoSuchMethodException | InvocationTargetException e) {
                e.printStackTrace();
            }

        }
        // Set the new children nodes to the parent node
        setChildrenNodes(newChildrenNodes);
    }

    /**
     * Gets the {@link Variable}s with the {@link Node}s that are in the equation
     * <p>
     * This function is recursive and hence needs a {@link List} of {@link Variable}s as a Argument
     *
     * @param parentNode The current {@link Node} that of the recursion
     * @return Returns the {@link Variable}s as a {@link HashMap}
     */
    public HashMap<Variable, Node> getVariables(Node parentNode) {
        HashMap<Variable, Node> vars = new HashMap<>();
        // Iterate over the children nodes in order to check them all
        for (Node childrenNode : parentNode.getChildrenNodes()) {
            // Add the node to the List of Variables
            if (childrenNode instanceof Variable) {
                vars.put((Variable) childrenNode, parentNode);
            }
            // If the children node is a operation, add the variables in that operation
            else if (childrenNode instanceof Operation) {
                vars.putAll(getVariables(childrenNode));
            }
        }
        // Return the list of Variables
        return vars;
    }
}
