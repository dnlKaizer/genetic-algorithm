package com.cefetmg.problems.math_functions.model.individuals;

public interface MathFunction {
    double execute(double[] x);

    boolean isOptimal(double fitness);

    double getMinDomain();
    double getMaxDomain();

    int getNumDimensions();
}
