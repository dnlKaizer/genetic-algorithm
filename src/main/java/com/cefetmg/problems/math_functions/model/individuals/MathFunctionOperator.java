package com.cefetmg.problems.math_functions.model.individuals;

import java.util.Random;

import com.cefetmg.core.RandomSingleton;

public class MathFunctionOperator {

    private final Random random;

    private final MathFunction function;
    private final double mutationRate;
    private final int numGenes;
    private final double alpha;
    private final CrossoverType crossoverType;

    public MathFunctionOperator(MathFunction function, double mutationRate, double alpha, CrossoverType crossoverType) {
        this.random = RandomSingleton.getInstance();
        this.function = function;
        this.mutationRate = mutationRate;
        this.numGenes = function.getNumDimensions();
        this.alpha = alpha;
        this.crossoverType = crossoverType;
    }

    public double[][] crossoverGenes(double[] parent1, double[] parent2) {
        double[][] children = new double[2][numGenes];

        for (int j = 0; j < numGenes; j++) {
            crossover(children, parent1, parent2, j);
        }

        return children;
    }

    private void crossover(double[][] children, double[] parent1, double[] parent2, int j) {
        switch (crossoverType) {
            case ARITHMETIC -> {
                children[0][j] = alpha * parent1[j] + (1 - alpha) * parent2[j];
                children[1][j] = alpha * parent2[j] + (1 - alpha) * parent1[j];
            }
            case BLX_ALPHA -> {
                double d = Math.abs(parent1[j] - parent2[j]);

                double minInterval = Math.min(parent1[j], parent2[j]) - alpha * d;
                double maxInterval = Math.max(parent1[j], parent2[j]) + alpha * d;

                children[0][j] = clamp(minInterval + (maxInterval - minInterval) * random.nextDouble());
                children[1][j] = clamp(minInterval + (maxInterval - minInterval) * random.nextDouble());
            }
        }
    }

    public double evaluate(double[] genes) {
        return function.execute(genes);
    }

    public double[] generateRandomGenes() {
        double[] genes = new double[numGenes];
        for (int i = 0; i < numGenes; i++) {
            genes[i] = random.nextDouble(function.getMinDomain(), function.getMaxDomain());
        }
        return genes;
    }

    public double[] mutateGenes(double[] genes) {
        if (random.nextDouble() >= mutationRate) {
            return genes;
        }

        double[] mutatedGenes = new double[numGenes];
        for (int i = 0; i < numGenes; i++) {
            double mutation = random.nextGaussian() * alpha;
            mutatedGenes[i] = clamp(genes[i] + mutation);
        }
        return mutatedGenes;
    }

    public boolean isOptimal(double fitness) {
        return function.isOptimal(fitness);
    }

    private double clamp(double value) {
        return Math.max(function.getMinDomain(), Math.min(function.getMaxDomain(), value));
    }

}
