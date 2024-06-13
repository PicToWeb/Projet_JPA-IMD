package fileReader;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.io.FileUtils;

import entity.Acteur;

public class ActeurLecteurFile {

	public static HashMap<String, Acteur> lire(String cheminFichier) {

		HashMap<String, Acteur> acteurMap = new HashMap<>();

		List<String> lignes = null;
		
		try {
			File file = new File(cheminFichier);
			lignes = FileUtils.readLines(file, "UTF-8");

			lignes.remove(0);

			for (String ligne : lignes) {

				ajoutLigne(ligne);
				//acteur.put(acteur.getId(),acteur);

			}

			return acteurMap;

		} catch (IOException e) {
			System.out.println(e.getMessage());
			return null;
		}

	}

	public static void ajoutLigne(String ligne) {

		String[] colonne = ligne.split(";", -1);

		if (colonne.length == 7) {
			System.err.println("attention");
		}
		String id = colonne[0];
		String Identite = colonne[1];
		String dateNaissanceString = colonne[2];
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMMM-dd-YYYY");
        LocalDate dateNaissance = LocalDate.parse(dateNaissanceString, formatter);
        
		String nutriGrade = colonne[3];
		String[] ingredients = colonne[4].split(",");

		// String allergenes = morceaux[28];
		// int ingredientTotal = Integer.parseInt(ingredients.replace(" ", "").trim());



		// recensement.getProduits().add(prod);
	}

}
