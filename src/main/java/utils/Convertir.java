package utils;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Locale;

import entity.Lieu;
import entity.Pays;
import fileReaderCsv.CountryReaderCsv2;


public class Convertir {

	public static LocalDate stringToDateUS(String string) {

		String chaine = string.trim();
		LocalDate date = LocalDate.parse(chaine, DateTimeFormatter.ofPattern("MMMM d yyyy", Locale.US));

		return date;
	}
	

	public static HashMap<String, Lieu> stringToLieuMap(String adresse, String id) {

		HashMap<String, Lieu> adresseMap = new HashMap<>();
		
		Lieu lieu = new Lieu();

		String[] colonne = adresse.split(",");


		if (colonne.length == 1) {
			//lieu.setPays(CountryReaderCsv.verifPays(colonne[0]));
			lieu.setPays(CountryReaderCsv2.verifPays(colonne[0]));
			//lieu.setPays(new Pays(colonne[0],""));
			adresseMap.put(id, lieu);
			
		} else if (colonne.length == 2) {
			lieu.setVille(colonne[0]);
			lieu.setPays(CountryReaderCsv2.verifPays(colonne[1]));
			//lieu.setPays(new Pays(colonne[1],""));
			adresseMap.put(id, lieu);
			
		} else if (colonne.length == 3) {
			lieu.setVille(colonne[0]);
			lieu.setEtat(colonne[1]);
			lieu.setPays(CountryReaderCsv2.verifPays(colonne[2]));
			//lieu.setPays(new Pays(colonne[2],""));
			adresseMap.put(id, lieu);
			
		} else if (colonne.length == 4) {
			lieu.setVille(colonne[0] + "-" +colonne[1]);
			lieu.setEtat(colonne[2]);
//			lieu.setPays(CountryReaderCsv.verifPays(colonne[3]));
			lieu.setPays(CountryReaderCsv2.verifPays(colonne[3]));
		//	lieu.setPays(new Pays(colonne[3],""));
			adresseMap.put(id, lieu);
		} else {
			lieu.setVille(colonne[0] + "-" +colonne[1]);
			lieu.setEtat(colonne[2] + "-" +colonne[3]);
//			lieu.setPays(CountryReaderCsv.verifPays(colonne[4]));
			lieu.setPays(CountryReaderCsv2.verifPays(colonne[4]));
			//lieu.setPays(new Pays(colonne[4],""));
			adresseMap.put(id, lieu);
		}

		//System.out.println(adresseMap.toString());
		return adresseMap;
	}

}
