package com.cefetmg.problems.nQueens.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertSame;

import org.junit.jupiter.api.Test;

import com.cefetmg.core.interfaces.Individual;
import com.cefetmg.problems.nQueens.model.individuals.IndividualNQueensFactory;

class NQueensStatisticsTest {

    @Test
    void instantiateAndAccessFieldsShouldReturnCorrectValues() {
        int numIndividuals = 20;
        int numEliteIndividuals = 5;
        int maxGenerations = 100;
        int numQueens = 8;
        int generationCount = 67;
        double mutationRate = 0.3;

        IndividualNQueensFactory factory = new IndividualNQueensFactory(numQueens, mutationRate);
        Individual<int[]> mockIndividual = factory.getInstance();

        NQueensStatistics statistics = new NQueensStatistics(mockIndividual, generationCount, numIndividuals,
                numEliteIndividuals, maxGenerations, numQueens, mutationRate);

        assertNotNull(statistics);
        assertSame(mockIndividual, statistics.bestIndividual());
        assertEquals(generationCount, statistics.generationCount());
        assertEquals(numIndividuals, statistics.numIndividuals());
        assertEquals(numEliteIndividuals, statistics.numEliteIndividuals());
        assertEquals(maxGenerations, statistics.maxGenerations());
        assertEquals(numQueens, statistics.numQueens());
        assertEquals(mutationRate, statistics.mutationRate());
    }
}
