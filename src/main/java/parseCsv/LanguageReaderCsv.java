package parseCsv;

import dao.LanguageDao;
import entity.MovieLanguage;
import service.connection.DaoLink;

/**
 * Abstract class used to verify if Language from films.csv needs to be insert
 * in Database
 */
public abstract class LanguageReaderCsv {

	/** langueDao */
	public static final LanguageDao languageDao = DaoLink.languageDao();

	/**
	 * Static Method used to verify if Language needs to be insert in Database
	 * 
	 * @param languageReceive from MovieReaderCsv
	 * @return movieLanguage MovieLanguage Object
	 */
	public static MovieLanguage languageExistOrAdded(String languageReceive) {

		MovieLanguage movieLanguage = languageDao.findByName(languageReceive.trim());

		if (movieLanguage == null) {
			movieLanguage = new MovieLanguage(languageReceive.trim());
			languageDao.insert(movieLanguage);
		}

		return movieLanguage;
	}

}
