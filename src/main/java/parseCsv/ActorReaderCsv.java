package parseCsv;

import java.io.File;

import java.io.IOException;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.io.FileUtils;

import entity.Acteur;
import entity.Lieu;
import utils.Convertir;

public abstract class ActorReaderCsv {

	public static HashMap<String, Acteur> readFileToMap(String urlFile) {

		HashMap<String, Acteur> actorMap = new HashMap<>();
		List<String> linesList = null;

		try {
			File file = new File(urlFile);
			linesList = FileUtils.readLines(file, "UTF-8");
			linesList.remove(0);

			for (String actorData : linesList) {
				Acteur acteur = addActor(actorData);
				actorMap.put(acteur.getId(), acteur);
			}
			
			return actorMap;

		} catch (IOException e) {
			System.out.println(e.getMessage());
			return null;
		}

	}

	public static Acteur addActor(String line) {

		String[] column = line.split(";", -1);
		Acteur acteur = new Acteur();

//		if (column.length == 7) {
//			System.err.println("attention");
//		}

		String id = column[0];
		String identite = column[1];

		LocalDate dateNaissance = null;
		try {
			if (column[2].split(" ").length == 3) {
				dateNaissance = Convertir.stringToDateUS(column[2]);
			}

		} catch (Exception e) {
			e.getMessage();
		}

		Lieu adress = AdresseReaderCsv.stringToLieu(column[3], column[0]);
		String taille = column[4];
		String url = column[5];
		
		acteur.setId(id);
		acteur.setIdentite(identite);
		acteur.setTaille(taille);
		acteur.setUrl(url);
		acteur.setDateNaissance(dateNaissance);
		acteur.setLieu(adress);

		return acteur;
	
	}

}
