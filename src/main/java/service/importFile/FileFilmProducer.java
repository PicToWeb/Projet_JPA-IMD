package service.importFile;

import java.util.HashMap;

import dao.MovieDao;
import entity.Acteur;
import parseCsv.ActorReaderCsv;
import utils.FileSource;
import utils.JpaConnection;

public abstract class FileFilmProducer {
	
	public static final MovieDao movieDao = JpaConnection.movieDao();
	
	public static HashMap<String,Acteur> link(String url) {
		return ActorReaderCsv.readFileToMap(FileSource.nom(url));
	}

	public static void addCsvToDataBase(HashMap<String,String> filmProduMap) {
		//movieDao.splitInsert(filmProduMap);
	}
}
