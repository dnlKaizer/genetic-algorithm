package com.cefetmg.problems.nQueens.model;

import com.cefetmg.config.variables.VarsExecutionDefaults;
import com.cefetmg.core.GeneticAlgorithm;
import com.cefetmg.core.interfaces.Individual;
import com.cefetmg.problems.nQueens.model.individuals.IndividualNQueensFactory;
import com.cefetmg.problems.nQueens.model.records.NQueensStatistics;
import com.cefetmg.problems.nQueens.model.records.NQueensVariables;

public class NQueensSolver {

    public static NQueensStatistics solve() {
        NQueensVariables vars = new NQueensVariables(
            VarsExecutionDefaults.getNumQueens(),
            VarsExecutionDefaults.getNumIndividuals(),
            VarsExecutionDefaults.getNumEliteIndividuals(),
            VarsExecutionDefaults.getMaxGenerations()
        );

        IndividualNQueensFactory factory = new IndividualNQueensFactory(vars.numQueens());
        GeneticAlgorithm<int[]> algorithm = new GeneticAlgorithm<>();
        Individual<int[]> individual = algorithm.execute(factory, vars.numIndividuals(), vars.numEliteIndividuals(), vars.maxGenerations());

        return new NQueensStatistics(individual, algorithm.getGenerationCount(), vars);
    }
}
