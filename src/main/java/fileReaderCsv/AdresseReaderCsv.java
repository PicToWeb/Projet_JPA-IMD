package fileReaderCsv;

import Controller.LieuController;
import Controller.PaysController;
import entity.Lieu;

public class AdresseReaderCsv {
	
	public static Lieu stringToLieu(String adresse, String id) {
		Lieu lieu = new Lieu();
		String[] colonne = adresse.split(",");
		
		switch(colonne.length) {
		case 1: 
			lieu.setVille("vide");
			lieu.setEtat("vide");
			lieu.setPays(PaysController.checkIfCountryExist(colonne[0]));
			break;
		case 2:
			lieu.setVille(colonne[0]);
			lieu.setEtat("vide");
			lieu.setPays(PaysController.checkIfCountryExist(colonne[1]));
			break;
		case 3 :
			lieu.setVille(colonne[0]);
			lieu.setEtat(colonne[1]);
			lieu.setPays(PaysController.checkIfCountryExist(colonne[2]));
			break;
		case 4 :
			lieu.setVille(colonne[0] + "-" +colonne[1]);
			lieu.setEtat(colonne[2]);
			lieu.setPays(PaysController.checkIfCountryExist(colonne[3]));
			break;
		case 5 : 
			lieu.setVille(colonne[0] + "-" +colonne[1]);
			lieu.setEtat(colonne[2] + "-" +colonne[3]);
			lieu.setPays(PaysController.checkIfCountryExist(colonne[4]));	
			break;
		default:
		
			break;
		}
	
//		return LieuController.checkIfLieuExist(lieu);
		return lieu;
	}
	

}