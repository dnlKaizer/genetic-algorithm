package com.cefetmg.problems.math_functions.model;

import com.cefetmg.problems.math_functions.model.individuals.CrossoverType;
import com.cefetmg.problems.math_functions.model.individuals.IndividualMathFunction;

public record MathFunctionStatistics(
    IndividualMathFunction bestIndividual,
    int numIndividuals,
    int numEliteIndividuals,
    int numGenerations,
    int maxGenerations,
    int numDimensions,
    double mutationRate,
    String functionName,
    CrossoverType crossoverType
) {}
