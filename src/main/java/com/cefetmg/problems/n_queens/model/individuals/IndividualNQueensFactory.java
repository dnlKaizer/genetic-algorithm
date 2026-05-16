package com.cefetmg.problems.n_queens.model.individuals;

import com.cefetmg.core.interfaces.IndividualFactory;

public class IndividualNQueensFactory extends IndividualFactory<int[]> {

    private final IntegerGeneOperator geneOperator;

    private final int numGenes;
    private final double mutationRate;

    public IndividualNQueensFactory(int numGenes, double mutationRate) {
        int minNumGenes = 4;

        if (numGenes < minNumGenes) {
            throw new IllegalArgumentException("O número de genes deve ser maior ou igual a " + minNumGenes + ".");
        }

        this.numGenes = numGenes;
        this.mutationRate = mutationRate;
        
        this.geneOperator = new IntegerGeneOperator();
    }

    @Override
    public IndividualNQueens getInstance() {
        return new IndividualNQueens(numGenes, mutationRate, geneOperator);
    }

}
