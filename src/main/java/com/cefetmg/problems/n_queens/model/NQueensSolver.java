package com.cefetmg.problems.n_queens.model;

import com.cefetmg.core.GeneticAlgorithm;
import com.cefetmg.core.interfaces.Individual;
import com.cefetmg.problems.n_queens.model.individuals.IndividualNQueensFactory;

public class NQueensSolver {

    private final static boolean IS_MAXIMIZATION = false;

    public static NQueensStatistics solve(int numQueens, double mutationRate, int numIndividuals, int numEliteIndividuals, int maxGenerations) {
        IndividualNQueensFactory factory = new IndividualNQueensFactory(numQueens, mutationRate);
        GeneticAlgorithm<int[]> algorithm = new GeneticAlgorithm<>();

        Individual<int[]> individual = algorithm.execute(factory, numIndividuals,
                numEliteIndividuals, maxGenerations, IS_MAXIMIZATION);

        return new NQueensStatistics(
                individual,
                algorithm.getGenerationCount(),
                numIndividuals,
                numEliteIndividuals,
                maxGenerations,
                numQueens,
                mutationRate);
    }
}
