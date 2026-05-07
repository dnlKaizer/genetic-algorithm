package com.cefetmg.problems.nQueens.model;

import com.cefetmg.core.interfaces.Individual;

public record NQueensStatistics(
    Individual<int[]> bestIndividual,
    int generationCount,
    int numIndividuals,
    int numEliteIndividuals,
    int maxGenerations,
    int numQueens,
    double mutationRate
) {}
