package service.importFile;

import java.util.HashMap;

import dao.ActorDao;
import entity.Acteur;
import parseCsv.ActorReaderCsv;
import utils.FileSource;
import utils.JpaConnection;

public abstract class FileActor {
	
	public static final ActorDao actorDao = JpaConnection.actorDao();
	
	public static HashMap<String,Acteur> link(String url) {
		return ActorReaderCsv.readFileToMap(FileSource.nom(url));
	}

	public static void addCsvToDataBase(HashMap<String,Acteur> actorMap) {
		actorDao.splitInsert(actorMap);
	}
}
