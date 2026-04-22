package com.cefetmg.problems.nQueens.model.records;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertSame;

import org.junit.jupiter.api.Test;

import com.cefetmg.core.interfaces.Individual;
import com.cefetmg.problems.nQueens.model.individuals.IndividualNQueensFactory;

class NQueensStatisticsTest {

    @Test
    void instantiateAndAccessFieldsShouldReturnCorrectValues() {
        NQueensVariables variables = new NQueensVariables(8, 20, 5, 100);
        IndividualNQueensFactory factory = new IndividualNQueensFactory(8);
        Individual<int[]> mockIndividual = factory.getInstance();

        NQueensStatistics statistics = new NQueensStatistics(mockIndividual, 50, variables);

        assertNotNull(statistics);
        assertSame(mockIndividual, statistics.bestIndividual());
        assertEquals(50, statistics.generationCount());
        assertSame(variables, statistics.variables());
    }
}
