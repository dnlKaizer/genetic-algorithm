package com.cefetmg.problems.nQueens.model;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

class NQueensSolverTest {

    @Test
    void solveShouldExecuteAlgorithmAndReturnValidStatistics() {
        int numQueens = 8;
        int numIndividuals = 20;
        int numEliteIndividuals = 5;
        int maxGenerations = 2000;
        double mutationRate = 0.3;

        NQueensStatistics stats = NQueensSolver.solve(numQueens, mutationRate, numIndividuals, numEliteIndividuals, maxGenerations);

        assertNotNull(stats);
        assertNotNull(stats.bestIndividual());

        assertTrue(stats.numQueens() == numQueens, "Número de rainhas deve ser " + numQueens);
        assertTrue(stats.numIndividuals() == numIndividuals, "Número de indivíduos deve ser " + numIndividuals);
        assertTrue(stats.numEliteIndividuals() == numEliteIndividuals, "Número de elites deve ser " + numEliteIndividuals);
        assertTrue(stats.maxGenerations() == maxGenerations, "Número máximo de gerações deve ser " + maxGenerations);
        assertTrue(stats.mutationRate() == mutationRate, "Taxa de mutação deve ser " + mutationRate);

        assertTrue(stats.generationCount() > 0, "Deve executar pelo menos uma geração");
    }
}
