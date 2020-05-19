package com.cubearrow.model.problem;

import com.cubearrow.model.rewriting.EquationRewriter;
import com.cubearrow.model.tree.Node;
import com.cubearrow.model.tree.interfaces.Simplifyable;

public class Problem {
    private final ProblemConfig problemConfig;
    private Node topNode;

    public Problem(Node topNode, ProblemConfig problemConfig) {
        this.topNode = topNode;
        this.problemConfig = problemConfig;
    }

    public ProblemConfig getProblemConfig() {
        return problemConfig;
    }

    public void setTopNode(Node topNode) {
        this.topNode = topNode;
    }

    public static Problem fromString(String string, ProblemConfig problemConfig){
        return new Problem(Node.fromString(string, null), problemConfig);
    }

    @Override
    public String toString() {
        return topNode.toString();
    }

    public Node simplify(EquationRewriter equationRewriter) {
        if (this.topNode instanceof Simplifyable simplifyable) {
            return simplifyable.simplify(equationRewriter, this);
        }
        return this.topNode;
    }

    public Node getTopNode() {
        return topNode;
    }

    public static class ProblemConfig {
        public boolean useRadians;
        public char variableToIsolate;

        public boolean isUseRadians() {
            return useRadians;
        }

        public void setUseRadians(boolean useRadians) {
            this.useRadians = useRadians;
        }

        public char getVariableToIsolate() {
            return variableToIsolate;
        }

        public void setVariableToIsolate(char variableToIsolate) {
            this.variableToIsolate = variableToIsolate;
        }

        public ProblemConfig(boolean useRadians) {
            this.useRadians = useRadians;
        }
    }
}
