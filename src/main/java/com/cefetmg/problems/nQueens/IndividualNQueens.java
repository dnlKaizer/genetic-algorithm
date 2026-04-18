package com.cefetmg.problems.nQueens;

import java.util.List;

import com.cefetmg.core.interfaces.Individual;

public class IndividualNQueens extends Individual<int[]> {

    private Integer evaluation = null;

    private int[] genes;
    private int numGenes;

    private final GeneOperator GENE_OPERATOR;
    private final double MUTATION_RATE;
    private final int MAX_COLLISIONS;

    protected IndividualNQueens(int numGenes, double mutationRate, GeneOperator geneOperator) {
        this.numGenes = numGenes;
        this.genes = new int[numGenes];
        
        this.GENE_OPERATOR = geneOperator;
        this.MUTATION_RATE = mutationRate;
        this.MAX_COLLISIONS = numGenes * (numGenes - 1) / 2;

        for (int i = 0; i < numGenes; i++) {
            genes[i] = geneOperator.generateRandomGene(numGenes);
        }
    }

    private IndividualNQueens(int numGenes, int[] genes, double mutationRate, GeneOperator geneOperator) {
        this.numGenes = numGenes;
        this.genes = genes;
        
        this.MAX_COLLISIONS = numGenes * (numGenes - 1) / 2;
        this.MUTATION_RATE = mutationRate;
        this.GENE_OPERATOR = geneOperator;
    }

    @Override
    public List<Individual<int[]>> recombine(Individual<int[]> parent2) {
        int[][] childGenes = GENE_OPERATOR.crossoverGenes(this.genes, parent2.getGenes(), numGenes);

        return List.of(
            new IndividualNQueens(numGenes, childGenes[0], MUTATION_RATE, GENE_OPERATOR),
            new IndividualNQueens(numGenes, childGenes[1], MUTATION_RATE, GENE_OPERATOR)
        );
    }

    @Override
    public Individual<int[]> mutate() {
        int[] mutatedGenes = GENE_OPERATOR.mutateGenes(this.genes, numGenes, MUTATION_RATE);

        return new IndividualNQueens(numGenes, mutatedGenes, MUTATION_RATE, GENE_OPERATOR);
    }

    @Override
    public double getFitness() {
        if (evaluation == null) {
            evaluation = GENE_OPERATOR.countCollisions(this.genes, numGenes);
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