package service.importFile;

import java.util.HashMap;

import dao.AddressDao;
import dao.ProducerDao;
import entity.Producer;
import parseCsv.ProducerReaderCsv;
import service.connection.DaoLink;

/**
 * Abstract class used to manage producer file
 */
public abstract class FileProducer {

	 /** DAO instance for managing producers */
	public static final ProducerDao PRODUCER_DAO = DaoLink.producerDao();
	 /** DAO instance for managing addresses */
	public static final AddressDao ADDRESS_DAO = DaoLink.addressDao();

	/**
	 * Reads and parses a CSV file from the specified URL.
	 *
	 * @param url The URL of the CSV file.
	 * @return A HashMap with producer names as keys and Producer objects as values.
	 */
	public static HashMap<String, Producer> link(String url) {
		return ProducerReaderCsv.readFile(url);
	}

	/**
	 * Adds the parsed producer data to the database.
	 *
	 * @param producerMap The HashMap containing producer names and Producer objects.
	 */
	public static void addCsvToDataBase(HashMap<String, Producer> producerMap) {

		PRODUCER_DAO.allInsert(producerMap);
	}
}
