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
    }

    public char askUserForVariableToIsolate(){
        HashMap<Variable, Node> variables = equation.getVariables();
        List<Character> variableCharSet = getVariableCharSet(variables);
        if(variableCharSet.size() == 1){
            return variableCharSet.get(0);
        }

        System.out.println(String.format("%sInput | %sWhich of these Variables should be solved? %s", ConsoleColors.ANSI_YELLOW,ConsoleColors.ANSI_RESET, variableCharSet));

        Scanner sc = new Scanner(System.in);
        String selectedChar = sc.next();

        if(selectedChar.length() != 1){
            System.out.println(String.format("%SError | %sPlease only specify one character.", ConsoleColors.ANSI_RED, ConsoleColors.ANSI_RESET));
        }
        else if(!variableCharSet.contains(selectedChar.charAt(0))){
            System.out.println(String.format("%sError | %sThe specified character is not an option.", ConsoleColors.ANSI_RED, ConsoleColors.ANSI_RESET));
        }
        else {
            System.out.println(String.format("%sSuccess | %sThe variable %s will be isolated.", ConsoleColors.ANSI_GREEN, ConsoleColors.ANSI_RESET, selectedChar));
            return selectedChar.charAt(0);
        }
        return '\u0000';
    }
}
