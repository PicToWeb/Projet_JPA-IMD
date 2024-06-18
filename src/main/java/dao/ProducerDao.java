package dao;

import java.util.HashMap;
import java.util.List;

import entity.Producer;
import jakarta.persistence.TypedQuery;
import service.connection.DaoLink;
import service.connection.JpaLink;

public class ProducerDao implements DaoInterface<Producer> {

	public static final AdressDao adressDao = DaoLink.adressDao();

	HashMap<String, Producer> realisateurMap = new HashMap<>();

	/**
	 * Constructor
	 * 
	 * @param lieuMap
	 */
	public ProducerDao() {

		this.realisateurMap = findAll();

	}

	public void splitInsert(HashMap<String, Producer> realisateurMap) {

		for (Producer r : realisateurMap.values()) {
			if (!producerExist(r.getId())) {
				Producer producer = new Producer();
				
//				if (!lieuDao.lieuExist(r.getLieu())) {
//					lieuDao.insert(r.getLieu());
//				}

				adressDao.lieuExistOrAdded(r.getAdress());
				
				producer.setAdress(adressDao.findByName(r.getAdress()));
				producer.setId(r.getId());
				producer.setIdentite(r.getIdentite());
				producer.setBirthdayDate(r.getbirthdayDate());
				producer.setUrl(r.getUrl());

				try {
					insert(producer);
//					realisateurMap.put(a.getId(), realisateur);

				} catch (Exception e) {
					e.getMessage();
					continue;
				}

			}
		}

	}

	public boolean producerExist(String idRealisateur) {
		return realisateurMap.values().stream().anyMatch(r -> r.getId().equals(idRealisateur));
	}
	
	public Producer findById(String idRealisateur) {
		return realisateurMap.values().stream().filter(p->p.getId().equals(idRealisateur)).findFirst().orElse(null);
	}

	public HashMap<String, Producer> findAll() {

		HashMap<String, Producer> realisateursMap = new HashMap<>();

		// Utilisez une requête JPQL pour récupérer les réalisateurs depuis la base de
		// données
		TypedQuery<Producer> query = JpaLink.getEntityManager().createQuery("SELECT p FROM Producer p JOIN FETCH p.adress a JOIN FETCH a.country",
				Producer.class);
		List<Producer> producers = query.getResultList();

		// Remplissez le HashMap avec les réalisateurs
		for (Producer producer : producers) {
			realisateursMap.put(producer.getId(), producer);
		}

		return realisateursMap;
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
