package com.cefetmg.problems.math_functions.model.functions;

import java.util.Objects;

import com.cefetmg.problems.math_functions.model.individuals.MathFunction;

public class LangermannFunction implements MathFunction {

    private final static double MIN_DOMAIN = 0.0;
    private final static double MAX_DOMAIN = 10.0;

    private final int m;
    private final double[] c;
    private final double[][] a;
    private final int d;

    public LangermannFunction(double[] c, double[][] a) {
        validate(c, a);

        this.m = c.length;
        this.c = c;
        this.a = a;
        this.d = a[0].length;
    }

    @Override
    public double execute(double[] x) {
        if (x.length != d) {
            throw new IllegalArgumentException("O vetor de entrada deve ter " + d + " dimensões.");
        }

        double totalSum = 0;

        for (int i = 0; i < m; i++) {
            double innerSum = 0;
            for (int j = 0; j < d; j++) {
                innerSum += Math.pow(x[j] - a[i][j], 2);
            }

            double exponent = - (1.0 / Math.PI) * innerSum;
            double cosineArgument = Math.PI * innerSum;

            totalSum += c[i] * Math.exp(exponent) * Math.cos(cosineArgument);
        }

        return totalSum;
    }

    @Override
    public boolean isOptimal(double fitness) {
        return false;
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

    private void validate(double[] c, double[][] a) {
        Objects.requireNonNull(c, "O vetor c não pode ser nulo.");
        Objects.requireNonNull(a, "A matriz A não pode ser nula.");

        int m = c.length;

        if (m <= 0) {
            throw new IllegalArgumentException("O número de colunas em c (valor de m) deve ser positivo.");
        }
        if (a.length != m) {
            throw new IllegalArgumentException("A matriz A deve ter o mesmo número de linhas que m.");
        }
        if (a[0].length == 0) {
            throw new IllegalArgumentException("O número de colunas em A (número de dimensões) deve ser maior que zero.");
        }
        for (int i = 1; i < a.length; i++) {
            if (a[i].length != a[0].length) {
                throw new IllegalArgumentException("Todas as linhas da matriz A devem ter o mesmo número de colunas.");
            }
        }
    }

}
