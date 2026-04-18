package com.cefetmg.core;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.Random;

import com.cefetmg.core.interfaces.Individual;
import com.cefetmg.core.interfaces.IndividualFactory;

public class GeneticAlgorithm<T> {

    private final Random random;

    private int generationCount = 0;

    public GeneticAlgorithm() {
        random = RandomSingleton.getInstance();
    }

    public int getGenerationCount() {
        return generationCount;
    }

    public Individual<T> execute(IndividualFactory<T> factory, int numIndividuals, int numEliteIndividuals, int maxGenerations) {
        List<Individual<T>> population = factory.getInstances(numIndividuals);
        generationCount = 0;

        for (int i = 0; i < maxGenerations; i++) {
            generationCount++;
            List<Individual<T>> allIndividuals = new ArrayList<>(numIndividuals * 3);
            allIndividuals.addAll(population);
            allIndividuals.addAll(applyMutation(population));
            allIndividuals.addAll(applyRecombination(population));

            Optional<Individual<T>> optimalIndividual = allIndividuals.stream().filter(Individual::isOptimal).findFirst();

            if (optimalIndividual.isPresent()) {
                return optimalIndividual.get();
            }

            population.clear();
            population.addAll(applyElitism(allIndividuals, numEliteIndividuals));
            population.addAll(applyRouletteWheelSelection(allIndividuals, numIndividuals - numEliteIndividuals));
        }

        Collections.sort(population);

        return population.get(0);
    }

    private List<Individual<T>> applyMutation(List<Individual<T>> population) {
        return population.stream()
            .map(Individual::mutate)
            .toList();
    }

    private List<Individual<T>> applyRecombination(List<Individual<T>> population) {
        List<Individual<T>> recombined = new ArrayList<>();
        List<Individual<T>> populationCopy = new ArrayList<>(population);

        while (populationCopy.size() > 1) {
            Individual<T> parent1 = populationCopy.remove(random.nextInt(populationCopy.size()));
            Individual<T> parent2 = populationCopy.remove(random.nextInt(populationCopy.size()));

            recombined.addAll(parent1.recombine(parent2));
        }

        if (!populationCopy.isEmpty()) {
            recombined.add(populationCopy.get(0));
        }

        return recombined;
    }

    private List<Individual<T>> applyElitism(List<Individual<T>> population, int numEliteIndividuals) {
        List<Individual<T>> populationSorted = new ArrayList<>(population);
        Collections.sort(populationSorted);

        return populationSorted.subList(0, numEliteIndividuals);
    }

    private List<Individual<T>> applyRouletteWheelSelection(List<Individual<T>> population, int numSelected) {
        double[] cumulativeScores = new double[population.size()];
        double sum = 0;

        for (int i = 0; i < population.size(); i++) {
            sum += population.get(i).getSelectionFitness();
            cumulativeScores[i] = sum;
        }

        List<Individual<T>> selected = new ArrayList<>(numSelected);

        for (int i = 0; i < numSelected; i++) {
            double target = random.nextDouble() * sum;

            int index = Arrays.binarySearch(cumulativeScores, target);
            if (index < 0) index = -(index + 1);
            if (index >= population.size()) index = population.size() - 1;

            selected.add(population.get(index));
        }

        return selected;
    }

}
