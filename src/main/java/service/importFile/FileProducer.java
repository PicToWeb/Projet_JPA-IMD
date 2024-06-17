package service.importFile;

import java.util.HashMap;

import dao.RealisateurDao;
import entity.Realisateur;
import parseCsv.RealisateurReaderCsv;
import utils.FileSource;
import utils.JpaConnection;

public abstract class FileProducer {
	
	public static final RealisateurDao realisateurDao = JpaConnection.realisateurDao();
	
	public static HashMap<String,Realisateur> link(String url) {
		return RealisateurReaderCsv.readFileToMap(FileSource.nom(url));
	}

	public static void addCsvToDataBase(HashMap<String,Realisateur> realisateurMap) {
		realisateurDao.splitInsert(realisateurMap);
	}
}
