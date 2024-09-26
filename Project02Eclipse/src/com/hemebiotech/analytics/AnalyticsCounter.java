package com.hemebiotech.analytics;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AnalyticsCounter {

	private static Map<String, Integer> symptomsCount = new HashMap<>();

	public static void main(String[] args) throws IOException {
		ISymptomReader symptomReader = new ReadSymptomDataFromFile("symptoms.txt");
		List<String> symptoms = symptomReader.GetSymptoms();

		// Retrieves symptoms and accounts
		for (String symptom : symptoms) {
			symptomsCount.put(symptom, symptomsCount.getOrDefault(symptom, 0) + 1);
		}

		// next generate output
		FileWriter writer = new FileWriter ("result.out");
		for (String symptom : symptomsCount.keySet()) {
			writer.write(symptom + "\t" + symptomsCount.get(symptom) + "\n");
		}
		writer.close();
	}
}
