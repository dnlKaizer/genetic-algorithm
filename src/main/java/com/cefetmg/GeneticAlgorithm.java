package com.cefetmg;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import com.cefetmg.individual.interfaces.Individual;
import com.cefetmg.individual.interfaces.IndividualFactory;

public class GeneticAlgorithm {

    private final static Random random = new Random();
    
    public static Individual execute(IndividualFactory factory, int numIndividuals, int numEliteIndividuals, int numGenerations) {
        List<Individual> population = factory.getInstances(numIndividuals);

        for (int i = 0; i < numGenerations; i++) {
            List<Individual> allIndividuals = new ArrayList<>(numIndividuals * 3);
            allIndividuals.addAll(population);
            allIndividuals.addAll(applyMutation(population));
            allIndividuals.addAll(applyRecombination(population));

            population.clear();
            population.addAll(applyElitism(allIndividuals, numEliteIndividuals));
            population.addAll(applyBiasedSelection(allIndividuals, numIndividuals - numEliteIndividuals));
        }

        Collections.sort(population);
        
        return population.get(0);
    }

    private static List<Individual> applyMutation(List<Individual> population) {
        return population.stream()
            .map(Individual::mutate)
            .toList();
    }

    private static List<Individual> applyRecombination(List<Individual> population) {
        List<Individual> recombined = new ArrayList<>();
        List<Individual> populationCopy = new ArrayList<>(population);

        while (populationCopy.size() > 1) {
            Individual parent1 = populationCopy.remove(random.nextInt(populationCopy.size()));
            Individual parent2 = populationCopy.remove(random.nextInt(populationCopy.size()));

            recombined.addAll(parent1.recombine(parent2));
        }

        if (!populationCopy.isEmpty()) {
            recombined.add(populationCopy.get(0));
        }

        return recombined;
    }

    private static List<Individual> applyElitism(List<Individual> population, int numEliteIndividuals) {
        List<Individual> populationSorted = new ArrayList<>(population);
        Collections.sort(populationSorted);

        return populationSorted.subList(0, numEliteIndividuals);
    }

    private static List<Individual> applyBiasedSelection(List<Individual> population, int numSelected) {
        List<Individual> selected = new ArrayList<>(numSelected);

        double totalEvaluation = population.stream()
            .mapToDouble(individual -> (1 / individual.getEvaluation()))
            .sum();

        for (int i = 0; i < numSelected; i++) {
            double nextDouble = random.nextDouble() * totalEvaluation;
            double cumulativeEvaluation = 0;

            for (Individual individual : population) {
                cumulativeEvaluation += (1 / individual.getEvaluation());
                if (cumulativeEvaluation > nextDouble) {
                    selected.add(individual);
                    break;
                }
            }
        }
        return selected;
    }

}
