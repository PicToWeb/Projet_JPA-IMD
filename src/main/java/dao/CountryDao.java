package dao;

import java.util.ArrayList;

import java.util.List;

import entity.Pays;
import utils.JpaConnection;

public class CountryDao implements DaoInterface<Pays> {

	List<Pays> countryList = new ArrayList<>();
	 /** Constructor
	 * @param lieuMap
	 */
	public CountryDao() {
		this.countryList = findAll() ;
		
	}

	public boolean countryExist(String pays) {
		return countryList.stream().anyMatch(p->p.getNom().equals(pays));
		
	}
	
	public Pays findByName(String pays) {
		return countryList.stream().filter(p->p.getNom().equals(pays)).findFirst().orElse(null);
	}
	

	

	
	public List<Pays> findAll() {
		
		return JpaConnection.getEntityManager().createQuery("SELECT p FROM Pays p",Pays.class).getResultList();
	}

	@Override
	public void insert(Pays pays) {
		
			JpaConnection.persist(pays);
			countryList.add(pays);
			
		
	}

	@Override
	public void delete(Pays entity) {
		
	}

	
	

}
