package com.cefetmg;

import java.util.Objects;
import java.util.Random;

import com.cefetmg.metadata.variables.VarsRandom;

public class RandomSingleton {
    private static Random instance = new Random();
    private static Long currentSeed = null;

    private RandomSingleton() {}

    public static synchronized Random getInstance() {
        Long configuredSeed = VarsRandom.getSeed();

        if (!Objects.equals(configuredSeed, currentSeed)) {
            currentSeed = configuredSeed;
            instance = configuredSeed == null ? new Random() : new Random(configuredSeed);
        }

        return instance;
    }
}
