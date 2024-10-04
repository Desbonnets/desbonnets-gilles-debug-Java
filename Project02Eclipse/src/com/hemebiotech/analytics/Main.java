package com.hemebiotech.analytics;

import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

public class Main {

    private static final Logger logger = Logger.getLogger(Main.class.getName());

    /**
     * The main method of the application that orchestrates the reading of symptoms, counting, sorting,
     * and writing the results to a file.
     *
     * @param args Command line arguments (not used in this application).
     */
    public static void main(String[] args) {
        try {
            // Read symptoms from file
            ISymptomReader symptomReader = new ReadSymptomDataFromFile("symptoms.txt");
            List<String> symptoms = symptomReader.GetSymptoms();

            // Validate that symptoms were read successfully
            if (symptoms.isEmpty()) {
                logger.warning("No symptoms were read from the file. Exiting the program.");
                return;  // Exit if no symptoms were read
            }

            // Process the symptoms: count and sort them
            ISymptomAnalyticsCounter symptomsAnalyticsCount = (ISymptomAnalyticsCounter) new AnalyticsCounter();
            Map<String, Integer> symptomsCount = symptomsAnalyticsCount.GetAnalyticsCounterSort(symptoms, "frequency_asc");

            // Write the sorted results to the output file
            ISymptomWriter symptomWriter = new WriteSymptomDataToFile();
            symptomWriter.writeSymptoms(symptomsCount);

            System.out.println("Symptoms processed and written to result.out successfully.");

        } catch (IllegalArgumentException e) {
            logger.severe("Error: " + e.getMessage());
        } catch (IllegalStateException e) {
            logger.severe("Processing failed: " + e.getMessage());
        } catch (Exception e) {
            logger.severe("An unexpected error occurred: " + e.getMessage());
        }
    }
}
