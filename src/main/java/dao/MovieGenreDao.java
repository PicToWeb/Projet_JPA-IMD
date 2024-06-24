package dao;

import java.util.ArrayList;
import java.util.List;
import entity.MovieGenre;
import service.connection.JpaLink;

/**
 * Data Access Object (DAO) for managing movie genres.
 */
public class MovieGenreDao implements DaoInterface<MovieGenre> {

	/** genreList to store genre of movie data */
	List<MovieGenre> genreList = new ArrayList<>();

	
	 /**
     * Constructor to initialize the genre list.
     */
	public MovieGenreDao() {
		this.genreList = findAll();
	}

	 /**
     * Retrieves all movie genres from the database.
     *
     * @return A list of movie genres
     */
	public List<MovieGenre> findAll() {
		return JpaLink.getEntityManager().createQuery("SELECT g FROM MovieGenre g", MovieGenre.class).getResultList();
	}

	  /**
     * Finds a movie genre by its name.
     *
     * @param genre The genre name to search for
     * @return The matching movie genre or null if not found
     */
	public MovieGenre findByName(String genre) {
		return genreList.stream().filter(p -> p.getName().equals(genre)).findFirst().orElse(null);
	}

	@Override
	public void insert(MovieGenre movieGenre) {
		JpaLink.persist(movieGenre);
		genreList.add(movieGenre);
	}


}
