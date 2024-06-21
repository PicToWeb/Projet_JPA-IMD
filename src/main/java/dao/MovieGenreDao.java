package dao;

import java.util.ArrayList;
import java.util.List;
import entity.MovieGenre;
import service.connection.JpaLink;

public class MovieGenreDao implements DaoInterface<MovieGenre> {

	List<MovieGenre> genreList = new ArrayList<>();

	/**
	 * Constructor
	 * 
	 * @param lieuMap
	 */
	public MovieGenreDao() {
		this.genreList = findAll();
	}

	public List<MovieGenre> findAll() {
		return JpaLink.getEntityManager().createQuery("SELECT g FROM MovieGenre g", MovieGenre.class).getResultList();
	}

	public MovieGenre findByName(String genre) {
		return genreList.stream().filter(p -> p.getName().equals(genre)).findFirst().orElse(null);
	}

	@Override
	public void insert(MovieGenre movieGenre) {
		JpaLink.persist(movieGenre);
		genreList.add(movieGenre);
	}

	@Override
	public void delete(MovieGenre movieGenre) {

	}

}
