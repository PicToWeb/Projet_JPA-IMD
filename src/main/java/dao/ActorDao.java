package dao;

import java.util.HashMap;

import java.util.List;
import java.util.Map;

import entity.Actor;
import entity.Address;
import jakarta.persistence.TypedQuery;
import service.connection.DaoLink;
import service.connection.JpaLink;

/**
 * 
 */
public class ActorDao implements DaoInterface<Actor> {

	public static final AddressDao addressDao = DaoLink.addressDao();

	HashMap<String, Actor> actorMap = new HashMap<>();

	/**
	 * Constructor
	 * 
	 * @param lieuMap
	 */
	public ActorDao() {
		this.actorMap = findAll();

	}
	

	public void allInsert(Map<String, Actor> actorMap) {

		for (Actor a : actorMap.values()) {
			if (!exist(a.getId())) {
				Actor actor = a;
				Address address = addressDao.existOrAdd(a.getAdress());
				
				actor.setAdress(address);
				actor.setId(a.getId());
				actor.setIdentite(a.getIdentite());
				actor.setSize(a.getSize());
				actor.setBirthdayDate(a.getBirthdayDate());
				actor.setUrl(a.getUrl());

				insert(actor);
			}
		}
	}

	public HashMap<String, Actor> findAll() {
		HashMap<String, Actor> acteurMap = new HashMap<>();

		TypedQuery<Actor> query = JpaLink.getEntityManager().createQuery(
				"SELECT a FROM Actor a LEFT JOIN FETCH a.address ad LEFT JOIN FETCH ad.country", Actor.class);
		List<Actor> actors = query.getResultList();

		for (Actor a : actors) {
			acteurMap.put(a.getId(), a);
		}

		return acteurMap;
	}

	public boolean exist(String idActor) {
		return actorMap.values().stream().anyMatch(r -> r.getId().equals(idActor));
	}

	public Actor findById(String acteurId) {
		return actorMap.values().stream().filter(a -> a.getId().equals(acteurId)).findFirst().orElse(null);
	}
	public Actor findByName(String actor) {
		return actorMap.values().stream().filter(a -> a.getIdentite().equals(actor)).findFirst().orElse(null);
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
