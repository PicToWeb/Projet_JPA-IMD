package dao;

import java.util.HashMap;

import java.util.List;
import entity.Film;
import jakarta.persistence.TypedQuery;
import utils.JpaConnection;

public class MovieDao implements DaoInterface<Film> {

	public static final LieuDao lieuDao = JpaConnection.lieuDao();
	public static final GenreDao genreDao = JpaConnection.genreDao();
	public static final CountryDao countryDao = JpaConnection.countryDao();

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

		for (Film f : movieMap.values()) {
			if (!movieExist(f.getId())) {
				
				if (!lieuDao.lieuExist(f.getLieu())) {
					lieuDao.insert(f.getLieu());
				}
				if(!countryDao.countryExist2(f.getPays())) {
					countryDao.insert(f.getPays());
				}
				
				Film movie = new Film(f.getId(),f.getNom(),f.getAnnee(),f.getRating(),f.getUrl(),f.getResume());
				movie.setLangue(f.getLangue());
				movie.setPays(f.getPays());
				movie.setLieu(lieuDao.findByName(f.getLieu()));
				movie.setGenres(f.getGenres());

				try {
					insert(movie);

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
		TypedQuery<Film> query = JpaConnection.getEntityManager().createQuery("SELECT f FROM Film f JOIN FETCH f.lieu l JOIN FETCH l.pays",
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
