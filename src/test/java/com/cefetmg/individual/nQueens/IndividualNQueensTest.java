package com.cefetmg.individual.nQueens;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotSame;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.lang.reflect.Constructor;
import java.util.List;

import org.junit.jupiter.api.Test;

public class IndividualNQueensTest {

	private IndividualNQueens createIndividual(int[] genes) {
		try {
			Constructor<IndividualNQueens> constructor = IndividualNQueens.class.getDeclaredConstructor(int.class, int[].class);
			constructor.setAccessible(true);
			return constructor.newInstance(genes.length, genes.clone());
		} catch (ReflectiveOperationException exception) {
			throw new IllegalStateException(exception);
		}
	}

	@Test
	void getEvaluationShouldReturnZeroForValidSolution() {
		IndividualNQueens individual = createIndividual(new int[] { 1, 3, 0, 2 });

		assertEquals(0, individual.getFitness());
	}

	@Test
	void getEvaluationShouldCountCollisionsForInvalidSolution() {
		IndividualNQueens individual = createIndividual(new int[] { 0, 0, 0, 0 });

		assertEquals(6, individual.getFitness());
	}

	@Test
	void toStringShouldExposeGenes() {
		IndividualNQueens individual = createIndividual(new int[] { 1, 3, 0, 2 });

		assertEquals("Indivíduo: [ { 1 3 0 2 } ], Avaliação: 0.0", individual.toString());
	}

	@Test
	void recombineShouldReturnTwoChildrenWithValidGeneValues() {
		IndividualNQueens parent1 = createIndividual(new int[] { 0, 0, 0, 0 });
		IndividualNQueens parent2 = createIndividual(new int[] { 1, 1, 1, 1 });

		List<com.cefetmg.individual.interfaces.Individual<int[]>> children = parent1.recombine(parent2);

		assertEquals(2, children.size());

		for (com.cefetmg.individual.interfaces.Individual<int[]> child : children) {
			assertEquals(4, child.getGenes().length);
			for (int gene : child.getGenes()) {
				assertTrue(gene == 0 || gene == 1);
			}
		}
	}

	@Test
	void mutateShouldKeepOriginalAndReturnNewIndividual() {
		int[] originalGenes = { 0, 1, 2, 3 };
		IndividualNQueens individual = createIndividual(originalGenes);

		int[] beforeMutation = individual.getGenes().clone();
		com.cefetmg.individual.interfaces.Individual<int[]> mutant = individual.mutate();

		assertNotSame(individual, mutant);
		assertArrayEquals(beforeMutation, individual.getGenes());
		assertEquals(beforeMutation.length, mutant.getGenes().length);

		for (int gene : mutant.getGenes()) {
			assertTrue(gene >= 0);
			assertTrue(gene < beforeMutation.length);
		}
	}
}
