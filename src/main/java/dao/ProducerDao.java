package dao;

import java.util.HashMap;
import java.util.List;

import entity.Address;
import entity.Producer;
import jakarta.persistence.TypedQuery;
import service.connection.DaoLink;
import service.connection.JpaLink;

/**
 * Data Access Object (DAO) for managing producers.
 */
public class ProducerDao implements DaoInterface<Producer> {

	/** ADDRESS_DAO */
	public static final AddressDao ADDRESS_DAO = DaoLink.addressDao();

	 /** HashMap to store producer data */
	HashMap<String, Producer> realisateurMap = new HashMap<>();

	/**
     * Constructor to initialize the producer map.
     */
	public ProducerDao() {

		this.realisateurMap = findAll();

	}

	 /**
     * Inserts all producers from the given map into the database.
     *
     * @param producerMap The map of producers to insert
     */
	public void allInsert(HashMap<String, Producer> producerMap) {

		for (Producer p : producerMap.values()) {
			if (!exist(p.getId())) {
				Producer producer = p;
				Address address = ADDRESS_DAO.existOrAdd(p.getAdress());

				producer.setAdress(address);
				producer.setId(p.getId());
				producer.setIdentite(p.getIdentite());
				producer.setBirthdayDate(p.getBirthdayDate());
				producer.setUrl(p.getUrl());

				insert(producer);
			}
		}

	}

	   /**
     * Retrieves all producers from the database and populates the map.
     *
     * @return A map of producers
     */
	public HashMap<String, Producer> findAll() {

		HashMap<String, Producer> realisateursMap = new HashMap<>();

		TypedQuery<Producer> query = JpaLink.getEntityManager().createQuery(
				"SELECT p FROM Producer p LEFT JOIN FETCH p.address ad LEFT JOIN FETCH ad.country", Producer.class);
		List<Producer> producers = query.getResultList();

		for (Producer producer : producers) {
			realisateursMap.put(producer.getId(), producer);
		}
		return realisateursMap;
	}

	
	 /**
     * Checks if a producer with the given ID exists.
     *
     * @param idRealisateur The producer ID to check
     * @return true if the producer exists, false otherwise
     */
	public boolean exist(String idRealisateur) {
		return realisateurMap.values().stream().anyMatch(r -> r.getId().equals(idRealisateur));
	}

    /**
     * Finds a producer by its ID.
     *
     * @param idRealisateur The producer ID to search for
     * @return The matching producer or null if not found
     */
	public Producer findById(String idRealisateur) {
		return realisateurMap.values().stream().filter(p -> p.getId().equals(idRealisateur)).findFirst().orElse(null);
	}

	@Override
	public void insert(Producer producer) {
		JpaLink.persist(producer);
		realisateurMap.put(producer.getId(), producer);
	}


}
