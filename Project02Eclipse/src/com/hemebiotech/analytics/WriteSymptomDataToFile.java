package com.hemebiotech.analytics;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Map;
import java.util.logging.Logger;

public class WriteSymptomDataToFile implements ISymptomWriter {

    Logger logger = Logger.getLogger(getClass().getName());

    public void writeSymptoms(Map<String, Integer> symptomsCount) {

        try (FileWriter writer = new FileWriter("result.out")) {
            for (Map.Entry<String, Integer> entry : symptomsCount.entrySet()) {
                writer.write(entry + "\n");
            }
        } catch (IOException e) {
            logger.info(e.getMessage());
        }

    }
}
