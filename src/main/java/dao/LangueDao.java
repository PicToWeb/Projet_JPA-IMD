package dao;

import java.util.ArrayList;
import java.util.List;
import entity.Langue;
import utils.JpaConnection;

public class LangueDao implements DaoInterface<Langue> {

	
	List<Langue> langueList = new ArrayList<>();
	 /** Constructor
	 * @param lieuMap
	 */
	public LangueDao() {
		this.langueList = findAll() ;
		
	}

	public boolean genreExist(String langue) {
		return langueList.stream().anyMatch(p->p.getNom().equals(langue));
	}
	
	public Langue findByName(String langue) {
		return langueList.stream().filter(p->p.getNom().equals(langue)).findFirst().orElse(null);
	}
	
	
	

	public List<Langue> findAll() {
		
		return JpaConnection.getEntityManager().createQuery("SELECT l FROM Langue l",Langue.class).getResultList();
	}

	@Override
	public void insert(Langue langue) {
		
			JpaConnection.persist(langue);
			langueList.add(langue);
				
	}

	@Override
	public void delete(Langue langue) {
		
	}

	
	

}
