package fileReaderCsv;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.io.FileUtils;

import entity.Lieu;
import entity.Realisateur;
import utils.Convertir;

public class RealisateurReaderCsv {

	public static HashMap<String, Realisateur> readFileToMap(String urlFile) {

		HashMap<String, Realisateur> realisateurMap = new HashMap<>();
		List<String> linesList = null;

		try {
			File file = new File(urlFile);
			linesList = FileUtils.readLines(file, "UTF-8");
			linesList.remove(0);

			for (String realisateurData : linesList) {
				Realisateur acteur = addRealisateur(realisateurData);
				realisateurMap.put(acteur.getId(), acteur);
			}
			
			return realisateurMap;

		} catch (IOException e) {
			System.out.println(e.getMessage());
			return null;
		}

	}

	public static Realisateur addRealisateur(String line) {

		String[] column = line.split(";", -1);
		Realisateur realisateur = new Realisateur();

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
		String url = column[4];
		
		realisateur.setId(id);
		realisateur.setIdentite(identite);
		realisateur.setUrl(url);
		realisateur.setDateNaissance(dateNaissance);
		realisateur.setLieu(adress);
		return realisateur;
	
	}

}
