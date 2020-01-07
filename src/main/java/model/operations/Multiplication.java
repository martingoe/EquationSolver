package model.operations;

import model.tree.Node;
import model.tree.Number;
import model.tree.Variable;

import java.lang.reflect.InvocationTargetException;

public class Multiplication extends Operation {

    final Class OPPOSITE_OPERATION = Division.class;

    public Multiplication(Node right, Node left) {
        super(right, left);
    }


    @Override
    public Node simplify() {
        if (this.getRight() instanceof Variable && (this.getLeft() instanceof Addition) || (this.getLeft() instanceof Subtraction)){
            return distributive_law((Operation) this.getLeft(),(Variable) this.getRight());
        }
        else if((this.getLeft() instanceof Variable) && (this.getLeft() instanceof Addition || this.getLeft() instanceof Subtraction)){
            return distributive_law((Operation) this.getRight(), (Variable) this.getLeft());
        }
        return this;
    }

    private Node distributive_law(Operation left, Variable right) {
        try {
            return left.getClass().getDeclaredConstructor(Node.class, Node.class).newInstance(new Multiplication(left.getLeft(), right), new Multiplication(left.getRight(), right));
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Number getResultFromNumbers(Number n1, Number n2) {
        return new Number(n1.getNumber() * n2.getNumber());
    }
}
