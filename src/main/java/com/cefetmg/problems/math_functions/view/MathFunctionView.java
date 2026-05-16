package com.cefetmg.problems.math_functions.view;

import java.util.List;

import com.cefetmg.problems.math_functions.model.MathFunctionStatistics;

public class MathFunctionView {
    private MathFunctionView() {}

    public static boolean shouldContinue() {
        System.out.print("\nExecutar (qualquer caracter cancela): ");
        String input = System.console().readLine();
        return input.equalsIgnoreCase("");
    }

    public static void display(List<MathFunctionStatistics> statistics) {
        if (statistics.isEmpty()) {
            System.out.println("Nenhuma função foi executada.");
            return;
        }

        String br = System.lineSeparator();
        StringBuilder sb = new StringBuilder(br);

        addHeader(sb, br);
        addConfig(sb, br, statistics.get(0));

        for (MathFunctionStatistics mathFunctionStatistics : statistics) {
            addLine(sb, br);
            addStatistics(sb, br, mathFunctionStatistics);
        }

        addHeaderLine(sb, br);

        System.out.print(sb.toString());
    }

    private static void addHeader(StringBuilder sb, String br) {
        addHeaderLine(sb, br);
        sb.append("\t\t\tAlgoritmo Genético - Funções de Otimização").append(br);
        addHeaderLine(sb, br);
    }

    private static void addConfig(StringBuilder sb, String br, MathFunctionStatistics statistics) {
        sb.append("Configurações de Execução:").append(br);
        
        sb.append("\tTaxa de Mutação: ").append(statistics.mutationRate()).append(br);
        sb.append("\tNúmero de Indivíduos: ").append(statistics.numIndividuals()).append(br);
        sb.append("\tNúmero de Elites: ").append(statistics.numEliteIndividuals()).append(br);
        sb.append("\tMáximo de Gerações: ").append(statistics.maxGenerations()).append(br);
    }
    
    private static void addStatistics(StringBuilder sb, String br, MathFunctionStatistics statistics) {
        sb.append("Função Matemática: ").append(statistics.functionName()).append(br);
        sb.append("Número de Dimensões: ").append(statistics.numDimensions()).append(br);
        sb.append("Tipo de Crossover: ").append(statistics.crossoverType()).append(br);
        sb.append(br);
        sb.append("Melhor caso: ").append(statistics.bestIndividual()).append(br);
        sb.append("Contagem de Gerações: ").append(statistics.numGenerations()).append(br);
    }

    private static void addHeaderLine(StringBuilder sb, String br) {
        sb.append("=====================================================================================================").append(br);
    }

    private static void addLine(StringBuilder sb, String br) {
        sb.append("-----------------------------------------------------------------------------------------------------").append(br);
    }

}
