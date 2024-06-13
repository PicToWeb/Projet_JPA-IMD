package fileReader;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.io.FileUtils;

import entity.Pays;

public class PaysReader {

	public static HashMap<String, Pays> paysMap = new HashMap<>();

	public static HashMap<String, Pays> lire(String cheminFichier) {

		List<String> lignes = null;

		try {
			File file = new File(cheminFichier);
			lignes = FileUtils.readLines(file, "UTF-8");

			lignes.remove(0);

			for (String ligne : lignes) {

				Pays paysLu = ajoutLigne(ligne);
				paysMap.put(paysLu.getNom(), paysLu);

			}
			System.out.println(paysMap.toString());
			return paysMap;

		} catch (IOException e) {
			System.out.println(e.getMessage());
			return null;
		}

	}

	public static Pays ajoutLigne(String ligne) {

		String[] colonne = ligne.split(";", -1);

		if (colonne.length == 3) {
			System.err.println("attention");
		}

		String nom = colonne[0].trim();
		String url = colonne[1];

		Pays paysCreer = new Pays(nom, url);

		return paysCreer;
	}
	

	public static Pays verifPays(String pays) {
		Pays paysTrouve = new Pays();

		String usa = "USA";
		String uk = "UK";
		
		if (pays.trim() .equals(usa)) {
			pays = "United States";
		}else if(pays.trim().equals(uk))
		{
			pays = "United Kingdom";
		}
		
		for (String key : paysMap.keySet()) {
			if (key.trim().equals(pays.trim())) {
				paysTrouve = paysMap.get(key);
			}
		}

		return paysTrouve;
	}

}
