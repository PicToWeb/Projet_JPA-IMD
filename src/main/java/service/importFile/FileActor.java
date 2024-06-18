package service.importFile;

import java.util.HashMap;

import dao.ActorDao;
import entity.Actor;
import parseCsv.ActorReaderCsv;
import service.connection.DaoLink;

public abstract class FileActor {
	
	public static final ActorDao actorDao = DaoLink.actorDao();
	
	public static HashMap<String,Actor> link(String url) {
		return ActorReaderCsv.readFileToMap(url);
	}

	public static void addCsvToDataBase(HashMap<String,Actor> actorMap) {
		actorDao.splitInsert(actorMap);
	}
}
