package service.importFile;

import java.util.Map;

import dao.ActorDao;
import entity.Actor;
import parseCsv.ActorReaderCsv;
import service.connection.DaoLink;

public abstract class FileActor {
	
	/** actorDao */
	public static final ActorDao ACTOR_DAO = DaoLink.actorDao();
	
	/**
	 * @param url
	 * @return
	 */
	public static Map<String,Actor> link(String url) {
		return ActorReaderCsv.readFile(url);
	}

	/**
	 * @param actorMap
	 */
	public static void addCsvToDataBase(Map<String,Actor> actorMap) {
		ACTOR_DAO.allInsert(actorMap);
	}
}
