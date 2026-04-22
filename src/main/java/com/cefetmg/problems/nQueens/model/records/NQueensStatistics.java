package com.cefetmg.problems.nQueens.model.records;

import com.cefetmg.core.interfaces.Individual;

public record NQueensStatistics(
    Individual<int[]> bestIndividual,
    int generationCount,
    NQueensVariables variables
) {}
