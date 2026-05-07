package com.cefetmg.problems.nQueens.model;

import com.cefetmg.core.GeneticAlgorithm;
import com.cefetmg.core.interfaces.Individual;

import com.cefetmg.problems.nQueens.model.individuals.IndividualNQueensFactory;

public class NQueensSolver {

    public static NQueensStatistics solve(int numQueens, double mutationRate, int numIndividuals, int numEliteIndividuals, int maxGenerations) {
        IndividualNQueensFactory factory = new IndividualNQueensFactory(numQueens, mutationRate);
        GeneticAlgorithm<int[]> algorithm = new GeneticAlgorithm<>();

        Individual<int[]> individual = algorithm.execute(factory, numIndividuals,
                numEliteIndividuals, maxGenerations);

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
