package com.cefetmg.problems.math_functions.controller;

import java.util.ArrayList;
import java.util.List;

import com.cefetmg.config.variables.VarsExecutionDefaults;
import com.cefetmg.config.variables.VarsIndividualMathFunctions;
import com.cefetmg.config.variables.VarsExecutionDefaults.InnerVarsExecutionDefaults;
import com.cefetmg.config.variables.VarsIndividualMathFunctions.InnerVarsIndividualMathFuncitons;
import com.cefetmg.problems.math_functions.model.MathFunctionSolver;
import com.cefetmg.problems.math_functions.model.MathFunctionStatistics;
import com.cefetmg.problems.math_functions.model.functions.DixonPriceFunction;
import com.cefetmg.problems.math_functions.model.functions.LangermannFunction;
import com.cefetmg.problems.math_functions.model.individuals.CrossoverType;
import com.cefetmg.problems.math_functions.model.individuals.MathFunction;
import com.cefetmg.problems.math_functions.view.MathFunctionView;

public class MathFunctionController {

    public static void execute() {
        while (true) {
            if (!MathFunctionView.shouldContinue()) 
                break;

            InnerVarsExecutionDefaults defaultVars = VarsExecutionDefaults.getInnerVarsExecutionDefaults();
            InnerVarsIndividualMathFuncitons individualVars = VarsIndividualMathFunctions
                    .getInnerVarsIndividualMathFuncitons();

            List<MathFunctionStatistics> statisticsList = new ArrayList<>();

            executeLangermann(individualVars, defaultVars, statisticsList);
            executeDixonPrice(individualVars, defaultVars, statisticsList);
            executePowell(individualVars, defaultVars, statisticsList);

            MathFunctionView.display(statisticsList);
        }
    }

    private static void executeLangermann(InnerVarsIndividualMathFuncitons individualVars,
            InnerVarsExecutionDefaults defaultVars, List<MathFunctionStatistics> statisticsList) {
                
        if (!individualVars.langermann().execute()) {
            return;
        }

        MathFunction function = new LangermannFunction(individualVars.langermann().c(),
                individualVars.langermann().a());

        executeFunction(individualVars, defaultVars, statisticsList, function, individualVars.langermann().crossoverType());
    }

    private static void executeDixonPrice(InnerVarsIndividualMathFuncitons individualVars,
            InnerVarsExecutionDefaults defaultVars, List<MathFunctionStatistics> statisticsList) {

        if (!individualVars.dixonPrice().execute()) {
            return;
        }

        MathFunction function = new DixonPriceFunction(individualVars.dixonPrice().dimensions());
        executeFunction(individualVars, defaultVars, statisticsList, function, individualVars.dixonPrice().crossoverType());
    }

    private static void executePowell(InnerVarsIndividualMathFuncitons individualVars,
            InnerVarsExecutionDefaults defaultVars, List<MathFunctionStatistics> statisticsList) {

        if (!individualVars.powell().execute()) {
            return;
        }

        MathFunction function = new DixonPriceFunction(individualVars.powell().dimensions());
        executeFunction(individualVars, defaultVars, statisticsList, function, individualVars.powell().crossoverType());
    }

    private static void executeFunction(InnerVarsIndividualMathFuncitons individualVars,
            InnerVarsExecutionDefaults defaultVars, List<MathFunctionStatistics> statisticsList,
            MathFunction function, CrossoverType crossoverType) {

        if (crossoverType == null) {
            executeFunction(individualVars, defaultVars, statisticsList, function, CrossoverType.BLX_ALPHA);
            executeFunction(individualVars, defaultVars, statisticsList, function, CrossoverType.ARITHMETIC);
            return;
        }

        MathFunctionStatistics statistics = MathFunctionSolver.solve(
                individualVars.mutationRate(),
                individualVars.alpha(),
                defaultVars.numIndividuals(),
                defaultVars.numEliteIndividuals(),
                defaultVars.maxGenerations(),
                function,
                crossoverType);

        statisticsList.add(statistics);
    }

}
