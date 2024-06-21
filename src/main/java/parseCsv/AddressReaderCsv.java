package parseCsv;

import entity.Adress;

/**
 * Abstract class used to parse adress data from acteurs.csv & realisateurs.csv
 * & films.csv and return Adress Object
 **/
public abstract class AddressReaderCsv {

	/**
	 * Static Method used to parse an adress line and create an Adress Object
	 * adress column call a static Method from CountryReaderCsv to parse and
	 * transform "String" in "Adress" Object
	 * 
	 * @param line received from ActorReaderCsv, ProducerReaderCsv
	 * @return Adress Object
	 */
	public static Adress parseLine(String line) {
		Adress address = new Adress();
		String[] column = line.split(",");

		switch (column.length) {
		case 1:
			address.setCity("");
			address.setEtat("");
			address.setCountry(CountryReaderCsv.existOrAdd(column[0]));
			break;
		case 2:
			address.setCity(column[0]);
			address.setEtat("");
			address.setCountry(CountryReaderCsv.existOrAdd(column[1]));
			break;
		case 3:
			address.setCity(column[0]);
			address.setEtat(column[1]);
			address.setCountry(CountryReaderCsv.existOrAdd(column[2]));
			break;
		case 4:
			address.setCity(column[0] + "-" + column[1]);
			address.setEtat(column[2]);
			address.setCountry(CountryReaderCsv.existOrAdd(column[3]));
			break;
		case 5:
			address.setCity(column[0] + "-" + column[1]);
			address.setEtat(column[2] + "-" + column[3]);
			address.setCountry(CountryReaderCsv.existOrAdd(column[4]));
			break;
		default:
			break;
		}
		address.setStreet("");
		return address;
	}

	/**
	 * Static Method used to parse an adress line and create an Adress Object
	 * adress column call a static Method from CountryReaderCsv to parse and
	 * transform "String" in "Adress" Object
	 * 
	 * @param line received from MovieReaderCsv
	 * @return Adress Object
	 */
	public static Adress parseLineReverse(String line) {
		Adress address = new Adress();
		String[] column = line.split(",|-");

		switch (column.length) {
		case 1:
			address.setCountry(CountryReaderCsv.existOrAdd(column[0]));
			address.setCity("");
			address.setEtat("");
			break;
		case 2:
			address.setCountry(CountryReaderCsv.existOrAdd(column[0]));
			address.setEtat(column[1]);
			address.setCity("");
			break;
		case 3:
			address.setCountry(CountryReaderCsv.existOrAdd(column[0]));
			address.setEtat(column[1]);
			address.setCity(column[2]);

			break;
		case 4:
			address.setCountry(CountryReaderCsv.existOrAdd(column[0]));
			address.setEtat(column[1]);
			address.setCity(column[2]);
			address.setStreet(column[3]);
			break;
		case 5:
			address.setCountry(CountryReaderCsv.existOrAdd(column[0]));
			address.setEtat(column[1]);
			address.setCity(column[2]);
			address.setStreet(column[3] + " " + column[4]);
			break;
		default:
			break;
		}
		
		return address;
	}

}
