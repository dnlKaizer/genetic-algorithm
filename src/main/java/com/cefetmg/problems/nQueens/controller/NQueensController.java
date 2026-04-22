package com.cefetmg.problems.nQueens.controller;

import com.cefetmg.problems.nQueens.model.NQueensSolver;
import com.cefetmg.problems.nQueens.view.NQueensView;

public class NQueensController {
    
    public static void execute() {
        while (true) {
            System.out.print("\nExecutar (qualquer caracter cancela): ");
            String input = System.console().readLine();
            if (!input.equalsIgnoreCase("")) {
                break;
            }

            System.out.println(NQueensView.display(NQueensSolver.solve()));
        }
    }
}
