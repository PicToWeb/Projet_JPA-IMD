package service.importFile;

import java.util.List;



import dao.CountryDao;
import entity.Country;
import parseCsv.CountryReaderCsv;
import service.connection.DaoLink;

/**
 * Abstract class used to manage country file
 */
public abstract class FileCountry {

	 /** DAO instance for managing countries */
	public static final CountryDao COUNTRY_DAO = DaoLink.countryDao();

	/**
	 * Reads and parses a CSV file from the specified URL.
	 *
	 * @param url The URL of the CSV file.
	 * @return A list of Country objects parsed from the CSV.
	 */
	public static List<Country> link(String url) {
		return CountryReaderCsv.readAndParseFile(url);
	}

	/**
	 * Adds the parsed CSV data to the database.
	 *
	 * @param countryList The list of Country objects to add.
	 */
	public static void addCsvToDataBase(List<Country> countryList) {
		for (Country p : countryList) {
			if (!COUNTRY_DAO.countryExist(p.getName())) {
				COUNTRY_DAO.insert(p);
			}
		}
	}
}
