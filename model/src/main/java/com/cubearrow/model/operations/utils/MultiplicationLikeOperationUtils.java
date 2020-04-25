package com.cubearrow.model.operations.utils;

import com.cubearrow.model.operations.Addition;
import com.cubearrow.model.operations.Multiplication;
import com.cubearrow.model.operations.Operation;
import com.cubearrow.model.operations.Subtraction;
import com.cubearrow.model.tree.Node;
import com.cubearrow.model.tree.Number;
import com.cubearrow.model.tree.Variable;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.List;

public class MultiplicationLikeOperationUtils {
    /**
     * A list of classes of nodes that when applying, you have to apply to every child of that operation
     */
    final static List<java.lang.Class> NODES_TO_APPLY_TO_DIRECTLY = Arrays.asList(Addition.class, Subtraction.class);

    /**
     * Applies an operation that is either a {@link Multiplication} or a {@link com.cubearrow.model.operations.Division} to a node
     *
     * @param nodeToApplyTo The node that the operation is applied to
     * @param appliedOperation The operation that is applied
     * @return Returns the applied and resulting {@link Node}
     */
    public static Node applyToNode(Node nodeToApplyTo, Operation appliedOperation) {
        if (nodeToApplyTo instanceof Operation && NODES_TO_APPLY_TO_DIRECTLY.contains(nodeToApplyTo.getClass())) {
            nodeToApplyTo.setLeft(applyToNode(nodeToApplyTo.getLeft(), (Operation) appliedOperation.clone()));
            nodeToApplyTo.setRight(applyToNode(nodeToApplyTo.getRight(), (Operation) appliedOperation.clone()));
            return nodeToApplyTo;
        }
        appliedOperation.setLeft(nodeToApplyTo);
        return appliedOperation;
    }

}
