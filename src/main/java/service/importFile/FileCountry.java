package service.importFile;

import java.util.List;

import dao.CountryDao;
import entity.Pays;
import parseCsv.CountryReaderCsv;
import utils.FileSource;
import utils.JpaConnection;

public abstract class FileCountry {

	public static final CountryDao countryDao = JpaConnection.countryDao();

	public static List<Pays> link(String url) {
		return CountryReaderCsv.readFileToList(FileSource.nom(url));
	}

	public static void addCsvToDataBase(List<Pays> countryList) {
		for (Pays p : countryList) {
			if (!countryDao.countryExist(p.getNom())) {
				countryDao.insert(p);
			}
		}
	}
}
