package model.equation;

import model.tree.Node;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class EquationInitializer {

    String equationString;

    public EquationInitializer(String equationString) {
        this.equationString = equationString;
    }

    public List<Node> getChildrenNodes() {
        String[] equationParts = this.equationString.split("=");
        return Arrays.stream(equationParts).map(this::getNodesFromPartString).collect(Collectors.toList());
    }

    private Node getNodesFromPartString(String equationPart) {
        System.out.println(equationPart);
        return null;
    }

}
