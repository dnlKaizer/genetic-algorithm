package com.cefetmg.metadata;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.Test;

class MetadataConfigTest {

    @Test
    void getIntShouldReturnDefaultWhenPathDoesNotExist() {
        int value = MetadataConfig.getInt("unknown.path", 42);

        assertEquals(42, value);
    }

    @Test
    void getDoubleShouldReturnDefaultWhenPathDoesNotExist() {
        double value = MetadataConfig.getDouble("unknown.path", 0.75);

        assertEquals(0.75, value);
    }

    @Test
    void getLongShouldReturnNullWhenPathDoesNotExistAndDefaultIsNull() {
        Long value = MetadataConfig.getLong("unknown.path", null);

        assertNull(value);
    }
}
