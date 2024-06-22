package parseCsv;


import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.HashMap;
import java.util.List;

import entity.Address;
import entity.Producer;
import utils.Convert;
import utils.FileSource;

/**
 * Abstract Class used to read realisateurs.csv and return a HashMap of producer
 */
public abstract class ProducerReaderCsv {

	/**
	 * Static Method used to read to read each lines of Csv file. Loop is used to
	 * call a parsing method to return a producer before to added in the
	 * HashMap<id_producer, producer>
	 * 
	 * @param url realisateurs.csv
	 * @return HashMap<id_producer, producer>
	 */
	public static HashMap<String, Producer> readFile(String url) {

		HashMap<String, Producer> producerMap = new HashMap<>();
		List<String> linesList = null;

			linesList = FileSource.readLinesCsv(url);
			linesList.remove(0);

			for (String data : linesList) {
				Producer producer = parseLine(data);
				producerMap.put(producer.getId(), producer);
			}

			return producerMap;
	}

	
	/**
	 * Static Method used to parse a producer row
	 * Convert Method is called for birthday date
	 * stringToAdress Method is called for Adress
	 * 
	 * @param line (row of producer csv file)
	 * @return Producer Object
	 */
	public static Producer parseLine(String line) {

		String[] column = line.split(";", -1);
		Producer producer = new Producer();

		if (column.length < 6) {

			String id = column[0];
			String identite = column[1];

			LocalDate birthdayDate = null;
			try {
				if (column[2].split(" ").length == 3) {
					birthdayDate = Convert.UsDate(column[2]);
				}

			} catch (DateTimeParseException e) {
				System.err.println(e.getMessage()); 
			}

			Address birthplace = AddressReaderCsv.parseLine(column[3]);

			String url = column[4];
			
			producer.setId(id);
			producer.setIdentite(identite);
			producer.setUrl(url);
			producer.setBirthdayDate(birthdayDate);
			producer.setAdress(birthplace);
		
		}
		return producer;

	}

}
