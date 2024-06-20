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
	public static final LanguageDao LANGUAGE_DAO = DaoLink.languageDao();

	/**
	 * Static Method used to verify if Language needs to be insert in Database
	 * 
	 * @param languageReceive from MovieReaderCsv
	 * @return movieLanguage MovieLanguage Object
	 */
	public static MovieLanguage existOrAdd(String languageReceive) {

		MovieLanguage movieLanguage = LANGUAGE_DAO.findByName(languageReceive.trim());

		if (movieLanguage == null) {
			movieLanguage = new MovieLanguage(languageReceive.trim());
			LANGUAGE_DAO.insert(movieLanguage);
		}

		return movieLanguage;
	}

}
