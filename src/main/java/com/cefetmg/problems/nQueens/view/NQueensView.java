package com.cefetmg.problems.nQueens.view;

import com.cefetmg.core.interfaces.Individual;
import com.cefetmg.problems.nQueens.model.records.NQueensStatistics;
import com.cefetmg.problems.nQueens.model.records.NQueensVariables;

public class NQueensView {

    private NQueensView() {}

    public static String display(NQueensStatistics statistics) {
        String br = System.lineSeparator();
        StringBuilder sb = new StringBuilder(br);

        addHeader(sb, br);
        addConfig(sb, br, statistics.variables());
        addStatistics(sb, br, statistics.bestIndividual(), statistics.generationCount());

        return sb.toString();
    }

    private static void addHeader(StringBuilder sb, String br) {
        addLine(sb, br);
        sb.append("\t\tAlgoritmo Genético - Problema das N Rainhas").append(br);
        addLine(sb, br);
    }

    private static void addConfig(StringBuilder sb, String br, NQueensVariables vars) {
        sb.append("Configurações de Execução:").append(br);
        sb.append("\tNúmero de Rainhas: ").append(vars.numQueens()).append(br);
        sb.append("\tNúmero de Indivíduos: ").append(vars.numIndividuals()).append(br);
        sb.append("\tNúmero de Elites: ").append(vars.numEliteIndividuals()).append(br);
        sb.append("\tMáximo de Gerações: ").append(vars.maxGenerations()).append(br);
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
