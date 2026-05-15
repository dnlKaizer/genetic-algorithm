package com.cefetmg.problems.math_functions.model.individuals;

import java.util.Objects;

import com.cefetmg.core.interfaces.Individual;
import com.cefetmg.core.interfaces.IndividualFactory;

public class IndividualMathFunctionFactory extends IndividualFactory<double[]> {

    private final MathFunctionOperator operator;

    public IndividualMathFunctionFactory(MathFunction function, double mutationRate, double alpha,
            CrossoverType crossoverType) {
        validate(function, mutationRate, alpha);

        this.operator = new MathFunctionOperator(function, mutationRate, alpha, crossoverType);
    }

    private void validate(MathFunction function, double mutationRate, double alpha) {
        Objects.requireNonNull(function, "A função de avaliação não pode ser nula.");

        if (mutationRate < 0 || mutationRate > 1) {
            throw new IllegalArgumentException("A taxa de mutação deve estar entre 0 e 1.");
        }
        if (alpha < 0 || alpha > 1) {
            throw new IllegalArgumentException("O parâmetro alpha deve estar entre 0 e 1.");
        }
    }

    @Override
    public Individual<double[]> getInstance() {
        return new IndividualMathFunction(operator);
    }

}
