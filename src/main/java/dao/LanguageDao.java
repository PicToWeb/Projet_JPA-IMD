package dao;

import java.util.ArrayList;
import java.util.List;
import entity.MovieLanguage;
import service.connection.JpaLink;

public class LanguageDao implements DaoInterface<MovieLanguage> {

	
	List<MovieLanguage> langueList = new ArrayList<>();
	 /** Constructor
	 * @param lieuMap
	 */
	public LanguageDao() {
		this.langueList = findAll() ;
		
	}

	public boolean genreExist(String langue) {
		return langueList.stream().anyMatch(p->p.getName().equals(langue));
	}
	
	public MovieLanguage findByName(String langue) {
		return langueList.stream().filter(p->p.getName().equals(langue)).findFirst().orElse(null);
	}
	
	
	

	public List<MovieLanguage> findAll() {
		
		return JpaLink.getEntityManager().createQuery("SELECT l FROM MovieLanguage l",MovieLanguage.class).getResultList();
	}

	@Override
	public void insert(MovieLanguage movieLanguage) {
			JpaLink.persist(movieLanguage);
			langueList.add(movieLanguage);
				
	}

	@Override
	public void delete(MovieLanguage movieLanguage) {
		
	}

	
	

}
