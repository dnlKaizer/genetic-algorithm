package com.cefetmg.config;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Collections;
import java.util.Map;

import org.yaml.snakeyaml.Yaml;

public final class MetadataConfig {

    private static final Path METADATA_PATH = Path.of("metadata.yaml");

    private MetadataConfig() {
    }

    public static int getInt(String path, int defaultValue) {
        Object value = get(path);

        if (value instanceof Number number) {
            return number.intValue();
        }

        return defaultValue;
    }

    public static double getDouble(String path, double defaultValue) {
        Object value = get(path);

        if (value instanceof Number number) {
            return number.doubleValue();
        }

        return defaultValue;
    }

    public static Long getLong(String path, Long defaultValue) {
        Object value = get(path);

        if (value instanceof Number number) {
            return number.longValue();
        }

        if (value instanceof String stringValue) {
            try {
                return Long.parseLong(stringValue.trim());
            } catch (NumberFormatException ignored) {
                return defaultValue;
            }
        }

        return defaultValue;
    }

    private static Object get(String path) {
        Map<String, Object> data = loadMetadata();
        String[] parts = path.split("\\.");
        Object current = data;

        for (String part : parts) {
            if (!(current instanceof Map<?, ?> currentMap)) {
                return null;
            }
            current = currentMap.get(part);
        }

        return current;
    }

    @SuppressWarnings("unchecked")
    private static Map<String, Object> loadMetadata() {
        if (!Files.exists(METADATA_PATH)) {
            return Collections.emptyMap();
        }

        try {
            String content = Files.readString(METADATA_PATH);
            Object parsed = new Yaml().load(content);

            if (parsed instanceof Map<?, ?> parsedMap) {
                return (Map<String, Object>) parsedMap;
            }
        } catch (IOException | RuntimeException ignored) {
            return Collections.emptyMap();
        }

        return Collections.emptyMap();
    }
}
