package service.importFile;

import java.util.HashMap;

import dao.AddressDao;
import dao.ProducerDao;
import entity.Producer;
import parseCsv.ProducerReaderCsv;
import service.connection.DaoLink;

public abstract class FileProducer {

	/** producerDao */
	public static final ProducerDao producerDao = DaoLink.producerDao();
	/** addressDao */
	public static final AddressDao addressDao = DaoLink.addressDao();

	public static HashMap<String, Producer> link(String url) {
		return ProducerReaderCsv.readFile(url);
	}

	public static void addCsvToDataBase(HashMap<String, Producer> producerMap) {
	
//		producerMap.values().forEach(producer -> {
//			 	adressDao.lieuExistOrAdded(producer.getAdress());
//			 	producerDao.splitInsert(producer);
////		        System.out.println("Adresse du producteur " + producer.getIdentite() + ": " + producer.getAdress());
//		    });

		producerDao.allInsert(producerMap);
	}
}
