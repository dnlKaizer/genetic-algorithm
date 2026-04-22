package com.cefetmg.problems.nQueens.model.records;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

class NQueensVariablesTest {

    @Test
    void instantiateAndAccessFieldsShouldReturnCorrectValues() {
        NQueensVariables variables = new NQueensVariables(8, 20, 5, 100);

        assertEquals(8, variables.numQueens());
        assertEquals(20, variables.numIndividuals());
        assertEquals(5, variables.numEliteIndividuals());
        assertEquals(100, variables.maxGenerations());
    }
}
