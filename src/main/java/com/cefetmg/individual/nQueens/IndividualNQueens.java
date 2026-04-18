package com.cefetmg.individual.nQueens;

import java.util.List;

import com.cefetmg.individual.interfaces.Individual;
import com.cefetmg.individual.utils.GeneOperator;

public class IndividualNQueens extends Individual<int[]> {

    private Integer evaluation = null;

    private double mutationRate = 0.3;
    private int[] genes;
    private int numGenes;

    private final int MAX_COLLISIONS;

    protected IndividualNQueens(int numGenes) {
        this.numGenes = numGenes;
        this.genes = new int[numGenes];
        this.MAX_COLLISIONS = numGenes * (numGenes - 1) / 2;

        for (int i = 0; i < numGenes; i++) {
            genes[i] = GeneOperator.generateRandomGene(numGenes);
        }
    }

    private IndividualNQueens(int numGenes, int[] genes) {
        this.numGenes = numGenes;
        this.genes = genes;
        this.MAX_COLLISIONS = numGenes * (numGenes - 1) / 2;
    }

    @Override
    public List<Individual<int[]>> recombine(Individual<int[]> parent2) {
        int[][] childGenes = GeneOperator.crossoverGenes(this.genes, parent2.getGenes(), numGenes);

        return List.of(
            new IndividualNQueens(numGenes, childGenes[0]),
            new IndividualNQueens(numGenes, childGenes[1])
        );
    }

    @Override
    public Individual<int[]> mutate() {
        int[] mutatedGenes = GeneOperator.mutateGenes(this.genes, numGenes, mutationRate);

        return new IndividualNQueens(numGenes, mutatedGenes);
    }

    @Override
    public double getFitness() {
        if (evaluation == null) {
            evaluation = GeneOperator.countCollisions(this.genes, numGenes);
        }
        return evaluation;
    }

    @Override
    public double getSelectionFitness() {
        return (MAX_COLLISIONS + 1) - getFitness();
    }

    @Override
    public int[] getGenes() {
        return genes;
    }

    @Override
    public String toString() {
        StringBuilder genesString = new StringBuilder("{ ");
        for (int gene : genes) {
            genesString.append(gene).append(" ");
        }
        genesString.append("}");

        return "Indivíduo: [ " + genesString.toString() + " ], Avaliação: " + getFitness();
    }

}