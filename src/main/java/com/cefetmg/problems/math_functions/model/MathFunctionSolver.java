package com.cefetmg.problems.math_functions.model;

import com.cefetmg.core.GeneticAlgorithm;
import com.cefetmg.problems.math_functions.model.individuals.CrossoverType;
import com.cefetmg.problems.math_functions.model.individuals.IndividualMathFunction;
import com.cefetmg.problems.math_functions.model.individuals.IndividualMathFunctionFactory;
import com.cefetmg.problems.math_functions.model.individuals.MathFunction;

public class MathFunctionSolver {

        private final static boolean IS_MAXIMIZATION = false;

        public static MathFunctionStatistics solve(double mutationRate, double alpha, int numIndividuals,
                        int numEliteIndividuals, int maxGenerations, MathFunction function,
                        CrossoverType crossoverType) {

                IndividualMathFunctionFactory factory = new IndividualMathFunctionFactory(function, mutationRate, alpha,
                                crossoverType);

                GeneticAlgorithm<double[]> algorithm = new GeneticAlgorithm<>();

                IndividualMathFunction individual = (IndividualMathFunction) algorithm.execute(factory, numIndividuals,
                                numEliteIndividuals, maxGenerations, IS_MAXIMIZATION);

                return new MathFunctionStatistics(
                                individual,
                                numIndividuals,
                                numEliteIndividuals,
                                algorithm.getGenerationCount(),
                                maxGenerations,
                                function.getNumDimensions(),
                                mutationRate,
                                function.getName(),
                                crossoverType);
        }

}
