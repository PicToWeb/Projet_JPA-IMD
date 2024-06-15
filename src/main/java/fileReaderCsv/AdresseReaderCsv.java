package fileReaderCsv;

import Controller.PaysController;
import entity.Lieu;

public class AdresseReaderCsv {
	
	public static Lieu stringToLieu(String adresse, String id) {
		Lieu lieu = new Lieu();
		String[] colonne = adresse.split(",");

		if (colonne.length == 1) {
			lieu.setPays(PaysController.checkIfCountryExist(colonne[0]));
			
		} else if (colonne.length == 2) {
			lieu.setVille(colonne[0]);
			lieu.setPays(PaysController.checkIfCountryExist(colonne[1]));
			
		} else if (colonne.length == 3) {
			lieu.setVille(colonne[0]);
			lieu.setEtat(colonne[1]);
			lieu.setPays(PaysController.checkIfCountryExist(colonne[2]));
			
		} else if (colonne.length == 4) {
			lieu.setVille(colonne[0] + "-" +colonne[1]);
			lieu.setEtat(colonne[2]);
			lieu.setPays(PaysController.checkIfCountryExist(colonne[3]));

		} else {
			lieu.setVille(colonne[0] + "-" +colonne[1]);
			lieu.setEtat(colonne[2] + "-" +colonne[3]);
			lieu.setPays(PaysController.checkIfCountryExist(colonne[4]));
		}
	
		return lieu;
	}
	

}
