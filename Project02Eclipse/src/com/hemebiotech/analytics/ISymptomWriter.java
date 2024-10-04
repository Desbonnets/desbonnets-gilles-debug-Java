package com.hemebiotech.analytics;

import java.util.Map;

public interface ISymptomWriter {

    /**
     * Writes the given map of symptoms and their occurrence counts to an output destination.
     * <p>
     * Each entry in the {@code symptoms} map represents a symptom (as a {@code String})
     * and the number of times it occurred (as an {@code Integer}). Implementations should
     * specify the format and destination (e.g., a file, database, or console).
     * </p>
     *
     * @param symptomsCount A {@code Map} containing symptoms as keys and their respective occurrence counts as values.
     *                 It should not be {@code null}.
     * @throws IllegalArgumentException if the {@code symptoms} map is {@code null}.
     */
    void writeSymptoms(Map<String, Integer> symptomsCount);
}
