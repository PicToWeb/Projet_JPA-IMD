package Application;

import service.importFile.FileActor;
import service.importFile.FileCountry;
import service.importFile.FileMovie;
import service.importFile.FileProducer;
import service.importFile.FileRole;

public class WriteData {
	
	public static void main(String[] args) {
//
//		FileCountry.addCsvToDataBase(FileCountry.link("pays.csv"));
//		FileActor.addCsvToDataBase(FileActor.link("acteurs.csv"));
//		FileProducer.addCsvToDataBase(FileProducer.link("realisateurs.csv"));
//
//		FileMovie.addCsvToDataBase(FileMovie.link("films.csv", "film_realisateurs.csv"));
		FileRole.addCsvToDataBase(FileRole.link("roles.csv","castingPrincipal.csv"));
		

	}

}
