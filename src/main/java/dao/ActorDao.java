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
 * Data Access Object (DAO) for managing actors.
 * Implements the DaoInterface for Actor objects.
 */
public class ActorDao implements DaoInterface<Actor> {

	/** addressDao */
	public static final AddressDao addressDao = DaoLink.addressDao();

	 /** HashMap to store actor data */
	HashMap<String, Actor> actorMap = new HashMap<>();

	/**
     * Constructor initializes the actor map by calling findAll().
     */
	public ActorDao() {
		this.actorMap = findAll();
	}

	/**
     * Inserts all actors from the given map into the database.
     *
     * @param actorMap Map of actors to insert.
     */
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

	/**
     * Retrieves all actors from the database.
     *
     * @return Map of actors with their IDs as keys.
     */
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
	
	/**
     * Finds actors who have roles in both specified movies.
     *
     * @param movie1 ID of the first movie.
     * @param movie2 ID of the second movie.
     * @return List of actors.
     */
	public List<Actor> findMovieOfActors(String movie1,String movie2) {
		TypedQuery<Actor> query = JpaLink.getEntityManager()
				.createQuery("SELECT a FROM Actor a "
		                + "JOIN a.roles r1 JOIN r1.movie m1 "
		                + "JOIN a.roles r2 JOIN r2.movie m2 "
		                + "WHERE m1.id = :movie1 AND m2.id = :movie2", Actor.class);
		query.setParameter("movie1", movie1);
		query.setParameter("movie2", movie2);
		List <Actor>actors = query.getResultList();
		return actors;
	}

	 /**
     * Checks if an actor with the given ID exists in the map.
     *
     * @param idActor ID of the actor.
     * @return True if actor exists, false otherwise.
     */
	public boolean exist(String idActor) {
		return actorMap.values().stream().anyMatch(r -> r.getId().equals(idActor));
	}

	/**
     * Finds an actor by their ID.
     *
     * @param acteurId ID of the actor.
     * @return Actor object or null if not found.
     */
	public Actor findById(String acteurId) {
		return actorMap.values().stream().filter(a -> a.getId().equals(acteurId)).findFirst().orElse(null);
	}
	
	/**
     * Finds an actor by their name (identite).
     *
     * @param actor Name of the actor.
     * @return Actor object or null if not found.
     */
	public Actor findByName(String actor) {
		return actorMap.values().stream().filter(a -> a.getIdentite().equals(actor)).findFirst().orElse(null);
	}

	@Override
	public void insert(Actor actor) {
		JpaLink.persist(actor);
		actorMap.put(actor.getId(), actor);
	}


}
