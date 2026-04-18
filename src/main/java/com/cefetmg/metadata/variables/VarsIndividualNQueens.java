package com.cefetmg.metadata.variables;

import com.cefetmg.metadata.MetadataConfig;

public class VarsIndividualNQueens {
    private VarsIndividualNQueens() {}

    public static double getMutationRate() {
        return MetadataConfig.getDouble("individual_n_queens.mutation_rate", 0.3);
    }
}
