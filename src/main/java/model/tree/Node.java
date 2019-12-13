package model.tree;

import java.util.List;

public class Node<T> {
    List<Node<T>> childrenNodes;

    public Node(List<Node<T>> childrenNodes) {
        this.childrenNodes = childrenNodes;
    }

    public List<Node<T>> removeChildrenNode(Node<T> nodeToBeRemoved) {
        this.childrenNodes.remove(nodeToBeRemoved);
        return childrenNodes;
    }

    public List<Node<T>> removeChildrenNode(int index) {
        this.childrenNodes.remove(index);
        return childrenNodes;
    }

    public List<Node<T>> insertChildrenNode(Node<T> nodeToBeInserted) {
        this.childrenNodes.add(nodeToBeInserted);

        return this.childrenNodes;
    }

    public List<Node<T>> getChildrenNodes() {
        return childrenNodes;
    }
}
