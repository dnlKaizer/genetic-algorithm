package com.cefetmg.individual.interfaces;

import java.util.List;

public abstract class Individual implements Comparable<Individual> {
    public abstract List<Individual> recombine(Individual other);

    public abstract Individual mutate();

    public abstract double getEvaluation();

    public abstract boolean isMaximization();

    public abstract int[] getGenes();

    @Override
    public int compareTo(Individual other) {
        if (this.isMaximization()) {
            return Double.compare(other.getEvaluation(), this.getEvaluation());
        } else {
            return Double.compare(this.getEvaluation(), other.getEvaluation());
        }
    }

    @Override
    public String toString() {
        StringBuilder genesString = new StringBuilder("{ ");
        for (int gene : getGenes()) {
            genesString.append(gene).append(" ");
        }
        genesString.append("}");

        return "Indivíduo: [ " + genesString.toString() + " ], Avaliação: " + getEvaluation();
    }
}