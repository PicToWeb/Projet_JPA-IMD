package fileReader;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.io.FileUtils;

import entity.Acteur;
import entity.Lieu;
import utils.Convertir;

public class ActeurLecteurFile {

	public static HashMap<String, Acteur> lire(String cheminFichier) {

		HashMap<String, Acteur> acteurMap = new HashMap<>();

		List<String> lignes = null;

		try {
			File file = new File(cheminFichier);
			lignes = FileUtils.readLines(file, "UTF-8");

			lignes.remove(0);

			for (String ligne : lignes) {

				Acteur acteur = ajoutLigne(ligne);
				acteurMap.put(acteur.getId(),acteur);

			}

			return acteurMap;

		} catch (IOException e) {
			System.out.println(e.getMessage());
			return null;
		}

	}

	public static Acteur ajoutLigne(String ligne) {

		String[] colonne = ligne.split(";", -1);
		Acteur acteur = new Acteur();
		
		if (colonne.length == 7) {
			System.err.println("attention");
		}
		String id = colonne[0];
		String identite = colonne[1];
		LocalDate dateNaissance = null;
		try {
			if (colonne[2].split(" ").length == 3) {
				dateNaissance = Convertir.stringToDateUS(colonne[2]);
				
			}
			
		}catch(Exception e) {
			e.getMessage();
		}
		HashMap<String,Lieu> lieuNaissance = Convertir.stringToLieuMap(colonne[3], colonne[0]);
		
		Lieu value = new Lieu();
		for (String key : lieuNaissance.keySet()) {
			if(id.equals(key)) {
				value = lieuNaissance.get(key);
			}
		}
		String taille = colonne[4];
		String url = colonne[5];
		acteur.setId(id);
		acteur.setIdentite(identite);
		acteur.setTaille(taille);
		acteur.setUrl(url);
		acteur.setDateNaissance(dateNaissance);
		acteur.setLieu(value);
//		String nutriGrade = colonne[3];
//		String[] ingredients = colonne[4].split(",");

		// String allergenes = morceaux[28];
		// int ingredientTotal = Integer.parseInt(ingredients.replace(" ", "").trim());

		// recensement.getProduits().add(prod);
		System.out.println(acteur.toString());
		return acteur;
	}

}
