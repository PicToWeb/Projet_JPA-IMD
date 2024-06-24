package service.importFile;

import java.util.HashMap;
import dao.MovieDao;
import entity.Movie;
import parseCsv.MovieReaderCsv;
import service.connection.DaoLink;

public abstract class FileMovie {

	/** movieDao */
	public static final MovieDao MOVIE_DAO = DaoLink.movieDao();

	/**
	 * @param url
	 * @param urlDep
	 * @return
	 */
	public static HashMap<String, Movie> link(String url, String urlDep) {
		return MovieReaderCsv.readFile(url, urlDep);
	}

	/**
	 * @param movieMap
	 */
	public static void addCsvToDataBase(HashMap<String, Movie> movieMap) {
		MOVIE_DAO.allInsert(movieMap);
	}
}
