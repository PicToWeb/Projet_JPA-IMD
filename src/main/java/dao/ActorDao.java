package dao;

import java.util.HashMap;
import java.util.List;

import entity.Actor;
import jakarta.persistence.TypedQuery;
import service.connection.DaoLink;
import service.connection.JpaLink;

public class ActorDao implements DaoInterface<Actor> {


	public static final AdressDao adressDao = DaoLink.adressDao();

	HashMap<String, Actor> actorMap = new HashMap<>();

	/**
	 * Constructor
	 * 
	 * @param lieuMap
	 */
	public ActorDao() {
		this.actorMap = findAll();

	}

	public void splitInsert(HashMap<String, Actor> actorMap) {

		for (Actor a : actorMap.values()) {
			if (!actorExist(a.getId())) {
				
				Actor actor = new Actor();

				adressDao.lieuExistOrAdded(a.getAdress());
				actor.setAdress(adressDao.findByName(a.getAdress()));
				actor.setId(a.getId());
				actor.setIdentite(a.getIdentite());
				actor.setSize(a.getSize());
				actor.setBirthdayDate(a.getbirthdayDate());
				actor.setUrl(a.getUrl());

				try {
					insert(actor);

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

	public Actor findActorById(String acteurId) {
		return actorMap.values().stream().filter(a->a.getId().equals(acteurId)).findFirst().orElse(null);
	}
	
	public HashMap<String, Actor> findAll() {

		HashMap<String, Actor> acteurMap = new HashMap<>();

		// Utilisez une requête JPQL pour récupérer les réalisateurs depuis la base de
		// données
		TypedQuery<Actor> query = JpaLink.getEntityManager().createQuery("SELECT a FROM Actor a JOIN FETCH a.adress l JOIN FETCH l.country",
				Actor.class);
		List<Actor> actors = query.getResultList();

		// Remplissez le HashMap avec les réalisateurs
		for (Actor a : actors) {
			acteurMap.put(a.getId(), a);
		}

		return acteurMap;
	}


	@Override
	public void insert(Actor actor) {

		JpaLink.persist(actor);
		actorMap.put(actor.getId(), actor);

	}

	@Override
	public void delete(Actor actor) {

	}

}
