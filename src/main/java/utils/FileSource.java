package utils;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.io.FileUtils;

import entity.Pays;

public class FileSource {
	
	public static String nom(String source) {
		
		String filePath = ClassLoader.getSystemClassLoader().getResource(source).getFile();
		
		return filePath;
	}
	
	public static Pays ajoutLigne(String ligne) {

		String[] colonne = ligne.split(";", -1);
		
		if (colonne.length == 3) {
			System.err.println("attention");
		}
		
		String nom = colonne[0];
		String url = colonne[1];
		
		Pays paysCreer = new Pays(nom,url);
		
		return paysCreer;
	}
	
public static HashMap<T, N> lire(String cheminFichier) {
		
		HashMap<T,N> paysMap = new HashMap<>();
		
		List<String> lignes = null;
		
		try {
			File file = new File(cheminFichier);
			lignes = FileUtils.readLines(file, "UTF-8");

			lignes.remove(0);

			for (String ligne : lignes) {

				Pays paysLu = ajoutLigne(ligne);
				paysMap.put(paysLu.getNom(),paysLu);
 
			}
			System.out.println(paysMap.toString());
			return paysMap;

		} catch (IOException e) {
			System.out.println(e.getMessage());
			return null;
		}
		
	}

}
