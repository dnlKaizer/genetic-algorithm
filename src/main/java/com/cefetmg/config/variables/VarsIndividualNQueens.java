package com.cefetmg.config.variables;

import com.cefetmg.config.MetadataConfig;

public class VarsIndividualNQueens {
    private VarsIndividualNQueens() {}

    public static double getMutationRate() {
        return MetadataConfig.getDouble("individual_n_queens.mutation_rate", 0.3);
    }
}
