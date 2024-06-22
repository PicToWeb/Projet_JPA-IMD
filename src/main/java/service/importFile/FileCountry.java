package service.importFile;

import java.util.List;



import dao.CountryDao;
import entity.Country;
import parseCsv.CountryReaderCsv;
import service.connection.DaoLink;

public abstract class FileCountry {

	/** countryDao */
	public static final CountryDao countryDao = DaoLink.countryDao();

	public static List<Country> link(String url) {
		return CountryReaderCsv.readAndParseFile(url);
	}

	public static void addCsvToDataBase(List<Country> countryList) {
		for (Country p : countryList) {
			if (!countryDao.countryExist(p.getName())) {
				countryDao.insert(p);
			}
		}
	}
}
