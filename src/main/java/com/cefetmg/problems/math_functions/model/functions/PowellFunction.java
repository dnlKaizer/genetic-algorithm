package com.cefetmg.problems.math_functions.model.functions;

import com.cefetmg.problems.math_functions.model.individuals.MathFunction;

public class PowellFunction implements MathFunction {

    private final static double MIN_DOMAIN = -4.0;
    private final static double MAX_DOMAIN = 5.0;

    private final int d;

    public PowellFunction(int d) {
        if (d <= 0) {
            throw new IllegalArgumentException("O número de dimensões deve ser maior que zero.");
        }
        if (d % 4 != 0) {
            throw new IllegalArgumentException("O número de dimensões deve ser um múltiplo de 4.");
        }

        this.d = d;
    }

    @Override
    public double execute(double[] x) {
        if (x.length != d) {
            throw new IllegalArgumentException("O vetor de entrada deve ter " + d + " dimensões.");
        }

        double totalSum = 0;

        for (int i = 0; i < x.length; i += 4) {
        double term1 = x[i] + (10 * x[i + 1]);
        double term2 = x[i + 2] + x[i + 3];
        double term3 = x[i + 1] - (2 * x[i + 2]);
        double term4 = x[i] - x[i + 3];

        totalSum += Math.pow(term1, 2) + 
                    (5 * Math.pow(term2, 2)) +
                    Math.pow(term3, 4) + 
                    (10 * Math.pow(term4, 4));
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
