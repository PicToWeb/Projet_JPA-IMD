package service.importFile;

import java.util.HashMap;

import dao.AddressDao;
import dao.ProducerDao;
import entity.Producer;
import parseCsv.ProducerReaderCsv;
import service.connection.DaoLink;

public abstract class FileProducer {

	/** producerDao */
	public static final ProducerDao PRODUCER_DAO = DaoLink.producerDao();
	/** addressDao */
	public static final AddressDao ADDRESS_DAO = DaoLink.addressDao();

	/**
	 * @param url
	 * @return
	 */
	public static HashMap<String, Producer> link(String url) {
		return ProducerReaderCsv.readFile(url);
	}

	/**
	 * @param producerMap
	 */
	public static void addCsvToDataBase(HashMap<String, Producer> producerMap) {

		PRODUCER_DAO.allInsert(producerMap);
	}
}
