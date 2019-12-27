package model.tree;

import java.util.List;

public class Node {
    List<Node> childrenNodes;

    public Node(List<Node> childrenNodes) {
        this.childrenNodes = childrenNodes;
    }

    public List<Node> removeChildrenNode(Node nodeToBeRemoved) {
        this.childrenNodes.remove(nodeToBeRemoved);
        return childrenNodes;
    }

    public List<Node> removeChildrenNode(int index) {
        this.childrenNodes.remove(index);
        return childrenNodes;
    }

    public List<Node> insertChildrenNode(Node nodeToBeInserted) {
        this.childrenNodes.add(nodeToBeInserted);

        return this.childrenNodes;
    }

    public List<Node> getChildrenNodes() {
        return childrenNodes;
    }

    public void setChildrenNodes(List<Node> childrenNodes) {
        this.childrenNodes = childrenNodes;
    }
}
