package dao;

import java.util.HashMap;


import entity.Lieu;

import utils.JpaConnection;

public class LieuDao implements DaoInterface<Lieu> {

	HashMap<Integer,Lieu> lieuMap = new HashMap<>();
	
	 /** Constructor
	 * @param lieuMap
	 */
	public LieuDao() {
		this.lieuMap = findAll() ;
		
	}

	
	public HashMap<Integer,Lieu> findAll() {
		return null;
		//return JpaConnection.getEntityManager().createQuery("SELECT l FROM Lieu l",Lieu.class).getResultList();
	}

	@Override
	public void insert(Lieu lieu) {
		
			JpaConnection.persist(lieu);
			lieuMap.put(lieu.getId(),lieu);
			
		
	}

	@Override
	public void delete(Lieu entity) {
		
	}

	
	

}
