package com.cefetmg.problems.math_functions.model.functions;

import com.cefetmg.problems.math_functions.model.individuals.MathFunction;

public class DixonPriceFunction implements MathFunction {

    private final static double MIN_DOMAIN = -10.0;
    private final static double MAX_DOMAIN = 10.0;
    
    private final int d;

    public DixonPriceFunction(int d) {
        if (d <= 0) {
            throw new IllegalArgumentException("O número de dimensões deve ser maior que zero.");
        }

        this.d = d;
    }

    @Override
    public double execute(double[] x) {
        if (x.length != d) {
            throw new IllegalArgumentException("O vetor de entrada deve ter " + d + " dimensões.");
        }

        double totalSum = Math.pow((x[0] - 1), 2);

        for (int i = 1; i < d; i++) {
            double term = 2 * Math.pow(x[i], 2) - x[i - 1];
            totalSum += (i + 1) * Math.pow(term, 2);
        }

        return totalSum;
    }

    @Override
    public boolean isOptimal(double fitness) {
        return fitness < FunctionConstants.epsilon;
    }

    @Override
    public double getMaxDomain() {
        return MAX_DOMAIN;
    }

    @Override
    public double getMinDomain() {
        return MIN_DOMAIN;
    }

    @Override
    public int getNumDimensions() {
        return d;
    }

}
