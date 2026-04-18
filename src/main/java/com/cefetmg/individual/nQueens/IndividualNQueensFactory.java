package com.cefetmg.individual.nQueens;

import com.cefetmg.individual.interfaces.IndividualFactory;
import com.cefetmg.individual.utils.GeneOperator;
import com.cefetmg.metadata.variables.VarsIndividualNQueens;

public class IndividualNQueensFactory extends IndividualFactory<int[]> {

    private int numGenes;
    
    private final GeneOperator geneOperator;
    private final double mutationRate;

    public IndividualNQueensFactory(int numGenes) {
        int minNumGenes = 4;

        if (numGenes < minNumGenes) {
            throw new IllegalArgumentException("O número de genes deve ser maior ou igual a " + minNumGenes + ".");
        }

        this.numGenes = numGenes;
        
        this.geneOperator = new GeneOperator();
        this.mutationRate = VarsIndividualNQueens.getMutationRate();
    }

    @Override
    public IndividualNQueens getInstance() {
        return new IndividualNQueens(numGenes, mutationRate, geneOperator);
    }

}
