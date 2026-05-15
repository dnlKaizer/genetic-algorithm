package com.cefetmg.problems.n_queens.view;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.jupiter.api.Test;

import com.cefetmg.core.interfaces.Individual;
import com.cefetmg.problems.n_queens.model.NQueensStatistics;
import com.cefetmg.problems.n_queens.model.individuals.IndividualNQueensFactory;

class NQueensViewTest {

    @Test
    void displayShouldFormatCorrectlyAllFields() {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream originalOut = System.out;
        System.setOut(new PrintStream(outputStream));

        try {
            int numIndividuals = 20;
            int numEliteIndividuals = 5;
            int maxGenerations = 100;
            int numQueens = 8;
            int generationCount = 67;
            double mutationRate = 0.3;

            IndividualNQueensFactory factory = new IndividualNQueensFactory(numQueens, mutationRate);
            Individual<int[]> mockIndividual = factory.getInstance();

            NQueensStatistics stats = new NQueensStatistics(mockIndividual, generationCount, numIndividuals,
                    numEliteIndividuals, maxGenerations, numQueens, mutationRate);

            NQueensView.display(stats);

            String result = outputStream.toString();

            assertNotNull(result);
            assertTrue(result.contains("Algoritmo Genético - Problema das N Rainhas"));
            assertTrue(result.contains("Número de Rainhas: " + numQueens));
            assertTrue(result.contains("Número de Indivíduos: " + numIndividuals));
            assertTrue(result.contains("Número de Elites: " + numEliteIndividuals));
            assertTrue(result.contains("Máximo de Gerações: " + maxGenerations));
            assertTrue(result.contains("Contagem de Gerações: " + generationCount));
            assertTrue(result.contains("Taxa de Mutação: " + mutationRate));
            assertTrue(result.contains(mockIndividual.toString()));
        } finally {
            System.setOut(originalOut);
        }
    }
}
