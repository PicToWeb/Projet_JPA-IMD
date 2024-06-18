package dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import entity.MovieGenre;
import service.connection.JpaLink;

public class MovieGenreDao implements DaoInterface<MovieGenre> {

	
	List<MovieGenre> genreList = new ArrayList<>();
	 /** Constructor
	 * @param lieuMap
	 */
	public MovieGenreDao() {
		this.genreList = findAll() ;
		
	}

	public boolean genreExist(String genre) {
		return genreList.stream().anyMatch(p->p.getName().equals(genre));
	}
	
	public MovieGenre findByName(String genre) {
		return genreList.stream().filter(p->p.getName().equals(genre)).findFirst().orElse(null);
	}
	
	
	

	public List<MovieGenre> findAll() {
		
		return JpaLink.getEntityManager().createQuery("SELECT g FROM MovieGenre g",MovieGenre.class).getResultList();
	}
	
	public void insertGenre(Set<MovieGenre> genreList) {
		for(MovieGenre g : genreList) {
			 MovieGenre existingGenre = findByName(g.getName());
			    if (existingGenre == null) {
			    	existingGenre = new MovieGenre(g.getName());
			        insert(existingGenre);
			    }
		}
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
