package com.cefetmg.core.interfaces;

public interface GeneOperator<T> {

    T generateRandomGenes(int numGenes);

    T mutateGenes(T genes, int numGenes, double mutationRate);

    T[] crossoverGenes(T parent1Genes, T parent2Genes, int numGenes);

    double evaluate(T genes, int numGenes);

}
