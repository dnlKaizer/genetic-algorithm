package com.cefetmg.problems.nQueens.model.individuals;

import java.util.Random;

import com.cefetmg.core.RandomSingleton;
import com.cefetmg.core.interfaces.GeneOperator;

public class IntegerGeneOperator implements GeneOperator<int[]> {

    private final Random random;

    public IntegerGeneOperator() {
        this.random = RandomSingleton.getInstance();
    }

    public int[] generateRandomGenes(int numGenes) {
        int[] genes = new int[numGenes];
        for (int i = 0; i < numGenes; i++) {
            genes[i] = random.nextInt(numGenes);
        }
        return genes;
    }

    private int generateRandomGene(int numGenes) {
        return random.nextInt(numGenes);
    }

    private int generateCrossoverPoint(int numGenes) {
        return random.nextInt(1, numGenes - 1);
    }

    private int mutateGene(int gene, int numGenes, double mutationRate) {
        if (random.nextDouble() < mutationRate) {
            return generateRandomGene(numGenes);
        }
        return gene;
    }

    public int[] mutateGenes(int[] genes, int numGenes, double mutationRate) {
        int[] mutatedGenes = new int[numGenes];
        for (int i = 0; i < numGenes; i++) {
            mutatedGenes[i] = mutateGene(genes[i], numGenes, mutationRate);
        }
        return mutatedGenes;
    }

    public int[][] crossoverGenes(int[] parent1Genes, int[] parent2Genes, int numGenes) {
        int crossoverPoint = generateCrossoverPoint(numGenes);
        int[][] childGenes = new int[2][numGenes];

        for (int i = 0; i < numGenes; i++) {
            if (i < crossoverPoint) {
                childGenes[0][i] = parent1Genes[i];
                childGenes[1][i] = parent2Genes[i];
            } else {
                childGenes[0][i] = parent2Genes[i];
                childGenes[1][i] = parent1Genes[i];
            }
        }

        return childGenes;
    }

    // private int countCollisions(int[] genes, int numGenes) {
    //     int collisions = 0;
    //     for (int i = 0; i < numGenes - 1; i++) {
    //         for (int j = i + 1; j < numGenes; j++) {
    //             boolean sameColumn = genes[i] == genes[j];
    //             boolean sameDiagonal = Math.abs(genes[i] - genes[j]) == Math.abs(i - j);

    //             if (sameColumn || sameDiagonal) {
    //                 collisions++;
    //             }
    //         }
    //     }
    //     return collisions;
    // }

    private int countCollisions(int[] genes, int numGenes) {
        int collisions = 0;

        // Arrays para contar quantas rainhas ocupam a mesma linha ou diagonal
        int[] rowCount = new int[numGenes];
        int[] diag1Count = new int[2 * numGenes]; // Para (genes[i] - i)
        int[] diag2Count = new int[2 * numGenes]; // Para (genes[i] + i)

        for (int i = 0; i < numGenes; i++) {
            int row = genes[i];
            int d1 = row - i + (numGenes - 1); // Offset para não ter índice negativo
            int d2 = row + i;

            // Se já existe alguém nessa linha/diagonal, cada rainha anterior
            // forma uma nova colisão com a rainha atual.
            collisions += rowCount[row];
            collisions += diag1Count[d1];
            collisions += diag2Count[d2];

            // Incrementa os contadores
            rowCount[row]++;
            diag1Count[d1]++;
            diag2Count[d2]++;
        }

        return collisions;
    }

    public double evaluate(int[] genes, int numGenes) {
        return countCollisions(genes, numGenes);
    }

}
