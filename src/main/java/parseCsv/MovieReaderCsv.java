package parseCsv;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.apache.commons.io.FileUtils;

import dao.LieuDao;
import dao.RealisateurDao;
import entity.Film;
import entity.Genre;
import entity.Langue;
import entity.Lieu;
import entity.Pays;
import entity.Realisateur;
import utils.FileSource;
import utils.JpaConnection;

public abstract class MovieReaderCsv {
	public static final String fileLangue = "UTF-8";
	public static final LieuDao lieuDao = JpaConnection.lieuDao();
	public static final RealisateurDao realisateurDao = JpaConnection.realisateurDao();

	public static HashMap<String, Film> readFileToMap(String urlFile) {

		HashMap<String, Film> movieMap = new HashMap<>();
		List<String> linesProducerList = null;

		HashMap<String, String> producerMoviesMap = new HashMap<>();
		List<String> linesMovieList = null;
		String producerMovie = FileSource.nom("film_realisateurs.csv");

		try {

			File fileProdMov = new File(producerMovie);
			linesProducerList = FileUtils.readLines(fileProdMov, fileLangue);
			linesProducerList.remove(0);
			
			for (String prodMovieData : linesProducerList) {
				String[] column = prodMovieData.split(";");
				producerMoviesMap.put(column[0], column[1]);
			}

			File file = new File(urlFile);
			linesMovieList = FileUtils.readLines(file, fileLangue);
			linesMovieList.remove(0);

			for (String movieData : linesMovieList) {
				Film film = parseStringBeforeAdd(movieData);
				Realisateur realisateur = realisateurDao.findById(producerMoviesMap.values().iterator().next());

				Iterator<String> keyReal = producerMoviesMap.keySet().iterator();
				while(keyReal.hasNext()) {
					String cleJoin = keyReal.next();
					
					if(film.getId().equals(cleJoin) && realisateur != null) {
					
						film.addProducer(realisateur);
					}
				}
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

		// if (column.length > 9) return new Film();

		String id = column[0];
		String name = column[1];
		int year = Integer.parseInt(column[2].substring(column[2].trim().length() - 4));

		Double rating = 0.0;
		if (!column[3].isEmpty()) {
			rating = Double.parseDouble(column[3].replaceAll(",", "."));
		}
		String url = column[4];
		Lieu filmAdress = AdresseReaderCsv.stringToLieuMovie(column[5], column[0]);
		Langue langue = LangueReaderCsv.langueExistOrAdded(column[7]);
		String resume = column[8];
		Pays pays = AdresseReaderCsv.countryExistOrAdded(column[9]);

		Film movie = new Film(id, name, year, rating, url, resume);

		Set<Genre> movieGenres = GenreReaderCsv.genreExistOrAdded(column[6]);
		if (!movieGenres.isEmpty()) {
			movie.setGenres(movieGenres);
		}

		movie.setLieu(filmAdress);
		movie.setLangue(langue);
		movie.setPays(pays);

		return movie;

	}

}
