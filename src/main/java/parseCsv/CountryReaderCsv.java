package parseCsv;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.io.FileUtils;
import entity.Pays;

public abstract class CountryReaderCsv {
	
	
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

	

}
