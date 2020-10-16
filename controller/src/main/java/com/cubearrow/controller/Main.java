package com.cubearrow.controller;

import com.cubearrow.model.problem.Problem;
import com.cubearrow.model.problem.ProblemManager;
import com.cubearrow.model.tree.Node;
import com.cubearrow.view.Interaction;
import de.cubearrow.restview.services.ProblemService;
import org.springframework.boot.SpringApplication;

public class Main {
    public static void main(String[] args) {
        Problem test = Problem.fromString("1/4 * (4 - 1/2)", new Problem.ProblemConfig(false));
        Interaction interaction = new Interaction(test);
        ProblemManager problemManager = new ProblemManager();
        Node prevNode = null;
        while (prevNode != test.getTopNode()) {
            prevNode = test.getTopNode();
            test.setTopNode(problemManager.solveProblem(test));
        }
        interaction.displayResult(test.getTopNode());

    }
}
