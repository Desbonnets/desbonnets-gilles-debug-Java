package com.hemebiotech.analytics;

import java.util.*;

public class AnalyticsCounter implements ISymptomAnalyticsCounter {

	/**
	 * Counts the occurrences of symptoms in the provided list and sorts the results based on the specified
	 * sorting criterion.
	 *
	 * @param symptoms     List of symptoms (each symptom is represented as a string).
	 * @param sortCriteria Sorting criterion to apply on the results. The possible values are:
	 *                     <ul>
	 *                         <li>"alphabetical_asc" : Sorts the symptoms alphabetically in ascending order.</li>
	 *                         <li>"alphabetical_desc" : Sorts the symptoms alphabetically in descending order.</li>
	 *                         <li>"frequency_asc" : Sorts the symptoms by increasing frequency (number of occurrences).</li>
	 *                         <li>"frequency_desc" : Sorts the symptoms by decreasing frequency (number of occurrences).</li>
	 *                     </ul>
	 * @return A {@code Map<String, Integer>} containing the symptoms as keys and their respective number of
	 *         occurrences as values, sorted according to the specified criterion.
	 *
	 * @throws IllegalArgumentException If the provided sorting criterion is not valid.
	 */
	public Map<String, Integer> GetAnalyticsCounterSort(List<String> symptoms, String sortCriteria) {
		// Validate the symptoms list
		if (symptoms == null) {
			throw new IllegalArgumentException("The symptoms list cannot be null.");
		}
		if (symptoms.isEmpty()) {
			return Collections.emptyMap();  // Return an empty map if the list is empty
		}

		// Validate the sorting criterion
		if (sortCriteria == null || sortCriteria.trim().isEmpty()) {
			throw new IllegalArgumentException("The sorting criterion cannot be null or empty.");
		}

		// Create a HashMap to count the occurrences of each symptom
		Map<String, Integer> symptomsCount = new HashMap<>();

		// Count occurrences of symptoms
		for (String symptom : symptoms) {
			if (symptom != null) {  // Handle null symptoms in the list (optional)
				symptomsCount.put(symptom, symptomsCount.getOrDefault(symptom, 0) + 1);
			}
		}

		// Sort the results based on the sorting criterion
		List<Map.Entry<String, Integer>> sortedList = new ArrayList<>(symptomsCount.entrySet());

		// Handle different sorting criteria
		switch (sortCriteria.toLowerCase()) {
			case "alphabetical_asc":
				// Sort by alphabetical order (ascending) of the symptoms (keys)
				sortedList.sort(Map.Entry.comparingByKey());
				break;

			case "alphabetical_desc":
				// Sort by alphabetical order (descending) of the symptoms (keys)
				sortedList.sort(Map.Entry.<String, Integer>comparingByKey().reversed());
				break;

			case "frequency_asc":
				// Sort by increasing frequency (values)
				sortedList.sort(Map.Entry.comparingByValue());
				break;

			case "frequency_desc":
				// Sort by decreasing frequency (values)
				sortedList.sort((a, b) -> b.getValue().compareTo(a.getValue()));
				break;

			default:
				throw new IllegalArgumentException("Unknown sorting criterion. " +
						"Use 'alphabetical_asc', 'alphabetical_desc', 'frequency_asc', or 'frequency_desc'."
				);
		}

		// Convert the sorted list back to a LinkedHashMap to preserve the sorted order
		Map<String, Integer> sortedSymptomsCount = new LinkedHashMap<>();
		for (Map.Entry<String, Integer> entry : sortedList) {
			sortedSymptomsCount.put(entry.getKey(), entry.getValue());
		}

		return sortedSymptomsCount;
	}
}
