package com.hemebiotech.analytics;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class ReadSymptomDataFromFile implements ISymptomReader {

	private final String filepath;
	Logger logger = Logger.getLogger(getClass().getName());

	/**
	 * Constructor that initializes the class with the file path to read symptoms from.
	 *
	 * @param filepath A full or partial path to a file containing symptom strings, one per line.
	 *                 The filepath must not be null or empty.
	 * @throws IllegalArgumentException If the provided filepath is null or empty.
	 */
	public ReadSymptomDataFromFile(String filepath) {
		if (filepath == null || filepath.trim().isEmpty()) {
			throw new IllegalArgumentException("The file path cannot be null or empty.");
		}
		this.filepath = filepath;
	}

	/**
	 * Reads symptoms from the file specified in the constructor and returns them as a list of strings.
	 *
	 * @return A list of symptoms read from the file. If the file cannot be read, an empty list is returned.
	 * @throws IllegalStateException If the filepath is invalid or the file cannot be opened.
	 */
	@Override
	public List<String> getSymptoms() {
		List<String> result = new ArrayList<>();

		// Validate that filepath is not null
		if (filepath != null) {
			try (BufferedReader reader = new BufferedReader(new FileReader(filepath))) {
				String line = reader.readLine();

				// Read each line from the file
				while (line != null) {
					result.add(line);
					line = reader.readLine();
				}
			} catch (FileNotFoundException e) {
				logger.severe("File not found: " + filepath);
				throw new IllegalStateException("The file at the specified path could not be found: " + filepath, e);
			} catch (IOException e) {
				logger.severe("An error occurred while reading the file: " + e.getMessage());
				throw new IllegalStateException("An error occurred while reading the file: " + filepath, e);
			}
		} else {
			logger.warning("File path is null.");
		}

		return result;
	}

}
