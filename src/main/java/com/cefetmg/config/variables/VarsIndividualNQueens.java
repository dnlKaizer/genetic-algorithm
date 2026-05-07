package com.cefetmg.config.variables;

import com.cefetmg.config.MetadataConfig;

public class VarsIndividualNQueens {
    private VarsIndividualNQueens() {}

    public static int getNumQueens() {
        return MetadataConfig.getInt("individual_n_queens.num_queens", 8);
    }

    public static double getMutationRate() {
        return MetadataConfig.getDouble("individual_n_queens.mutation_rate", 0.3);
    }

    public record InnerVarsIndividualNQueens(
        int numQueens,
        double mutationRate
    ) {}

    public static InnerVarsIndividualNQueens getInnerVarsIndividualNQueens() {
        return new InnerVarsIndividualNQueens(
            getNumQueens(),
            getMutationRate()
        );
    }

}
