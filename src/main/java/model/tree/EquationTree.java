package model.tree;

import model.operations.Operation;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("rawtypes")
public class EquationTree extends Node<Operation> {

    public EquationTree(List<Node<Operation>> childrenNodes) {
        super(childrenNodes);
    }

    public void applyOperationToEveryNumberOnAllBaseNodes(Class operationClass, float number, List<Node> nodesToExclude) {
        childrenNodes.forEach(childrenNode -> applyOperationOnNode(childrenNode, operationClass, number, nodesToExclude));
    }

    @SuppressWarnings("unchecked")
    private void applyOperationOnNode(Node node, Class operationClass, float number, List<Node> nodesToExclude) {
        // Initializes a List of new the new Nodes that are included in the parent Node
        List<Node> newChildrenNodes = new ArrayList();

        for (int i = 0; i < node.childrenNodes.size(); i++) {
            Object childrenNode = node.childrenNodes.get(i);
            // Continues with the next iteration if the children node should be excluded
            if (nodesToExclude.contains(childrenNode)) continue;
            // Insert a new operation with the new node as the first parameter
            try {
                List elements = new ArrayList<>() {{
                    add(childrenNode);
                    add(number);
                }};
                // Add the new element to the List of new Nodes
                newChildrenNodes.add((Node) operationClass.getDeclaredConstructor(List.class).newInstance(elements));
            } catch (InstantiationException | IllegalAccessException | NoSuchMethodException | InvocationTargetException e) {
                e.printStackTrace();
            }

        }
        // Set the new children nodes to the parent node
    node.setChildrenNodes(newChildrenNodes);
    }

    Variable[] getVariables(List<Node> childrenNodes) {
        ArrayList<Variable> vars = new ArrayList<>();
        for (Node childrenNode : childrenNodes) {
            if (childrenNode instanceof Variable) {
                vars.add((Variable) childrenNode);
            } else if (childrenNode instanceof Operation) {
                for (Node variable : getVariables(childrenNode.getChildrenNodes())) {
                    vars.add((Variable) variable);
                }
            }
        }
        return vars.toArray(new Variable[0]);
    }
}
