package com.cefetmg.config.variables;

import com.cefetmg.config.MetadataConfig;

public class VarsRandom {
    private VarsRandom() {}

    public static Long getSeed() {
        return MetadataConfig.getLong("random.seed", null);
    }
}
