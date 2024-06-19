package service.importFile;

import java.util.HashMap;

import dao.AdressDao;
import dao.ProducerDao;
import entity.Producer;
import parseCsv.ProducerReaderCsv;
import service.connection.DaoLink;

public abstract class FileProducer {

	public static final ProducerDao producerDao = DaoLink.producerDao();
	public static final AdressDao adressDao = DaoLink.adressDao();

	public static HashMap<String, Producer> link(String url) {
		return ProducerReaderCsv.readFileToMap(url);
	}

	public static void addCsvToDataBase(HashMap<String, Producer> producerMap) {
	
//		producerMap.values().forEach(producer -> {
//			 	adressDao.lieuExistOrAdded(producer.getAdress());
//			 	producerDao.splitInsert(producer);
////		        System.out.println("Adresse du producteur " + producer.getIdentite() + ": " + producer.getAdress());
//		    });

		producerDao.splitInsert(producerMap);
	}
}
