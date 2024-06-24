package dao;

import java.util.HashMap;
import java.util.List;

import entity.Address;
import entity.Country;
import entity.Movie;
import jakarta.persistence.TypedQuery;
import service.connection.DaoLink;
import service.connection.JpaLink;

/**
 * 
 */
public class MovieDao implements DaoInterface<Movie> {

	/** COUNTRY_DAO */
	public static final CountryDao COUNTRY_DAO = DaoLink.countryDao();
	/** ADDRESS_DAO */
	public static final AddressDao ADDRESS_DAO = DaoLink.addressDao();
	/** MOVIE_GENRE_DAO */
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
			if (!exist(f.getId())) {

				Address address = ADDRESS_DAO.existOrAdd(f.getAdress());
				Country country = COUNTRY_DAO.existOrAdd(f.getCountry());

				Movie movie = new Movie(f.getId(), f.getNam(), f.getYear(), f.getRating(), f.getUrl(), f.getResume());

				movie.setLanguage(f.getLanguage());
				movie.setCountry(country);
				movie.setAdress(address);
				movie.setProducers(f.getProducers());
				movie.setGenres(f.getGenres());

				insert(movie);

			}
		}
	}

	public boolean exist(String idMovie) {
		return movieMap.values().stream().anyMatch(r -> r.getId().equals(idMovie));
	}

	public Movie findMovieById(String movieId) {
		return movieMap.values().stream().filter(a -> a.getId().equals(movieId)).findFirst().orElse(null);
	}

	public HashMap<String, Movie> findAll() {
		HashMap<String, Movie> movieMap = new HashMap<>();

		TypedQuery<Movie> query = JpaLink.getEntityManager().createQuery(
				"SELECT m FROM Movie m LEFT JOIN FETCH m.address a LEFT JOIN FETCH a.country LEFT JOIN FETCH m.country",
				Movie.class);
		List<Movie> movies = query.getResultList();

		for (Movie a : movies) {
			movieMap.put(a.getId(), a);
		}
		return movieMap;
	}

	public List<Movie> findMovieOfActor(String actorSearched) {
		TypedQuery<Movie> query = JpaLink.getEntityManager().createQuery(
				"SELECT m FROM Movie m JOIN m.roles r JOIN r.actor a WHERE a.identity = :actorName", Movie.class);
		List<Movie> movies = query.setParameter("actorName", actorSearched).getResultList();
		return movies;
	}

	public List<Movie> findMovieBetweenDate(int year1, int year2) {
		TypedQuery<Movie> query = JpaLink.getEntityManager()
				.createQuery("SELECT m FROM Movie m WHERE m.year BETWEEN :year1 AND :year2", Movie.class);
		query.setParameter("year1", year1);
		query.setParameter("year2", year2);
		List<Movie> movies = query.getResultList();
		return movies;
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
