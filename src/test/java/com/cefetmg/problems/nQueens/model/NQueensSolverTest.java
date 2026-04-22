package com.cefetmg.problems.nQueens.model;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import com.cefetmg.problems.nQueens.model.records.NQueensStatistics;

class NQueensSolverTest {

    @Test
    void solveShouldExecuteAlgorithmAndReturnValidStatistics() {
        NQueensStatistics stats = NQueensSolver.solve();

        assertNotNull(stats);
        assertNotNull(stats.bestIndividual());
        assertNotNull(stats.variables());
        assertTrue(stats.generationCount() > 0, "Deve executar pelo menos uma geração");
        assertTrue(stats.variables().numQueens() >= 4, "Número de rainhas deve ser válido");
    }
}
