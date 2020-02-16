package com.cubearrow.model.operations.utils;

import com.cubearrow.model.operations.Addition;
import com.cubearrow.model.operations.Operation;
import com.cubearrow.model.operations.Subtraction;
import com.cubearrow.model.tree.Node;
import com.cubearrow.model.tree.Variable;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;

@SuppressWarnings("unchecked")
public class MultiplicationLikeOperationUtils {
    final static Class[] NODES_TO_APPLY_TO_DIRECTLY = {Addition.class, Subtraction.class};


    public static Node applyToNode(Node node, Operation operation) {
        if (node instanceof Operation && Arrays.asList(NODES_TO_APPLY_TO_DIRECTLY).contains(node.getClass())) {
            node.setLeft(applyToNode(node.getLeft(), operation));
            node.setRight(applyToNode(node.getRight(), operation));
            return node;
        }
        operation.setLeft(node);
        return operation;
    }

    public static Node distributiveLaw(Operation operation){
        if (operation.getRight() instanceof Variable && (operation.getLeft() instanceof Addition) || (operation.getLeft() instanceof Subtraction)){
            return distributiveLaw((Operation) operation.getLeft(),(Variable) operation.getRight(), operation.getClass());
        }
        else if((operation.getLeft() instanceof Variable) && (operation.getLeft() instanceof Addition || operation.getLeft() instanceof Subtraction)){
            return distributiveLaw((Operation) operation.getRight(), (Variable) operation.getLeft(), operation.getClass());
        }
        return operation;
    }

    private static Node distributiveLaw(Operation left, Variable right, Class baseOperationClass) {
        try {
            Constructor<Operation> baseOperationClassDeclaredConstructor = baseOperationClass.getDeclaredConstructor(Node.class, Node.class);
            return left.getClass().getDeclaredConstructor(Node.class, Node.class).newInstance(
                    (Node) baseOperationClassDeclaredConstructor.newInstance(left.getLeft(), right),
                    baseOperationClassDeclaredConstructor.newInstance(left.getRight(), right));
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
            e.printStackTrace();
        }
        return null;
    }
}
