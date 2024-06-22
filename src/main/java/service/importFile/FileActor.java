package service.importFile;

import java.util.Map;

import dao.ActorDao;
import entity.Actor;
import parseCsv.ActorReaderCsv;
import service.connection.DaoLink;

public abstract class FileActor {
	
	/** actorDao */
	public static final ActorDao actorDao = DaoLink.actorDao();
	
	public static Map<String,Actor> link(String url) {
		return ActorReaderCsv.readFile(url);
	}

	public static void addCsvToDataBase(Map<String,Actor> actorMap) {
		actorDao.allInsert(actorMap);
	}
}
