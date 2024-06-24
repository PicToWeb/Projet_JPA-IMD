package dao;

import java.util.ArrayList;
import java.util.List;
import entity.MovieLanguage;
import service.connection.JpaLink;

/**
 * Data Access Object (DAO) for managing movie languages.
 * Implements the DaoInterface for MovieLanguage objects.
 */
public class LanguageDao implements DaoInterface<MovieLanguage> {

	/** langueList to store language data */
	List<MovieLanguage> langueList = new ArrayList<>();

	
	/**
     * Constructor initializes the language list by calling findAll().
     */
	public LanguageDao() {
		this.langueList = findAll();
	}

	 /**
     * Retrieves all movie languages from the database.
     *
     * @return List of movie languages.
     */
	public List<MovieLanguage> findAll() {
		return JpaLink.getEntityManager().createQuery("SELECT l FROM MovieLanguage l", MovieLanguage.class)
				.getResultList();
	}

	/**
     * Finds a movie language by its name.
     *
     * @param langue Name of the movie language.
     * @return MovieLanguage object or null if not found.
     */
	public MovieLanguage findByName(String langue) {
		return langueList.stream().filter(p -> p.getName().equals(langue)).findFirst().orElse(null);
	}

	@Override
	public void insert(MovieLanguage movieLanguage) {
		JpaLink.persist(movieLanguage);
		langueList.add(movieLanguage);
	}


}
