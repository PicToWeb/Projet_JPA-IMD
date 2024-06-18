package parseCsv;

import entity.Adress;

/**
 * Abstract class used to parse adress data from acteurs.csv & realisateurs.csv
 * & films.csv and return Adress Object
 **/
public abstract class AdressReaderCsv {


	/**
	 * Static Method used to parse an adress line and create an Adress Object
	 * adress column call a static Method from CountryReaderCsv to parse and
	 * transform "String" in "Adress" Object
	 * 
	 * @param adressString received from ActorReaderCsv, ProducerReaderCsv
	 * @return Adress Object
	 */
	public static Adress stringToAdress(String adressString) {
		Adress adress = new Adress();
		String[] colonne = adressString.split(",");

		switch (colonne.length) {
		case 1:
			adress.setCity("");
			adress.setEtat("");
			adress.setCountry(CountryReaderCsv.countryExistOrAdded(colonne[0]));
			break;
		case 2:
			adress.setCity(colonne[0]);
			adress.setEtat("");
			adress.setCountry(CountryReaderCsv.countryExistOrAdded(colonne[1]));
			break;
		case 3:
			adress.setCity(colonne[0]);
			adress.setEtat(colonne[1]);
			adress.setCountry(CountryReaderCsv.countryExistOrAdded(colonne[2]));
			break;
		case 4:
			adress.setCity(colonne[0] + "-" + colonne[1]);
			adress.setEtat(colonne[2]);
			adress.setCountry(CountryReaderCsv.countryExistOrAdded(colonne[3]));
			break;
		case 5:
			adress.setCity(colonne[0] + "-" + colonne[1]);
			adress.setEtat(colonne[2] + "-" + colonne[3]);
			adress.setCountry(CountryReaderCsv.countryExistOrAdded(colonne[4]));
			break;
		default:

			break;
		}

		return adress;
	}

	/**
	 * Static Method used to parse an adress line and create an Adress Object
	 * adress column call a static Method from CountryReaderCsv to parse and
	 * transform "String" in "Adress" Object
	 * 
	 * @param adressString received from MovieReaderCsv
	 * @return Adress Object
	 */
	public static Adress stringToLieuMovie(String adressString) {
		Adress adress = new Adress();
		String[] column = adressString.split(",|-");

		switch (column.length) {
		case 1:
			adress.setCountry(CountryReaderCsv.countryExistOrAdded(column[0]));
			adress.setEtat("");
			adress.setCity("");
			adress.setStreet("");
			break;
		case 2:
			adress.setCountry(CountryReaderCsv.countryExistOrAdded(column[0]));
			adress.setEtat(column[1]);
			adress.setCity("");
			adress.setStreet("");
			break;
		case 3:
			adress.setCountry(CountryReaderCsv.countryExistOrAdded(column[0]));
			adress.setEtat(column[1]);
			adress.setCity(column[2]);
			adress.setStreet("");

			break;
		case 4:
			adress.setCountry(CountryReaderCsv.countryExistOrAdded(column[0]));
			adress.setEtat(column[1]);
			adress.setCity(column[2]);
			adress.setStreet(column[3]);
			break;
		case 5:
			adress.setCountry(CountryReaderCsv.countryExistOrAdded(column[0]));
			adress.setEtat(column[1]);
			adress.setCity(column[2]);
			adress.setStreet(column[3] + " " + column[4]);
			break;
		default:
			adress.setEtat("");
			adress.setCity("");
			adress.setStreet("");
			break;
		}

		return adress;
	}

}
