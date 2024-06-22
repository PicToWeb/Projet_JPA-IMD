package utils;

import java.time.LocalDate;

import java.time.format.DateTimeFormatter;
import java.util.Locale;

/**
 * Abstract Class used to convert data
 */
public abstract class Convert {

	
	/**
	 * Parses a string representing a date in US format (e.g., "January 1 2024") and
	 * returns a LocalDate object.
	 *
	 * @param string The input string representing the date.
	 * @return A LocalDate object parsed from the input string.
	 */
	public static LocalDate UsDate(String string) {
		String chaine = string.trim();
		LocalDate date = LocalDate.parse(chaine, DateTimeFormatter.ofPattern("MMMM d yyyy", Locale.US));

		return date;
	}

}
