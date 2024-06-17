package parseCsv;

import dao.CountryDao;
import entity.Lieu;
import entity.Pays;
import utils.JpaConnection;

public abstract class AdresseReaderCsv {
	
	public static final CountryDao countryDao = JpaConnection.countryDao();
	
	public static Lieu stringToLieu(String adresse, String id) {
		Lieu lieu = new Lieu();
		String[] colonne = adresse.split(",");
		
		switch(colonne.length) {
		case 1: 
			lieu.setVille("vide");
			lieu.setEtat("vide");
			lieu.setPays(countryExistOrAdded(colonne[0]));
			break;
		case 2:
			lieu.setVille(colonne[0]);
			lieu.setEtat("vide");
			lieu.setPays(countryExistOrAdded(colonne[1]));
			break;
		case 3 :
			lieu.setVille(colonne[0]);
			lieu.setEtat(colonne[1]);
			lieu.setPays(countryExistOrAdded(colonne[2]));
			break;
		case 4 :
			lieu.setVille(colonne[0] + "-" +colonne[1]);
			lieu.setEtat(colonne[2]);
			lieu.setPays(countryExistOrAdded(colonne[3]));
			break;
		case 5 : 
			lieu.setVille(colonne[0] + "-" +colonne[1]);
			lieu.setEtat(colonne[2] + "-" +colonne[3]);
			lieu.setPays(countryExistOrAdded(colonne[4]));	
			break;
		default:
		
			break;
		}
	
		return lieu;
	}
	
	public static Lieu stringToLieuMovie(String adresse, String id) {
		Lieu lieu = new Lieu();
		String[] colonne = adresse.split(",|-");
		
		
		switch(colonne.length) {
		case 1: 
			lieu.setPays(countryExistOrAdded(colonne[0]));
			lieu.setEtat("vide");
			lieu.setVille("vide");
			lieu.setRue("vide");
			break;
		case 2:
			lieu.setPays(countryExistOrAdded(colonne[0]));
			lieu.setEtat(colonne[1]);
			lieu.setVille("vide");
			lieu.setRue("vide");
			break;
		case 3 :
			lieu.setPays(countryExistOrAdded(colonne[0]));
			lieu.setEtat(colonne[1]);
			lieu.setVille(colonne[2]);
			lieu.setRue("vide");
			
			break;
		case 4 :
			lieu.setPays(countryExistOrAdded(colonne[0]));
			lieu.setEtat(colonne[1]);
			lieu.setVille(colonne[2]);
			lieu.setRue(colonne[3]);
			break;
		case 5 : 
			lieu.setPays(countryExistOrAdded(colonne[0]));
			lieu.setEtat(colonne[1]);
			lieu.setVille(colonne[2]);
			lieu.setRue(colonne[3] + " " + colonne[4]);
			break;
		default:
		
			break;
		}
	
		return lieu;
	}
	

	
	public static Pays countryExistOrAdded(String pays) {
		
		  if (pays == null || pays.length()>60) {
		        return new Pays("", "");
		    }

		    String usa = "USA";
		    String uk = "UK";
		    String cleanedPays = pays.replaceAll("\\[.*?\\]", "").trim();

		    if (cleanedPays.equals(usa)) {
		        cleanedPays = "United States";
		    } else if (cleanedPays.equals(uk)) {
		        cleanedPays = "United Kingdom";
		    }

		    Pays existingCountry = countryDao.findByName(cleanedPays);
		    if (existingCountry == null) {
		        existingCountry = new Pays(cleanedPays, "");
		        countryDao.insert(existingCountry);
		    }

		    return existingCountry;
	}
	

}
