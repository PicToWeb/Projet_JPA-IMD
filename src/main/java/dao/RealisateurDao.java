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

		for (Realisateur a : realisateurMap.values()) {
			if (!realisateurExist(a.getId())) {
				Realisateur realisateur = new Realisateur();
				if (!lieuDao.lieuExist(a.getLieu())) {
					lieuDao.insert(a.getLieu());

				}
				
				realisateur.setLieu(lieuDao.findByName(a.getLieu()));
				realisateur.setId(a.getId());
				realisateur.setIdentite(a.getIdentite());
				realisateur.setDateNaissance(a.getDateNaissance());
				realisateur.setUrl(a.getUrl());

				try {
					insert(realisateur);
					realisateurMap.put(a.getId(), realisateur);

				} catch (Exception e) {
					e.getMessage();
					continue;
				}

			}
		}

	}

	public boolean realisateurExist(String idRealisateur) {
		return realisateurMap.values().stream().anyMatch(r -> r.getId().equals(idRealisateur));
	}

	public HashMap<String, Realisateur> findAll() {

		HashMap<String, Realisateur> realisateursMap = new HashMap<>();

		// Utilisez une requête JPQL pour récupérer les réalisateurs depuis la base de
		// données
		TypedQuery<Realisateur> query = JpaConnection.getEntityManager().createQuery("SELECT r FROM Realisateur r",
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
		// lieuMap.put(lieu.getId(),lieu);

	}

	@Override
	public void delete(Realisateur realisateur) {

	}

}
