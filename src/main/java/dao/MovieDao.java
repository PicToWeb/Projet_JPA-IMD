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
 * MovieDao is a data access class that interacts with the movie-related data in the database.
 * It provides methods for inserting, retrieving, and searching movie information.
 * The class uses DAOs (Data Access Objects) for related entities such as countries, addresses, and genres.
 *
 * @author Antoine Picron
 */
public class MovieDao implements DaoInterface<Movie> {

	/** COUNTRY_DAO */
	public static final CountryDao COUNTRY_DAO = DaoLink.countryDao();
	/** ADDRESS_DAO */
	public static final AddressDao ADDRESS_DAO = DaoLink.addressDao();
	/** MOVIE_GENRE_DAO */
	public static final MovieGenreDao MOVIE_GENRE_DAO = DaoLink.movieGenreDao();

	 /** HashMap to store movie data */
	HashMap<String, Movie> movieMap = new HashMap<>();

	/**
     * Constructor initializes the movie map by fetching data from the database.
     */
	public MovieDao() {
		this.movieMap = findAll();

	}
	
	 /**
     * Inserts movie data into the database.
     *
     * @param movieMap A map containing movie data
     */
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

	 /**
     * Retrieves all movies from the database.
     *
     * @return A map of movie data
     */
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

	/**
     * Finds movies associated with a specific actor.
     *
     * @param actorSearched The name of the actor
     * @return A list of movies
     */
	public List<Movie> findMovieOfActor(String actorSearched) {
		TypedQuery<Movie> query = JpaLink.getEntityManager().createQuery(
				"SELECT m FROM Movie m JOIN m.roles r JOIN r.actor a WHERE a.id = :actorId", Movie.class);
		List<Movie> movies = query.setParameter("actorId", actorSearched).getResultList();
		return movies;
	}

	/**
	 * Retrieves a list of movies whose release year falls between {@code year1} and {@code year2}.
	 *
	 * @param year1 The starting year.
	 * @param year2 The ending year.
	 * @return The list of movies.
	 */
	public List<Movie> findMovieBetweenYear(int year1, int year2) {
		TypedQuery<Movie> query = JpaLink.getEntityManager()
				.createQuery("SELECT m FROM Movie m WHERE m.year BETWEEN :year1 AND :year2", Movie.class);
		query.setParameter("year1", year1);
		query.setParameter("year2", year2);
		List<Movie> movies = query.getResultList();
		return movies;
	}

	/**
	 * Finds a movie that both {@code actor1} and {@code actor2} have acted in.
	 *
	 * @param actor1 The first actor's ID.
	 * @param actor2 The second actor's ID.
	 * @return The common movie.
	 */
	public Movie findCommunMoviebyTwoActors(String actor1, String actor2){
		TypedQuery<Movie> query = JpaLink.getEntityManager()
				.createQuery("SELECT DISTINCT m FROM Movie m "
		                + "JOIN m.roles r1 JOIN r1.actor a1 "
		                + "JOIN m.roles r2 JOIN r2.actor a2 "
		                + "WHERE a1.id = :actor1 AND a2.id = :actor2", Movie.class);
		query.setParameter("actor1", actor1);
		query.setParameter("actor2", actor2);
		Movie movie = query.getSingleResult();
		return movie;
	}
	
	/**
	 * Retrieves movies acted in by a specific {@code actor} between {@code year1} and {@code year2}.
	 *
	 * @param year1 The starting year.
	 * @param year2 The ending year.
	 * @param actor The actor's ID.
	 * @return The list of movies.
	 */
	public List<Movie> findMovieBetweenYearFromActor(int year1,int year2,String actor){
		TypedQuery<Movie> query = JpaLink.getEntityManager()
				.createQuery("SELECT m FROM Movie m "
						+ "JOIN m.roles r JOIN r.actor a "
						+ "WHERE a.id = :actor AND m.year BETWEEN :year1 AND :year2", Movie.class);
		query.setParameter("year1", year1);
		query.setParameter("year2", year2);
		query.setParameter("actor", actor);
		List<Movie> movies = query.getResultList();
		return movies;
	}
	
	/**
     * Checks if a movie with the given ID exists in the map.
     *
     * @param idMovie The ID of the movie to check
     * @return true if the movie exists, false otherwise
     */
	public boolean exist(String idMovie) {
		return movieMap.values().stream().anyMatch(r -> r.getId().equals(idMovie));
	}

	 /**
     * Finds a movie by its ID.
     *
     * @param movieId The ID of the movie to find
     * @return The Movie object or null if not found
     */
	public Movie findMovieById(String movieId) {
		return movieMap.values().stream().filter(a -> a.getId().equals(movieId)).findFirst().orElse(null);
	}
	
	@Override
	public void insert(Movie movie) {
		JpaLink.persist(movie);
		movieMap.put(movie.getId(), movie);
	}


}
