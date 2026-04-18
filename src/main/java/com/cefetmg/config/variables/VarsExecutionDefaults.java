package com.cefetmg.config.variables;

import com.cefetmg.config.MetadataConfig;

public class VarsExecutionDefaults {
    private VarsExecutionDefaults() {}

    public static int getNumQueens() {
        return MetadataConfig.getInt("execution_defaults.num_queens", 8);
    }

    public static int getNumIndividuals() {
        return MetadataConfig.getInt("execution_defaults.num_individuals", 20);
    }

    public static int getNumEliteIndividuals() {
        return getNumIndividuals() / 5;
    }

    public static int getMaxGenerations() {
        return MetadataConfig.getInt("execution_defaults.max_generations", 10000);
    }
}
