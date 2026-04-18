package com.cefetmg;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import com.cefetmg.individual.interfaces.Individual;
import com.cefetmg.individual.interfaces.IndividualFactory;

class GeneticAlgorithmTest {

    @Test
    void executeWithZeroGenerationsShouldReturnBestIndividualFromInitialPopulation() {
        List<Individual<Integer>> initialPopulation = List.of(
            new DummyIndividual(10),
            new DummyIndividual(3),
            new DummyIndividual(7)
        );

        GeneticAlgorithm<Integer> algorithm = new GeneticAlgorithm<>();
        Individual<Integer> best = algorithm.execute(
            new DummyFactory(initialPopulation),
            initialPopulation.size(),
            1,
            0
        );

        assertEquals(3.0, best.getFitness());
    }

    @Test
    void executeShouldReturnExactBestReferenceWhenGenerationCountIsZero() {
        DummyIndividual bestReference = new DummyIndividual(1);
        List<Individual<Integer>> initialPopulation = List.of(
            new DummyIndividual(5),
            bestReference,
            new DummyIndividual(8)
        );

        GeneticAlgorithm<Integer> algorithm = new GeneticAlgorithm<>();
        Individual<Integer> best = algorithm.execute(
            new DummyFactory(initialPopulation),
            initialPopulation.size(),
            1,
            0
        );

        assertSame(bestReference, best);
    }

    private static final class DummyFactory extends IndividualFactory<Integer> {
        private final List<Individual<Integer>> seedPopulation;

        private DummyFactory(List<Individual<Integer>> seedPopulation) {
            this.seedPopulation = seedPopulation;
        }

        @Override
        public Individual<Integer> getInstance() {
            throw new UnsupportedOperationException("Not used in this test.");
        }

        @Override
        public List<Individual<Integer>> getInstances(int num) {
            return new ArrayList<>(seedPopulation);
        }
    }

    private static final class DummyIndividual extends Individual<Integer> {
        private final int value;

        private DummyIndividual(int value) {
            this.value = value;
        }

        @Override
        public List<Individual<Integer>> recombine(Individual<Integer> other) {
            return List.of(new DummyIndividual(this.value), new DummyIndividual(other.getGenes()));
        }

        @Override
        public Individual<Integer> mutate() {
            return new DummyIndividual(value);
        }

        @Override
        public double getFitness() {
            return value;
        }

        @Override
        public double getSelectionFitness() {
            return 1.0 / (1.0 + value);
        }

        @Override
        public Integer getGenes() {
            return value;
        }
    }
}
