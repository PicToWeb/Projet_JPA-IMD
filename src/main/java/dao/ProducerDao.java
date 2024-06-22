package dao;

import java.util.HashMap;
import java.util.List;

import entity.Address;
import entity.Producer;
import jakarta.persistence.TypedQuery;
import service.connection.DaoLink;
import service.connection.JpaLink;

public class ProducerDao implements DaoInterface<Producer> {

	public static final AddressDao ADDRESS_DAO = DaoLink.addressDao();

	HashMap<String, Producer> realisateurMap = new HashMap<>();

	/**
	 * Constructor
	 * 
	 * @param lieuMap
	 */
	public ProducerDao() {

		this.realisateurMap = findAll();

	}

	public void allInsert(HashMap<String, Producer> producerMap) {

		for (Producer p : producerMap.values()) {
			if (!exist(p.getId())) {
				Producer producer = p;
				Address address = ADDRESS_DAO.existOrAdd(p.getAdress());

				producer.setAdress(address);
				producer.setId(p.getId());
				producer.setIdentite(p.getIdentite());
				producer.setBirthdayDate(p.getbirthdayDate());
				producer.setUrl(p.getUrl());

				insert(producer);
			}
		}

	}

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
	 * @param idRealisateur
	 * @return
	 */
	public boolean exist(String idRealisateur) {
		return realisateurMap.values().stream().anyMatch(r -> r.getId().equals(idRealisateur));
	}

	/**
	 * @param idRealisateur
	 * @return
	 */
	public Producer findById(String idRealisateur) {
		return realisateurMap.values().stream().filter(p -> p.getId().equals(idRealisateur)).findFirst().orElse(null);
	}

	@Override
	public void insert(Producer producer) {
		JpaLink.persist(producer);
		realisateurMap.put(producer.getId(), producer);
	}

	@Override
	public void delete(Producer producer) {

	}

}
