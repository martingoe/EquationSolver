package com.cubearrow.view;

import com.cubearrow.model.problem.Problem;
import com.cubearrow.model.tree.nodes.Equation;
import com.cubearrow.model.tree.nodes.Variable;
import com.cubearrow.model.tree.Node;
import com.cubearrow.view.utils.ConsoleColors;

import java.util.List;
import java.util.Scanner;

import static com.cubearrow.view.utils.VariableUtil.getVariableCharSet;

public class Interaction {
    private Problem problem;

    public Interaction(Problem equation) {
        this.problem = equation;
        printInformation(String.format("This is the problem to be solved: %s%s", ConsoleColors.ANSI_CYAN, equation.toString()));
    }

    public Interaction() {

    }

    public char askUserForVariableToIsolate() {
        List<Variable> variables = ((Equation) problem.getTopNode()).getVariables();
        List<Character> variableCharSet = getVariableCharSet(variables);
        if (variableCharSet.size() == 1) {
            return variableCharSet.get(0);
        }

        String selectedChar = getInput(String.format("Which variable should be isolated? %s", variableCharSet.toString()));


        if (selectedChar.length() == 1) {
            printSuccess(String.format("The variable %s will be isolated.", selectedChar));
            return selectedChar.charAt(0);
        } else if (!variableCharSet.contains(selectedChar.charAt(0))) {
            printError("The specified character is not an option.");
        } else {
            printError("Please only specify one character.");
        }
        return '\u0000';
    }

    private String getInput(String message) {
        System.out.println(String.format("%sInput %s| %s%s", ConsoleColors.ANSI_YELLOW, ConsoleColors.ANSI_GREY, ConsoleColors.ANSI_RESET, message));

        Scanner sc = new Scanner(System.in);
        return sc.next();

    }

    public void printInformation(String message) {
        System.out.println(String.format("%sInformation %s| %s%s", ConsoleColors.ANSI_BLUE, ConsoleColors.ANSI_GREY, ConsoleColors.ANSI_RESET, message));
    }

    public void printError(String message) {
        System.out.println(String.format("%sError %s| %s%s", ConsoleColors.ANSI_RED, ConsoleColors.ANSI_GREY, ConsoleColors.ANSI_RESET, message));
    }

    public void printSuccess(String message) {
        System.out.println(String.format("%sSuccess %s| %s%s", ConsoleColors.ANSI_GREEN, ConsoleColors.ANSI_GREY, ConsoleColors.ANSI_RESET, message));
    }

    public void displayResult(Node simplifiedEquation) {
        System.out.println(String.format("%sSuccess %s| %sThis is the simplified/solved problem:\n%s", ConsoleColors.ANSI_GREEN, ConsoleColors.ANSI_GREY, ConsoleColors.ANSI_RESET, simplifiedEquation.toString()));
    }
}
