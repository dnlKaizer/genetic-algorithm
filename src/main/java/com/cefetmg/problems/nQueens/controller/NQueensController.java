package com.cefetmg.problems.nQueens.controller;

import com.cefetmg.config.variables.VarsExecutionDefaults;
import com.cefetmg.config.variables.VarsIndividualNQueens;
import com.cefetmg.config.variables.VarsExecutionDefaults.InnerVarsExecutionDefaults;
import com.cefetmg.config.variables.VarsIndividualNQueens.InnerVarsIndividualNQueens;

import com.cefetmg.problems.nQueens.model.NQueensSolver;
import com.cefetmg.problems.nQueens.view.NQueensView;

public class NQueensController {

    public static void execute() {
        while (true) {
            InnerVarsExecutionDefaults defaultVars = VarsExecutionDefaults.getInnerVarsExecutionDefaults();
            InnerVarsIndividualNQueens nQueensVars = VarsIndividualNQueens.getInnerVarsIndividualNQueens();

            if (!NQueensView.shouldContinue())
                break;

            NQueensView.display(NQueensSolver.solve(nQueensVars.numQueens(),
                    nQueensVars.mutationRate(), defaultVars.numIndividuals(), defaultVars.numEliteIndividuals(),
                    defaultVars.maxGenerations()));
        }
    }
}
