package com.cefetmg.individual.nQueens;

import com.cefetmg.individual.interfaces.IndividualFactory;

public class IndividualNQueensFactory implements IndividualFactory {

    private int numGenes;

    public IndividualNQueensFactory(int numGenes) {
        if (numGenes < 4) throw new IllegalArgumentException("O número de genes deve ser maior ou igual a 4.");
        
        this.numGenes = numGenes;
    }

    @Override
    public IndividualNQueens getInstance() {
        return new IndividualNQueens(numGenes);
    }

}
