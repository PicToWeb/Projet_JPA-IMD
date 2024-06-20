package parseCsv;

import java.util.ArrayList;
import java.util.List;
import dao.CountryDao;
import entity.Country;
import service.connection.DaoLink;
import utils.FileSource;

/**
 * Abstract class used to parse and persist country data from acteurs.csv & realisateurs.csv
 * & films.csv and return Country Object
 **/
public abstract class CountryReaderCsv {
	
	/** countryDao */
	public static final CountryDao COUNTRY_DAO = DaoLink.countryDao();
	
	/**
	 * Static Method used to read each lines of Csv file. The first line is removed
	 * (header of column) For each line, a Country Object is create and added to a List
	 * 
	 * @param url from Csv file in main/resources
	 * @return List of Country
	 */
	public static List<Country> readAndParseFile(String url) {

		List<Country> countryList = new ArrayList<>();

			List<String> linesList = FileSource.readLinesCsv(url);
			linesList.remove(0);

			for (String line : linesList) {
				String[] column = line.split(";");
				
				if (column.length <= 2) {
				
				Country p = new Country();
				
				p.setName(column[0].trim());
				p.setUrl(column[1]);
				
				countryList.add(p);
				}
			}
			return countryList;
	}

	/**
	 * Static Method used to rename country abbreviations and
	 * verify if Country needs to be insert in Database 
	 * 
	 * @param countryString 
	 * @return Country composed by a name and an URL
	 */
	public static Country existOrAdd(String countryString) {
	
		if ((countryString == null) || (countryString.length() > 60)) {
			return new Country("", "");
		}
	
		String usa = "USA";
		String uk = "UK";
		String cleanedPays = countryString.replaceAll("\\[.*?\\]", "").trim();
	
		if (cleanedPays.equals(usa)) {
			cleanedPays = "United States";
		} else if (cleanedPays.equals(uk)) {
			cleanedPays = "United Kingdom";
		}
	
		Country existingCountry = COUNTRY_DAO.findByName(cleanedPays);
		
		if (existingCountry == null) {
			existingCountry = new Country(cleanedPays, "");
			COUNTRY_DAO.insert(existingCountry);
		}
	
		return existingCountry;
	}

	

}
