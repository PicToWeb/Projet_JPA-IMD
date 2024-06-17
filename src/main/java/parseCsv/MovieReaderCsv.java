package parseCsv;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

import org.apache.commons.io.FileUtils;

import dao.LieuDao;
import entity.Film;
import entity.Genre;
import entity.Langue;
import entity.Lieu;
import entity.Pays;
import utils.JpaConnection;

public abstract class MovieReaderCsv {

	public static final LieuDao lieuDao = JpaConnection.lieuDao();
	
	public static HashMap<String, Film> readFileToMap(String urlFile) {

		HashMap<String, Film> movieMap = new HashMap<>();
		List<String> linesList = null;

		try {
			File file = new File(urlFile);
			linesList = FileUtils.readLines(file, "UTF-8");
			linesList.remove(0);

			for (String movieData : linesList) {
				Film film = parseStringBeforeAdd(movieData);
				movieMap.put(film.getId(), film);
			}
			
			return movieMap;

		} catch (IOException e) {
			System.out.println(e.getMessage());
			return null;
		}

	}

	public static Film parseStringBeforeAdd(String line) {

		String[] column = line.split(";", -1);
		

		//if (column.length > 9) return new Film();


		String id = column[0];
		String name = column[1];
		int year = Integer.parseInt(column[2].substring(column[2].trim().length() - 4));
		
		Double rating = 0.0;
		if(!column[3].isEmpty()) {
		 rating = Double.parseDouble(column[3].replaceAll(",","."));
		}
	
		String url = column[4];
		Lieu filmAdress = AdresseReaderCsv.stringToLieuMovie(column[5], column[0]);
		
		
		String resume = column[8];
		Langue langue = LangueReaderCsv.langueExistOrAdded(column[7]);
		Pays pays = AdresseReaderCsv.countryExistOrAdded(column[9]);
		Film movie = new Film(id,name,year,rating,url,resume);
		
		Set<Genre> movieGenres= GenreReaderCsv.genreExistOrAdded(column[6]);
		if(!movieGenres.isEmpty()) {
			movie.setGenres(movieGenres);	
		}
		
		movie.setLieu(filmAdress);
		movie.setLangue(langue);
		movie.setPays(pays);
		
		return movie;
	
	}

}
