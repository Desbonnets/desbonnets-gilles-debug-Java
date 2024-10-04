package com.hemebiotech.analytics;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Map;
import java.util.logging.Logger;

public class WriteSymptomDataToFile implements ISymptomWriter {

    Logger logger = Logger.getLogger(getClass().getName());

    @Override
    public void writeSymptoms(Map<String, Integer> symptomsCount) {
        // Validate the symptomsCount map
        if (symptomsCount == null || symptomsCount.isEmpty()) {
            throw new IllegalArgumentException("The symptomsCount map cannot be null or empty.");
        }

        // Attempt to write the symptoms to the file
        try (FileWriter writer = new FileWriter("result.out")) {
            for (Map.Entry<String, Integer> entry : symptomsCount.entrySet()) {
                writer.write(entry.getKey() + ": " + entry.getValue() + "\n");
            }
        } catch (IOException e) {
            logger.severe("An error occurred while writing to the file: " + e.getMessage());
        }
    }
}
