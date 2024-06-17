package Controller;

import dao.CountryDao;
import entity.Pays;
import utils.JpaConnection;

public abstract class PaysController {
//	public static final CountryDao country = JpaConnection.countryDao();
//	
//	public static Pays checkIfCountryExist(String pays) {
//		 
////		if(pays==null) return new Pays("","");
////		
////		String usa = "USA";
////		String uk = "UK";
////		
////		if (pays.replaceAll("\\[.*?\\]","").trim() .equals(usa)) {
////			pays = "United States";
////		}else if(pays.replaceAll("\\[.*?\\]","").trim().equals(uk))
////		{
////			pays = "United Kingdom";
////		}
////		if(country.findByName(pays.replaceAll("\\[.*?\\]","").trim())==null)
////		  country.insert(new Pays (pays.replaceAll("\\[.*?\\]","").trim(),""));
////		
////		
////		return country.findByName(pays.replaceAll("\\[.*?\\]","").trim());
//		
//		
//		  if (pays == null) {
//		        return new Pays("", "");
//		    }
//
//		    String usa = "USA";
//		    String uk = "UK";
//		    String cleanedPays = pays.replaceAll("\\[.*?\\]", "").trim();
//
//		    if (cleanedPays.equals(usa)) {
//		        cleanedPays = "United States";
//		    } else if (cleanedPays.equals(uk)) {
//		        cleanedPays = "United Kingdom";
//		    }
//
//		    Pays existingCountry = country.findByName(cleanedPays);
//		    if (existingCountry == null) {
//		        existingCountry = new Pays(cleanedPays, "");
//		        country.insert(existingCountry);
//		    }
//
//		    return existingCountry;
//		
//	}

}
