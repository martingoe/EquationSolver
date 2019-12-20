package model.tree;

import model.operations.Operation;

import java.util.ArrayList;
import java.util.List;

public class EquationTree extends Node<Operation> {

    Node<Variable>[] getVariables(Node[] childrenNodes){
        ArrayList<Node<Variable>> vars = new ArrayList<>();
        for (int i = 0; i < childrenNodes.length; i++) {

        }
        return null;
    }


    public EquationTree(List<Node<Operation>> childrenNodes) {
        super(childrenNodes);
    }
}
