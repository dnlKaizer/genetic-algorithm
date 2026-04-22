package com.cefetmg.problems.nQueens.model.individuals;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class GeneOperatorTest {

	private final GeneOperator geneOperator = new GeneOperator();

	@Test
	void generateRandomGeneShouldStayWithinRange() {
		int numGenes = 8;

		for (int i = 0; i < 100; i++) {
			int gene = geneOperator.generateRandomGene(numGenes);

			assertTrue(gene >= 0);
			assertTrue(gene < numGenes);
		}
	}

	@Test
	void generateCrossoverPointShouldStayInsideBounds() {
		int numGenes = 6;

		for (int i = 0; i < 100; i++) {
			int point = geneOperator.generateCrossoverPoint(numGenes);

			assertTrue(point >= 1);
			assertTrue(point < numGenes - 1);
		}
	}

	@Test
	void mutateGeneWithZeroRateShouldKeepOriginalGene() {
		assertEquals(3, geneOperator.mutateGene(3, 8, 0.0));
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
	void countCollisionsShouldDetectSameRowAndDiagonalConflicts() {
		assertEquals(6, geneOperator.countCollisions(new int[] { 0, 0, 0, 0 }, 4));
		assertEquals(6, geneOperator.countCollisions(new int[] { 0, 1, 2, 3 }, 4));
		assertEquals(0, geneOperator.countCollisions(new int[] { 1, 3, 0, 2 }, 4));
	}
}
