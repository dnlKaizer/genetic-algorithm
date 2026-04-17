package com.cefetmg.individual.utils;

import java.util.Random;

public class GeneOperator {
    private final static Random random = new Random();

    public static int generateRandomGene(int numGenes) {
        return random.nextInt(numGenes);
    }

    public static int generateCrossoverPoint(int numGenes) {
        return random.nextInt(1, numGenes - 1);
    }

    public static int mutateGene(int gene, int numGenes, double mutationRate) {
        if (random.nextDouble() < mutationRate) {
            return generateRandomGene(numGenes);
        }
        return gene;
    }

    public static int[] mutateGenes(int[] genes, int numGenes, double mutationRate) {
        int[] mutatedGenes = new int[numGenes];
        for (int i = 0; i < numGenes; i++) {
            mutatedGenes[i] = mutateGene(genes[i], numGenes, mutationRate);
        }
        return mutatedGenes;
    }

    public static int[][] crossoverGenes(int[] parent1Genes, int[] parent2Genes, int numGenes) {
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

    public static int countCollisions(int[] genes, int numGenes) {
        int collisions = 0;
        for (int i = 0; i < numGenes - 1; i++) {
            for (int j = i + 1; j < numGenes; j++) {
                boolean sameColumn = genes[i] == genes[j];
                boolean sameDiagonal = Math.abs(genes[i] - genes[j]) == Math.abs(i - j);

                if (sameColumn || sameDiagonal) {
                    collisions++;
                }
            }
        }
        return collisions;
    }

}
