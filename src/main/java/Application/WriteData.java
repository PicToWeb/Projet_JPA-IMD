package Application;

import service.importFile.FileActor;

import service.importFile.FileCountry;
import service.importFile.FileMovie;
import service.importFile.FileProducer;
import service.importFile.FileRole;

/**
 * WriteData is an entry point class for adding data from CSV files to the database.
 * It contains methods to process CSV files related to countries, actors, producers,
 * movies, and roles.
 *
 * @author Antoine Picron
 */
public class WriteData {
	
	/**
     * The main method that adds data from CSV files to the database.
     *
     * @param args Command line arguments (not used in this case)
     */
	public static void main(String[] args) {

		FileCountry.addCsvToDataBase(FileCountry.link("pays.csv"));
		FileActor.addCsvToDataBase(FileActor.link("acteurs.csv"));
		FileProducer.addCsvToDataBase(FileProducer.link("realisateurs.csv"));
		FileMovie.addCsvToDataBase(FileMovie.link("films.csv", "film_realisateurs.csv"));
		FileRole.addCsvToDataBase(FileRole.link("roles.csv","castingPrincipal.csv"));
			
	}

}
