package com.cefetmg.individual.nQueens;

import java.util.List;

import com.cefetmg.individual.interfaces.Individual;
import com.cefetmg.individual.utils.GeneOperator;

public class IndividualNQueens implements Individual {

    private double mutationRate = 0.3;
    private int[] genes;
    private int numGenes;

    private final static boolean maximization = false;

    // genes = {2, 2, 0, 1};
    //     0   1   2   3
    // -----------------
    // 0 |   |   | x |   |
    // -----------------
    // 1 |   |   |   | x |
    // -----------------
    // 2 | x | x |   |   |
    // -----------------
    // 3 |   |   |   |   |
    // -----------------

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
        return GeneOperator.countCollisions(this.genes, numGenes);
    }

    @Override
    public boolean isMaximization() {
        return maximization;
    }

    @Override
    public int[] getGenes() {
        return genes;
    }

    @Override
    public String toString() {
        StringBuilder genesString = new StringBuilder("{ ");
        for (int gene : this.genes) {
            genesString.append(gene).append(" ");
        }
        genesString.append("}");

        return "Indivíduo: [ " + genesString.toString() + " ]";
    }

}