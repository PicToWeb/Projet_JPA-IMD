package service.importFile;

import java.util.Map;

import dao.ActorDao;
import entity.Actor;
import parseCsv.ActorReaderCsv;
import service.connection.DaoLink;


/**
 * Abstract class used to manage actor file
 */
public abstract class FileActor {
	
	 /** DAO instance for managing actors */
	public static final ActorDao ACTOR_DAO = DaoLink.actorDao();
	
	  /**
     * Reads actor data from a CSV file.
     *
     * @param url URL of the CSV file
     * @return Map of actor names to Actor objects
     */
	public static Map<String,Actor> link(String url) {
		return ActorReaderCsv.readFile(url);
	}

    /**
     * Adds actor data from a map to the database.
     *
     * @param actorMap Map of actor names to Actor objects
     */
	public static void addCsvToDataBase(Map<String,Actor> actorMap) {
		ACTOR_DAO.allInsert(actorMap);
	}
}
