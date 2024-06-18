package dao;

import java.util.HashMap;
import java.util.List;
import entity.Acteur;
import jakarta.persistence.TypedQuery;
import utils.JpaConnection;

public class ActorDao implements DaoInterface<Acteur> {


	public static final LieuDao lieuDao = JpaConnection.lieuDao();

	HashMap<String, Acteur> actorMap = new HashMap<>();

	/**
	 * Constructor
	 * 
	 * @param lieuMap
	 */
	public ActorDao() {
		this.actorMap = findAll();

	}

	public void splitInsert(HashMap<String, Acteur> actorMap) {

		for (Acteur a : actorMap.values()) {
			if (!actorExist(a.getId())) {
				
				Acteur acteur = new Acteur();

				lieuDao.lieuExistOrAdded(a.getLieu());
				acteur.setLieu(lieuDao.findByName(a.getLieu()));
				acteur.setId(a.getId());
				acteur.setIdentite(a.getIdentite());
				acteur.setTaille(a.getTaille());
				acteur.setDateNaissance(a.getDateNaissance());
				acteur.setUrl(a.getUrl());

				try {
					insert(acteur);

				} catch (Exception e) {
					e.getMessage();
					continue;
				}

			}
		}
	}
	
	public boolean actorExist(String idActor) {
		return actorMap.values().stream().anyMatch(r -> r.getId().equals(idActor));
	}

	
	public HashMap<String, Acteur> findAll() {

		HashMap<String, Acteur> acteurMap = new HashMap<>();

		// Utilisez une requête JPQL pour récupérer les réalisateurs depuis la base de
		// données
		TypedQuery<Acteur> query = JpaConnection.getEntityManager().createQuery("SELECT a FROM Acteur a JOIN FETCH a.lieu l JOIN FETCH l.pays",
				Acteur.class);
		List<Acteur> actors = query.getResultList();

		// Remplissez le HashMap avec les réalisateurs
		for (Acteur a : actors) {
			acteurMap.put(a.getId(), a);
		}

		return acteurMap;
	}


	@Override
	public void insert(Acteur acteur) {

		JpaConnection.persist(acteur);
		actorMap.put(acteur.getId(), acteur);

	}

	@Override
	public void delete(Acteur acteur) {

	}

}
