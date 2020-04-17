package com.cubearrow.view;

import com.cubearrow.model.equation.Equation;
import com.cubearrow.model.tree.Node;
import com.cubearrow.model.tree.Variable;
import com.cubearrow.view.utils.ConsoleColors;

import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

import static com.cubearrow.view.utils.VariableUtilities.getVariableCharSet;

public class Interaction {
    private Equation equation;

    public Interaction(Equation equation) {
        this.equation = equation;
        printInformation(String.format("This is the equation to be solved: %s%s", ConsoleColors.ANSI_CYAN, equation.toString()));
    }

    public Interaction() {

    }

    public char askUserForVariableToIsolate() {
        List<Variable> variables = equation.getVariables();
        List<Character> variableCharSet = getVariableCharSet(variables);
        if (variableCharSet.size() == 1) {
            return variableCharSet.get(0);
        }

        String selectedChar = getInput(String.format("Which variable should be isolated? %s", variableCharSet.toString()));


        if (selectedChar.length() != 1) {
                printError("Please only specify one character.");
        } else if (!variableCharSet.contains(selectedChar.charAt(0))) {
            printError("The specified character is not an option.");
        } else {
            printSuccess(String.format("The variable %s will be isolated.", selectedChar));
            return selectedChar.charAt(0);
        }
        return '\u0000';
    }

    private String getInput(String message){
        System.out.println(String.format("%sInput %s| %s%s", ConsoleColors.ANSI_YELLOW, ConsoleColors.ANSI_GREY, ConsoleColors.ANSI_RESET, message));

        Scanner sc = new Scanner(System.in);
        return sc.next();

    }
    public void printInformation(String message){
        System.out.println(String.format("%sInformation %s| %s%s", ConsoleColors.ANSI_BLUE, ConsoleColors.ANSI_GREY, ConsoleColors.ANSI_RESET, message));
    }
    public void printError(String message){
        System.out.println(String.format("%sError %s| %s%s", ConsoleColors.ANSI_RED, ConsoleColors.ANSI_GREY, ConsoleColors.ANSI_RESET, message));
    }

    public void printSuccess(String message) {
        System.out.println(String.format("%sSuccess %s| %s%s", ConsoleColors.ANSI_GREEN, ConsoleColors.ANSI_GREY, ConsoleColors.ANSI_RESET, message));
    }

    public void displayResult(Equation simplifiedEquation) {
        System.out.println(String.format("%sSuccess %s| %sThis is the simplified/solved equation:\n%s", ConsoleColors.ANSI_GREEN, ConsoleColors.ANSI_GREY, ConsoleColors.ANSI_RESET, simplifiedEquation.toString()));
    }
}
