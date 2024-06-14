package fileReaderCsv;

import java.io.File;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.io.FileUtils;

import dao.CountryDao;
import entity.Pays;
import utils.JpaConnection;

public class CountryReaderCsv2 {
	
	public static final CountryDao country = JpaConnection.countryDao();
	
	public static List<Pays> readFileToList(String urlFile) {

		List<Pays> countryList = new ArrayList<>();

		try {
			File file = new File(urlFile);
			List<String> linesList = FileUtils.readLines(file, "UTF-8");
			linesList.remove(0);

			for (String line : linesList) {
				Pays p = new Pays();
				String[] column = line.split(";");
				if (column.length > 2) {
					return countryList;
				}
				p.setNom(column[0].trim());
				p.setUrl(column[1]);
				countryList.add(p);

			}

			return countryList;

		} catch (IOException e) {
			System.out.println(e.getMessage());
			return null;
		}

	}

	public static Pays verifPays(String pays) {
		 
		if(pays==null) return new Pays("","");
		
		String usa = "USA";
		String uk = "UK";
		
		if (pays.trim() .equals(usa)) {
			pays = "United States";
		}else if(pays.trim().equals(uk))
		{
			pays = "United Kingdom";
		}
		if(country.findByName(pays.trim())==null)
		  country.insert(new Pays (pays.trim(),""));
		
		
		return country.findByName(pays.trim());
	}

}
