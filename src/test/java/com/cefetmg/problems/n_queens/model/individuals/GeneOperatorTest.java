package com.cefetmg.problems.n_queens.model.individuals;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class GeneOperatorTest {

	private final IntegerGeneOperator geneOperator = new IntegerGeneOperator();

	@Test
	void generateRandomGeneShouldStayWithinRange() {
		int numGenes = 20;

		for (int i = 0; i < 100; i++) {
			int[] gene = geneOperator.generateRandomGenes(numGenes);

			assertTrue(gene[0] >= 0);
			assertTrue(gene[0] < numGenes);
		}
	}

	@Test
	void mutateGeneWithZeroRateShouldKeepOriginalGene() {
		int numGenes = 8;
		int[] genes = new int[numGenes];
		for (int i = 0; i < numGenes; i++) {
			genes[i] = i;
		}
		int[] mutatedGenes = geneOperator.mutateGenes(genes, numGenes, 0.0);
		for (int i = 0; i < numGenes; i++) {
			assertEquals(genes[i], mutatedGenes[i]);
		}
	}

	@Test
	void mutateGenesWithZeroRateShouldKeepOriginalArray() {
		int[] genes = { 0, 1, 2, 3 };

		int[] mutatedGenes = geneOperator.mutateGenes(genes, 4, 0.0);

		assertArrayEquals(genes, mutatedGenes);
	}

	@Test
	void mutateGenesShouldKeepSizeAndValueRange() {
		int[] genes = { 0, 1, 2, 3 };
		int numGenes = 4;

		int[] mutatedGenes = geneOperator.mutateGenes(genes, numGenes, 1.0);

		assertEquals(numGenes, mutatedGenes.length);
		for (int gene : mutatedGenes) {
			assertTrue(gene >= 0);
			assertTrue(gene < numGenes);
		}
	}

	@Test
	void crossoverGenesShouldReturnTwoValidChildren() {
		int[] parent1Genes = { 0, 0, 0, 0 };
		int[] parent2Genes = { 1, 1, 1, 1 };

		int[][] children = geneOperator.crossoverGenes(parent1Genes, parent2Genes, 4);

		assertEquals(2, children.length);
		assertEquals(4, children[0].length);
		assertEquals(4, children[1].length);

		for (int i = 0; i < 4; i++) {
			assertTrue(children[0][i] == 0 || children[0][i] == 1);
			assertTrue(children[1][i] == 0 || children[1][i] == 1);
			assertTrue(children[0][i] != children[1][i]);
		}
	}

	@Test
	void evaluateShouldDetectSameRowAndDiagonalConflicts() {
		assertEquals(6, geneOperator.evaluate(new int[] { 0, 0, 0, 0 }, 4));
		assertEquals(6, geneOperator.evaluate(new int[] { 0, 1, 2, 3 }, 4));
		assertEquals(0, geneOperator.evaluate(new int[] { 1, 3, 0, 2 }, 4));
	}
}
