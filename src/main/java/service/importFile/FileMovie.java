package service.importFile;

import java.io.IOException;
import java.util.HashMap;
import dao.MovieDao;
import entity.Film;
import parseCsv.MovieReaderCsv;
import utils.FileSource;
import utils.JpaConnection;

public abstract class FileMovie {

	public static final MovieDao movieDao = JpaConnection.movieDao();
	

	public static HashMap<String, Film> link(String url) throws IOException {
		return MovieReaderCsv.readFileToMap(FileSource.nom(url));
	}

	public static void addCsvToDataBase(HashMap<String,Film> movieMap) {
		movieDao.splitInsert(movieMap);
	}
}
	
