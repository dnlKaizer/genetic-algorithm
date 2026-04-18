package com.cefetmg.view;

import com.cefetmg.config.variables.VarsExecutionDefaults;
import com.cefetmg.core.GeneticAlgorithm;
import com.cefetmg.core.interfaces.Individual;
import com.cefetmg.problems.nQueens.IndividualNQueensFactory;

public class NQueensView {

    private NQueensView() {}

    private record NQueensVariables(
        int numQueens,
        int numIndividuals,
        int numEliteIndividuals,
        int numGenerations
    ) {}

    public static String execute() {
        String br = System.lineSeparator();
        StringBuilder sb = new StringBuilder(br);

        NQueensVariables vars = new NQueensVariables(
            VarsExecutionDefaults.getNumQueens(),
            VarsExecutionDefaults.getNumIndividuals(),
            VarsExecutionDefaults.getNumEliteIndividuals(),
            VarsExecutionDefaults.getNumGenerations()
        );

        IndividualNQueensFactory factory = new IndividualNQueensFactory(vars.numQueens());
        GeneticAlgorithm<int[]> algorithm = new GeneticAlgorithm<>();
        Individual<int[]> individual = algorithm.execute(factory, vars.numIndividuals(), vars.numEliteIndividuals(), vars.numGenerations());

        addHeader(sb, br);
        addConfig(sb, br, vars);
        addStatistics(sb, br, individual, algorithm);

        return sb.toString();
    }

    private static void addHeader(StringBuilder sb, String br) {
        addLine(sb, br);
        sb.append("\t\tAlgoritmo Genético - Problema das N Rainhas").append(br);
        addLine(sb, br);
    }

    private static void addConfig(StringBuilder sb, String br, NQueensVariables vars) {
        sb.append("Configurações de Execução:").append(br);
        sb.append("\tNúmero de Rainhas: " + vars.numQueens()).append(br);
        sb.append("\tNúmero de Indivíduos: " + vars.numIndividuals()).append(br);
        sb.append("\tNúmero de Indivíduos Elites: " + vars.numEliteIndividuals()).append(br);
        sb.append("\tNúmero de Gerações: " + vars.numGenerations()).append(br);
    }

    private static void addStatistics(StringBuilder sb, String br, Individual<int[]> individual, GeneticAlgorithm<int[]> algorithm) {
        sb.append("\nMelhor caso -> " + individual).append(br);
        // sb.append("\nNúmero de Gerações: " + algorithm.getNumGenerations()).append(br);
        addLine(sb, br);
    }

    private static void addLine(StringBuilder sb, String br) {
        sb.append("====================================================================================").append(br);
    }

}
