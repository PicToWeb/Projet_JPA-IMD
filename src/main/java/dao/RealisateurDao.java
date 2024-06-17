package dao;

import java.util.HashMap;
import java.util.List;

import entity.Realisateur;
import jakarta.persistence.TypedQuery;
import utils.JpaConnection;

public class RealisateurDao implements DaoInterface<Realisateur> {

	public static final LieuDao lieuDao = JpaConnection.lieuDao();

	HashMap<String, Realisateur> realisateurMap = new HashMap<>();

	/**
	 * Constructor
	 * 
	 * @param lieuMap
	 */
	public RealisateurDao() {

		this.realisateurMap = findAll();

	}

	public void splitInsert(HashMap<String, Realisateur> realisateurMap) {

		for (Realisateur r : realisateurMap.values()) {
			if (!producerExist(r.getId())) {
				Realisateur realisateur = new Realisateur();
				
				if (!lieuDao.lieuExist(r.getLieu())) {
					lieuDao.insert(r.getLieu());
				}
				
				realisateur.setLieu(lieuDao.findByName(r.getLieu()));
				realisateur.setId(r.getId());
				realisateur.setIdentite(r.getIdentite());
				realisateur.setDateNaissance(r.getDateNaissance());
				realisateur.setUrl(r.getUrl());

				try {
					insert(realisateur);
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

	public HashMap<String, Realisateur> findAll() {

		HashMap<String, Realisateur> realisateursMap = new HashMap<>();

		// Utilisez une requête JPQL pour récupérer les réalisateurs depuis la base de
		// données
		TypedQuery<Realisateur> query = JpaConnection.getEntityManager().createQuery("SELECT r FROM Realisateur r JOIN FETCH r.lieu l JOIN FETCH l.pays",
				Realisateur.class);
		List<Realisateur> realisateurs = query.getResultList();

		// Remplissez le HashMap avec les réalisateurs
		for (Realisateur realisateur : realisateurs) {
			realisateursMap.put(realisateur.getId(), realisateur);
		}

		return realisateursMap;
	}

	@Override
	public void insert(Realisateur realisateur) {
		JpaConnection.persist(realisateur);
		realisateurMap.put(realisateur.getId(), realisateur);

	}

	@Override
	public void delete(Realisateur realisateur) {

	}

}
