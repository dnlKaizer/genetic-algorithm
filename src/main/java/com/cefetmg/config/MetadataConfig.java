package com.cefetmg.config;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Collections;
import java.util.Map;
import java.util.stream.StreamSupport;

import org.yaml.snakeyaml.Yaml;

public final class MetadataConfig {

    private static final Path METADATA_PATH = Path.of("metadata.yaml");

    private MetadataConfig() {
    }

    public static String getString(String path, String defaultValue) {
        Object value = get(path);

        if (value instanceof String stringValue) {
            return stringValue.trim();
        }

        return defaultValue;
    }

    public static double[] getDoubleArray(String path, double[] defaultValue) {
        Object value = get(path);

        if (value instanceof Iterable<?> iterable) {
            return StreamSupport.stream(iterable.spliterator(), false)
                    .filter(Number.class::isInstance)
                    .mapToDouble(number -> ((Number) number).doubleValue())
                    .toArray();
        }

        return defaultValue;
    }

    public static double[][] getDouble2DArray(String path, double[][] defaultValue) {
        Object value = get(path);

        if (value instanceof Iterable<?> iterable) {
            return StreamSupport.stream(iterable.spliterator(), false)
                    .filter(inner -> inner instanceof Iterable<?>)
                    .map(inner -> StreamSupport.stream(((Iterable<?>) inner).spliterator(), false)
                            .filter(Number.class::isInstance)
                            .mapToDouble(number -> ((Number) number).doubleValue())
                            .toArray())
                    .toArray(double[][]::new);
        }

        return defaultValue;
    }

    public static boolean getBoolean(String path, boolean defaultValue) {
        Object value = get(path);

        if (value instanceof Boolean booleanValue) {
            return booleanValue;
        }

        if (value instanceof String stringValue) {
            return Boolean.parseBoolean(stringValue.trim());
        }

        return defaultValue;
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
