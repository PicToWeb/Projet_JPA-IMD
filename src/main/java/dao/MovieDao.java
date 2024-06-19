package dao;

import java.util.HashMap;
import java.util.List;

import entity.Movie;
import jakarta.persistence.TypedQuery;
import service.connection.DaoLink;
import service.connection.JpaLink;

public class MovieDao implements DaoInterface<Movie> {

	public static final CountryDao countryDao = DaoLink.countryDao();
	public static final AdressDao adressDao = DaoLink.adressDao();
	public static final MovieGenreDao movieGenreDao = DaoLink.movieGenreDao();

	HashMap<String, Movie> movieMap = new HashMap<>();

	/**
	 * Constructor
	 * 
	 * @param lieuMap
	 */
	public MovieDao() {
		this.movieMap = findAll();

	}

	public void splitInsert(HashMap<String, Movie> movieMap) {

		for (Movie f : movieMap.values()) {
			if (!movieExist(f.getId())) {

				//tester modif
//				if (!lieuDao.lieuExist(f.getLieu())) {
//					lieuDao.insert(f.getLieu());
//				}
				adressDao.lieuExistOrAdded(f.getAdress());
				countryDao.countryExistOrAdded(f.getCountry());
				
//				countryDao.countryExistOrAdded(f.getLieu().getPays());
//				lieuDao.lieuExistOrAdded(f.getLieu());
				
				//tester modif
//				if (!countryDao.countryExist2(f.getPays())) {
//					countryDao.insert(f.getPays());
//				}

				Movie movie = new Movie(f.getId(), f.getNam(), f.getYear(), f.getRating(), f.getUrl(), f.getResume());
				movie.setLanguage(f.getLanguage());
				movie.setCountry(f.getCountry());
				movie.setAdress(adressDao.findByName(f.getAdress()));
				
				movie.setProducers(f.getProducers());
				movie.setGenres(f.getGenres());

				try {
					insert(movie);

				} catch (Exception e) {
					e.getMessage();
					continue;
				}

			}
		}
	}

	
	public boolean movieExist(String idMovie) {
		return movieMap.values().stream().anyMatch(r -> r.getId().equals(idMovie));
	}
	
	public Movie findMovieById(String movieId) {
		return movieMap.values().stream().filter(a->a.getId().equals(movieId)).findFirst().orElse(null);
	}

	public HashMap<String, Movie> findAll() {

		HashMap<String, Movie> movieMap = new HashMap<>();

		// Utilisez une requête JPQL pour récupérer les réalisateurs depuis la base de
		// données
		TypedQuery<Movie> query = JpaLink.getEntityManager().createQuery(
				"SELECT m FROM Movie m LEFT JOIN FETCH m.adress a LEFT JOIN FETCH a.country LEFT JOIN FETCH m.country",
				Movie.class);
		List<Movie> movies = query.getResultList();
		
		// Remplissez le HashMap avec les réalisateurs
		for (Movie a : movies) {
			movieMap.put(a.getId(), a);
		}

		return movieMap;
	}

	@Override
	public void insert(Movie movie) {

		JpaLink.persist(movie);
		movieMap.put(movie.getId(), movie);

	}

	@Override
	public void delete(Movie movie) {

	}

}
