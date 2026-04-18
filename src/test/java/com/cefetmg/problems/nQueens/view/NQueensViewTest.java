package com.cefetmg.problems.nQueens.view;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import com.cefetmg.core.interfaces.Individual;
import com.cefetmg.problems.nQueens.model.individuals.IndividualNQueensFactory;
import com.cefetmg.problems.nQueens.model.records.NQueensStatistics;
import com.cefetmg.problems.nQueens.model.records.NQueensVariables;

class NQueensViewTest {

    @Test
    void displayShouldFormatCorrectlyAllFields() {
        NQueensVariables vars = new NQueensVariables(8, 20, 5, 2000);
        Individual<int[]> mockIndividual = new IndividualNQueensFactory(8).getInstance();
        NQueensStatistics stats = new NQueensStatistics(mockIndividual, 75, vars);

        String result = NQueensView.display(stats);

        assertNotNull(result);
        assertTrue(result.contains("Algoritmo Genético - Problema das N Rainhas"));
        assertTrue(result.contains("Número de Rainhas: 8"));
        assertTrue(result.contains("Número de Indivíduos: 20"));
        assertTrue(result.contains("Número de Elites: 5"));
        assertTrue(result.contains("Máximo de Gerações: 2000"));
        assertTrue(result.contains("Contagem de Gerações: 75"));
        assertTrue(result.contains(mockIndividual.toString()));
    }
}
