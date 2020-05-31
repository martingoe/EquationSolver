package de.cubearrow.restview.services;

import com.cubearrow.model.problem.Problem;
import com.cubearrow.model.problem.ProblemManager;
import com.cubearrow.model.tree.Node;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class ProblemService {
    ProblemManager problemService = new ProblemManager();

    @GetMapping("/problem")
    public String solveEquation(@RequestParam(name = "problem") String projectString) {
        return String.format("{\"input\":\"%S\", \"result\": \"%s\"}", projectString, this.problemService.solveProblem(new Problem(Node.fromString(projectString, null), new Problem.ProblemConfig(false))));
    }
}
