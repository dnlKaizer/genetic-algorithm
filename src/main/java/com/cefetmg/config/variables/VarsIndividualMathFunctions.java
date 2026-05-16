package com.cefetmg.config.variables;

import com.cefetmg.config.MetadataConfig;
import com.cefetmg.problems.math_functions.model.individuals.CrossoverType;

public class VarsIndividualMathFunctions {
    private VarsIndividualMathFunctions() {}

    private final static String PREFIX = "individual_math_function";

    public static double getMutationRate() {
        return MetadataConfig.getDouble(PREFIX + ".mutation_rate", 0.1);
    }

    public static double getAlpha() {
        return MetadataConfig.getDouble(PREFIX + ".alpha", 0.3);
    }

    public static CrossoverType getCrossoverType(String functionRootPrefix) {
        String crossoverTypeStr = MetadataConfig.getString(functionRootPrefix + ".crossover_type", null);
        return CrossoverType.fromString(crossoverTypeStr);
    }

    public static VarsLangermannConfig getVarsLangermann() {
        return new VarsLangermannConfig(
            MetadataConfig.getBoolean(PREFIX + ".langermann", true),
            // Corrigido: Apontando para a chave base do yaml
            getCrossoverType("langermann_function"),
            MetadataConfig.getDoubleArray("langermann_function.c", new double[]{ 1, 2, 5, 2, 3 }),
            MetadataConfig.getDouble2DArray("langermann_function.A", new double[][] {
                { 3, 5 },
                { 5, 2 },
                { 2, 1 },
                { 1, 4 },
                { 7, 9 }
            })
        );
    }

    public static VarsDixonPriceConfig getVarsDixonPrice() {
        return new VarsDixonPriceConfig(
            MetadataConfig.getBoolean(PREFIX + ".dixon-price", true),
            // Corrigido: Apontando para a chave base do yaml
            getCrossoverType("dixon_price_function"),
            MetadataConfig.getInt("dixon_price_function.dimensions", 4)
        );
    }

    public static VarsPowellConfig getVarsPowell() {
        return new VarsPowellConfig(
            MetadataConfig.getBoolean(PREFIX + ".powell", true),
            // Corrigido: Apontando para a chave base do yaml
            getCrossoverType("powell_function"),
            MetadataConfig.getInt("powell_function.dimensions", 4)
        );
    }

    public record InnerVarsIndividualMathFuncitons(
        double mutationRate,
        double alpha,
        VarsLangermannConfig langermann,
        VarsDixonPriceConfig dixonPrice,
        VarsPowellConfig powell
    ) {}

    public record VarsLangermannConfig(
        boolean execute,
        CrossoverType crossoverType,
        double[] c,
        double[][] a
    ) {}

    public record VarsDixonPriceConfig(
        boolean execute,
        CrossoverType crossoverType,
        int dimensions
    ) {}

    public record VarsPowellConfig(
        boolean execute,
        CrossoverType crossoverType,
        int dimensions
    ) {}

    public static InnerVarsIndividualMathFuncitons getInnerVarsIndividualMathFuncitons() {
        return new InnerVarsIndividualMathFuncitons(
            getMutationRate(),
            getAlpha(),
            getVarsLangermann(),
            getVarsDixonPrice(),
            getVarsPowell()
        );
    }
}
