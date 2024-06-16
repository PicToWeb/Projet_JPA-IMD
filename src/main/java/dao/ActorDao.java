package dao;

import java.util.HashMap;

import entity.Acteur;

import utils.JpaConnection;

public class ActorDao implements DaoInterface<Acteur> {

	public static final LieuDao lieuDao = JpaConnection.lieuDao();

	HashMap<Integer, Acteur> acteurMap = new HashMap<>();

	/**
	 * Constructor
	 * 
	 * @param lieuMap
	 */
	public ActorDao() {
		this.acteurMap = findAll();

	}

	public void splitInsert(HashMap<String, Acteur> acteurMap) {
		Acteur acteur = new Acteur();
		for (Acteur a : acteurMap.values()) {
			//if(!lieuDao.lieuExist(a.getLieu())) {
			lieuDao.insert(a.getLieu());
			acteur.setLieu(a.getLieu());
			//}
			acteur.setId(a.getId());
			acteur.setIdentite(a.getIdentite());
			acteur.setDateNaissance(a.getDateNaissance());
			acteur.setTaille(a.getTaille());
			acteur.setUrl(a.getUrl());
			insert(acteur);
		}
	
//		JpaConnection.persist(acteurMap.get("nm0287142").getLieu());
//		acteur.setLieu(acteurMap.get("nm0287142").getLieu());
//		
//		JpaConnection.persist(acteur);
	}

	public HashMap<Integer, Acteur> findAll() {
		return null;
		// return JpaConnection.getEntityManager().createQuery("SELECT l FROM Lieu
		// l",Lieu.class).getResultList();
	}

	@Override
	public void insert(Acteur acteur) {

		JpaConnection.persist(acteur);
		// lieuMap.put(lieu.getId(),lieu);

	}

	@Override
	public void delete(Acteur acteur) {

	}

}
