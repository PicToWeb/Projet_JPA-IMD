package service.importFile;

import java.util.HashMap;
import dao.MovieDao;
import entity.Movie;
import parseCsv.MovieReaderCsv;
import service.connection.DaoLink;

public abstract class FileMovie {

	public static final MovieDao movieDao = DaoLink.movieDao();

	public static HashMap<String, Movie> link(String url, String urlDep) {
		return MovieReaderCsv.readFile(url, urlDep);
	}

	public static void addCsvToDataBase(HashMap<String, Movie> movieMap) {
		movieDao.allInsert(movieMap);
	}
}
