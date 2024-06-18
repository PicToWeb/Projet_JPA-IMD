package service.importFile;

import java.util.HashMap;


import dao.ProducerDao;
import entity.Producer;
import parseCsv.ProducerReaderCsv;
import service.connection.DaoLink;

public abstract class FileProducer {
	
	public static final ProducerDao producerDao = DaoLink.producerDao();
	
	public static HashMap<String,Producer> link(String url) {
		return ProducerReaderCsv.readFileToMap(url);
	}

	public static void addCsvToDataBase(HashMap<String,Producer> producerMap) {
		producerDao.splitInsert(producerMap);
	}
}
