package com.cefetmg.problems.nQueens.model.individuals;

import java.util.List;

import com.cefetmg.core.interfaces.Individual;

public class IndividualNQueens extends Individual<int[]> {

    private Double evaluation = null;

    private final int[] genes;
    private final int numGenes;

    private final IntegerGeneOperator GENE_OPERATOR;
    private final double MUTATION_RATE;
    private final int MAX_COLLISIONS;

    protected IndividualNQueens(int numGenes, double mutationRate, IntegerGeneOperator geneOperator) {
        this.numGenes = numGenes;
        
        this.GENE_OPERATOR = geneOperator;
        this.MUTATION_RATE = mutationRate;
        this.MAX_COLLISIONS = numGenes * (numGenes - 1) / 2;
        
        this.genes = GENE_OPERATOR.generateRandomGenes(numGenes);
    }

    private IndividualNQueens(int numGenes, int[] genes, double mutationRate, IntegerGeneOperator geneOperator) {
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
            evaluation = GENE_OPERATOR.evaluate(this.genes, numGenes);
        }
        return evaluation;
    }

    @Override
    public double getSelectionFitness() {
        return (MAX_COLLISIONS + 1) - getFitness();
    }

    @Override
    public boolean isOptimal() {
        return getFitness() == 0;
    }

    @Override
    public int[] getGenes() {
        return genes;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("Indivíduo: { ");
        for (int gene : genes) {
            sb.append(gene).append(" ");
        }
        sb.append("}, Avaliação: ").append(getFitness());
        return sb.toString();
    }

}