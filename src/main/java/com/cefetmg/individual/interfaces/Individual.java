package com.cefetmg.individual.interfaces;

import java.util.List;

public interface Individual {
    public List<Individual> recombine(Individual other);

    public Individual mutate();

    public double getEvaluation();

    public boolean isMaximization();

    public int[] getGenes();
}