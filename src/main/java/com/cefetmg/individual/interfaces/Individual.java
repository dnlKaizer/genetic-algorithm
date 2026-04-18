package com.cefetmg.individual.interfaces;

import java.util.List;

public abstract class Individual implements Comparable<Individual> {
    public abstract List<Individual> recombine(Individual other);

    public abstract Individual mutate();

    public abstract double getFitness();

    public abstract double getSelectionFitness();

    public abstract int[] getGenes();

    @Override
    public int compareTo(Individual other) {
        return Double.compare(this.getFitness(), other.getFitness());
    }

    @Override
    public String toString() {
        StringBuilder genesString = new StringBuilder("{ ");
        for (int gene : getGenes()) {
            genesString.append(gene).append(" ");
        }
        genesString.append("}");

        return "Indivíduo: [ " + genesString.toString() + " ], Avaliação: " + getFitness();
    }
}