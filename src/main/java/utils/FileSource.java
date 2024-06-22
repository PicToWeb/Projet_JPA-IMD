package utils;

import java.io.File;

import java.io.IOException;
import java.util.List;

import org.apache.commons.io.FileUtils;

/**
 * Abstract Class used for the file path
 */
public abstract class FileSource {

	/**
	 * Reads lines from a CSV file and returns them as a list of strings.
	 *
	 * @param source The path to the CSV file.
	 * @return A list of strings representing the lines from the CSV file.
	 */
	public static List<String> readLinesCsv(String source) {
		String filePath = ClassLoader.getSystemClassLoader().getResource(source).getFile();
		File file = new File(filePath);
		try {
			return FileUtils.readLines(file, "UTF-8");
		} catch (IOException e) {
			throw new RuntimeException(e);
			
		}
	}

}
