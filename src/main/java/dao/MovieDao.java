package dao;

import java.util.HashMap;

import java.util.List;
import entity.Movie;
import jakarta.persistence.TypedQuery;
import service.connection.DaoLink;
import service.connection.JpaLink;

public class MovieDao implements DaoInterface<Movie> {

	public static final CountryDao COUNTRY_DAO = DaoLink.countryDao();
	public static final AddressDao ADDRESS_DAO = DaoLink.addressDao();
	public static final MovieGenreDao MOVIE_GENRE_DAO = DaoLink.movieGenreDao();

	HashMap<String, Movie> movieMap = new HashMap<>();

	/**
	 * Constructor
	 * 
	 * @param lieuMap
	 */
	public MovieDao() {
		this.movieMap = findAll();

	}

	public void allInsert(HashMap<String, Movie> movieMap) {

		for (Movie f : movieMap.values()) {
			if (!movieExist(f.getId())) {

				ADDRESS_DAO.existOrAdd(f.getAdress());
				COUNTRY_DAO.existOrAdd(f.getCountry());

				Movie movie = new Movie(f.getId(), f.getNam(), f.getYear(), f.getRating(), f.getUrl(), f.getResume());

				movie.setLanguage(f.getLanguage());
				movie.setCountry(f.getCountry());
				movie.setAdress(ADDRESS_DAO.findByName(f.getAdress()));
				movie.setProducers(f.getProducers());
				movie.setGenres(f.getGenres());

				insert(movie);

			}
		}
	}

	public boolean movieExist(String idMovie) {
		return movieMap.values().stream().anyMatch(r -> r.getId().equals(idMovie));
	}

	public Movie findMovieById(String movieId) {
		return movieMap.values().stream().filter(a -> a.getId().equals(movieId)).findFirst().orElse(null);
	}

	public HashMap<String, Movie> findAll() {
		HashMap<String, Movie> movieMap = new HashMap<>();

		TypedQuery<Movie> query = JpaLink.getEntityManager().createQuery(
				"SELECT m FROM Movie m LEFT JOIN FETCH m.adress a LEFT JOIN FETCH a.country LEFT JOIN FETCH m.country",
				Movie.class);
		List<Movie> movies = query.getResultList();

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
