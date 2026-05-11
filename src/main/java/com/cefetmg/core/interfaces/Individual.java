package com.cefetmg.core.interfaces;

import java.util.List;

public abstract class Individual<T> implements Comparable<Individual<T>> {
    public abstract List<Individual<T>> recombine(Individual<T> other);

    public abstract Individual<T> mutate();

    public abstract double getFitness();

    // public abstract double getSelectionFitness();

    public abstract boolean isOptimal();

    public abstract T getGenes();

    @Override
    public int compareTo(Individual<T> other) {
        return Double.compare(this.getFitness(), other.getFitness());
    }

}