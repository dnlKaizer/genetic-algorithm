package com.cefetmg.problems.math_functions.model.individuals;

public enum CrossoverType {
    ARITHMETIC("Aritmético"),
    BLX_ALPHA("BLX-Alpha");

    private final String name;

    private CrossoverType(String name) {
        this.name = name;
    }

    public static CrossoverType fromString(String str) {
        if (str == null) {
            return null;
        }
        try {
            return CrossoverType.valueOf(str.trim().toUpperCase());
        } catch (IllegalArgumentException e) {
            return null;
        }
    }

    @Override
    public String toString() {
        return name;
    }

}
