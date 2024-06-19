package dao;

import java.util.HashMap;

import java.util.List;
import entity.Adress;
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

	public void splitInsert(HashMap<String, Producer> producerMap) {

		for (Producer p : producerMap.values()) {
			if (!producerExist(p.getId())) {
				Producer producerNew = new Producer();

				Adress adress = adressDao.lieuExistOrAdded(p.getAdress());
				if (adress != null) {
					producerNew.setAdress(adress);
				}
				producerNew.setId(p.getId());
				producerNew.setIdentite(p.getIdentite());
				producerNew.setBirthdayDate(p.getbirthdayDate());
				producerNew.setUrl(p.getUrl());

				insert(producerNew);

			}
		}

	}

	public boolean producerExist(String idRealisateur) {
		return realisateurMap.values().stream().anyMatch(r -> r.getId().equals(idRealisateur));
	}
	

	public Producer findById(String idRealisateur) {
		return realisateurMap.values().stream().filter(p -> p.getId().equals(idRealisateur)).findFirst().orElse(null);
	}

	public HashMap<String, Producer> findAll() {

		HashMap<String, Producer> realisateursMap = new HashMap<>();

		// Utilisez une requête JPQL pour récupérer les réalisateurs depuis la base de
		// données
		TypedQuery<Producer> query = JpaLink.getEntityManager()
				.createQuery("SELECT p FROM Producer p LEFT JOIN FETCH p.adress ad LEFT JOIN FETCH ad.country", Producer.class);
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
