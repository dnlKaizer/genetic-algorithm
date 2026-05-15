package com.cefetmg.problems.math_functions.model.individuals;

import java.util.List;

import com.cefetmg.core.interfaces.Individual;

public class IndividualMathFunction extends Individual<double[]> {

    private Double evaluation = null;

    private final double[] genes;

    private final MathFunctionOperator operator;

    protected IndividualMathFunction(MathFunctionOperator operator) {
        this(operator.generateRandomGenes(), operator);
    }

    private IndividualMathFunction(double[] genes, MathFunctionOperator operator) {
        this.genes = genes;
        this.operator = operator;
    }

    @Override
    public double getFitness() {
        if (evaluation == null) {
            evaluation = operator.evaluate(this.genes);
        }
        return evaluation;
    }

    @Override
    public double[] getGenes() {
        return this.genes;
    }

    @Override
    public boolean isOptimal() {
        return operator.isOptimal(getFitness());
    }

    @Override
    public Individual<double[]> mutate() {
        double[] mutatedGenes = operator.mutateGenes(this.genes);

        return new IndividualMathFunction(mutatedGenes, operator);
    }

    @Override
    public List<Individual<double[]>> recombine(Individual<double[]> other) {
        double[][] childGenes = operator.crossoverGenes(this.genes, other.getGenes());

        return List.of(
            new IndividualMathFunction(childGenes[0], operator),
            new IndividualMathFunction(childGenes[1], operator)
        );
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("Indivíduo: { ");
        for (double gene : genes) {
            sb.append(gene).append(" ");
        }
        sb.append("}, Avaliação: ").append(getFitness());
        return sb.toString();
    }
    
}
