package model.operations;


import model.tree.Number;

public interface Operation {
    void simplify();

    Number getResultFromNumbers(Number n1, Number n2);

}
