package service.importFile;

import java.util.HashMap;
import dao.MovieDao;
import entity.Movie;
import parseCsv.MovieReaderCsv;
import service.connection.DaoLink;

/**
 * Abstract class used to manage movie file
 */
public abstract class FileMovie {

	/** DAO instance for managing movies */
	public static final MovieDao MOVIE_DAO = DaoLink.movieDao();

	/**
	 * Reads and parses a CSV file from the specified URL and its dependent URL.
	 *
	 * @param url    The URL of the main CSV file.
	 * @param urlDep The URL of the dependent CSV file.
	 * @return A HashMap with movie titles as keys and Movie objects as values.
	 */
	public static HashMap<String, Movie> link(String url, String urlDep) {
		return MovieReaderCsv.readFile(url, urlDep);
	}

	/**
	 * Adds the parsed movie data to the database.
	 *
	 * @param movieMap The HashMap containing movie titles and Movie objects.
	 */
	public static void addCsvToDataBase(HashMap<String, Movie> movieMap) {
		MOVIE_DAO.allInsert(movieMap);
	}
}
