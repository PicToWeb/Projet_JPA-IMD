package dao;

import java.util.HashMap;
import java.util.List;
import entity.Acteur;
import entity.Film;
import jakarta.persistence.TypedQuery;
import utils.JpaConnection;

public class MovieDao implements DaoInterface<Film> {

	public static final LieuDao lieuDao = JpaConnection.lieuDao();

	HashMap<String, Film> movieMap = new HashMap<>();

	/**
	 * Constructor
	 * 
	 * @param lieuMap
	 */
	public MovieDao() {
		this.movieMap = findAll();

	}

	public void splitInsert(HashMap<String, Film> movieMap) {

		for (Film a : movieMap.values()) {
			if (!movieExist(a.getId())) {
				
				Film acteur = new Film();
				if (!lieuDao.lieuExist(a.getLieu())) {
					lieuDao.insert(a.getLieu());
				}
				
				acteur.setLieu(lieuDao.findByName(a.getLieu()));
				acteur.setId(a.getId());
//				acteur.setIdentite(a.getIdentite());
//				acteur.setTaille(a.getTaille());
//				acteur.setDateNaissance(a.getDateNaissance());
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
	
	public boolean movieExist(String idMovie) {
		return movieMap.values().stream().anyMatch(r -> r.getId().equals(idMovie));
	}

	public HashMap<String, Film> findAll() {

		HashMap<String, Film> movieMap = new HashMap<>();

		// Utilisez une requête JPQL pour récupérer les réalisateurs depuis la base de
		// données
		TypedQuery<Film> query = JpaConnection.getEntityManager().createQuery("SELECT f FROM Film f",
				Film.class);
		List<Film> movies = query.getResultList();

		// Remplissez le HashMap avec les réalisateurs
		for (Film a : movies) {
			movieMap.put(a.getId(), a);
		}

		return movieMap;
	}


	@Override
	public void insert(Film movie) {

		JpaConnection.persist(movie);
		movieMap.put(movie.getId(), movie);

	}

	@Override
	public void delete(Film movie) {

	}

}
