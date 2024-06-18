package utils;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.apache.commons.io.FileUtils;

/**
 * Abstract Class used for the file path
 */
public abstract class FileSource {

	/** File FORMAT */
	public static final String FORMAT = "UTF-8";

	/**
	 * Returns the file path for the specified resource.
	 *
	 * @param source The name of the resource.
	 * @return The file path of the resource.
	 */
	public static String nom(String source) {

		String filePath = ClassLoader.getSystemClassLoader().getResource(source).getFile();

		return filePath;
	}

	public static List<String> readLinesCsv(String source) {
		String filePath = ClassLoader.getSystemClassLoader().getResource(source).getFile();
		File file = new File(filePath);
		try {
			return FileUtils.readLines(file, "UTF-8");
		} catch (IOException e) {
			System.err.println(e.getMessage());
			e.printStackTrace();
			return null;
		}
	}

}
