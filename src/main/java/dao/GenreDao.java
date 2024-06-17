package dao;

import java.util.ArrayList;


import java.util.List;
import java.util.Set;

import entity.Genre;
import entity.Pays;
import utils.JpaConnection;

public class GenreDao implements DaoInterface<Genre> {

	
	List<Genre> genreList = new ArrayList<>();
	 /** Constructor
	 * @param lieuMap
	 */
	public GenreDao() {
		this.genreList = findAll() ;
		
	}

	public boolean genreExist(String genre) {
		return genreList.stream().anyMatch(p->p.getNom().equals(genre));
	}
	
	public Genre findByName(String genre) {
		return genreList.stream().filter(p->p.getNom().equals(genre)).findFirst().orElse(null);
	}
	
	
	

	public List<Genre> findAll() {
		
		return JpaConnection.getEntityManager().createQuery("SELECT g FROM Genre g",Genre.class).getResultList();
	}
	
	public void insertGenre(Set<Genre> genreList) {
		for(Genre g : genreList) {
			 Genre existingGenre = findByName(g.getNom());
			    if (existingGenre == null) {
			    	existingGenre = new Genre(g.getNom());
			        insert(existingGenre);
			    }
		}
	}

	@Override
	public void insert(Genre genre) {
		
			JpaConnection.persist(genre);
			genreList.add(genre);
				
	}

	@Override
	public void delete(Genre genre) {
		
	}

	
	

}
