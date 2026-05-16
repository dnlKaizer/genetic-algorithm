package com.cefetmg.problems.n_queens.view;

import com.cefetmg.core.interfaces.Individual;
import com.cefetmg.problems.n_queens.model.NQueensStatistics;

public class NQueensView {

    private NQueensView() {}

    public static boolean shouldContinue() {
        System.out.print("\nExecutar (qualquer caracter cancela): ");
        String input = System.console().readLine();
        return input.equalsIgnoreCase("");
    }

    public static void display(NQueensStatistics statistics) {
        String br = System.lineSeparator();
        StringBuilder sb = new StringBuilder(br);

        addHeader(sb, br);
        addConfig(sb, br, statistics);
        addStatistics(sb, br, statistics.bestIndividual(), statistics.generationCount());

        System.out.print(sb.toString());
    }

    private static void addHeader(StringBuilder sb, String br) {
        addLine(sb, br);
        sb.append("\t\tAlgoritmo Genético - Problema das N Rainhas").append(br);
        addLine(sb, br);
    }

    private static void addConfig(StringBuilder sb, String br, NQueensStatistics statistics) {
        sb.append("Configurações de Execução:").append(br);
        sb.append("\tNúmero de Rainhas: ").append(statistics.numQueens()).append(br);
        sb.append("\tTaxa de Mutação: ").append(statistics.mutationRate()).append(br);
        sb.append("\tNúmero de Indivíduos: ").append(statistics.numIndividuals()).append(br);
        sb.append("\tNúmero de Elites: ").append(statistics.numEliteIndividuals()).append(br);
        sb.append("\tMáximo de Gerações: ").append(statistics.maxGenerations()).append(br);
    }

    private static void addStatistics(StringBuilder sb, String br, Individual<int[]> individual, int generationCount) {
        sb.append(br);
        sb.append("Melhor caso: ").append(individual).append(br);
        sb.append("Contagem de Gerações: ").append(generationCount).append(br);
        addLine(sb, br);
    }

    private static void addLine(StringBuilder sb, String br) {
        sb.append("====================================================================================").append(br);
    }

}
