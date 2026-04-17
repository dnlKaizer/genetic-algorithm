package com.cefetmg.individual.nQueens;

import java.util.List;

import com.cefetmg.individual.interfaces.Individual;
import com.cefetmg.individual.utils.GeneOperator;

public class IndividualNQueens extends Individual {

    private Integer evaluation = null;

    private double mutationRate = 0.3;
    private int[] genes;
    private int numGenes;

    protected IndividualNQueens(int numGenes) {
        this.numGenes = numGenes;
        this.genes = new int[numGenes];

        for (int i = 0; i < numGenes; i++) {
            genes[i] = GeneOperator.generateRandomGene(numGenes);
        }
    }

    private IndividualNQueens(int numGenes, int[] genes) {
        this.numGenes = numGenes;
        this.genes = genes;
    }

    @Override
    public List<Individual> recombine(Individual parent2) {
        int[][] childGenes = GeneOperator.crossoverGenes(this.genes, parent2.getGenes(), numGenes);

        return List.of(
            new IndividualNQueens(numGenes, childGenes[0]),
            new IndividualNQueens(numGenes, childGenes[1])
        );
    }

    @Override
    public Individual mutate() {
        int[] mutatedGenes = GeneOperator.mutateGenes(this.genes, numGenes, mutationRate);

        return new IndividualNQueens(numGenes, mutatedGenes);
    }

    @Override
    public double getEvaluation() {
        if (evaluation == null) {
            evaluation = GeneOperator.countCollisions(this.genes, numGenes);
        }
        return evaluation;
    }

    @Override
    public boolean isMaximization() {
        return false;
    }

    @Override
    public int[] getGenes() {
        return genes;
    }

}