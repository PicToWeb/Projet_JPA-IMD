package utils;

import java.time.LocalDate;

import java.time.format.DateTimeFormatter;
import java.util.Locale;



public class Convertir {
	
	//public static final CountryDao countryDao = JpaConnection.countryDao();
	
	
	public static LocalDate stringToDateUS(String string) {
		String chaine = string.trim();
		LocalDate date = LocalDate.parse(chaine, DateTimeFormatter.ofPattern("MMMM d yyyy", Locale.US));

		return date;
	}
	

	
	

}
