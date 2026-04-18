package com.cefetmg.metadata.variables;

import com.cefetmg.metadata.MetadataConfig;

public class VarsRandom {
    private VarsRandom() {}

    public static Long getSeed() {
        return MetadataConfig.getLong("random.seed", null);
    }
}
