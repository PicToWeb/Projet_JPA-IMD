package utils;

import dao.ActorDao;
import dao.CountryDao;
import dao.GenreDao;
import dao.LangueDao;
import dao.LieuDao;
import dao.MovieDao;
import dao.RealisateurDao;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public abstract class JpaConnection {

	private static final CountryDao countryDao = new CountryDao();
	private static final LieuDao lieuDao = new LieuDao();
	private static final ActorDao actorDao = new ActorDao();
	private static final RealisateurDao realisateurDao = new RealisateurDao();
	private static final MovieDao movieDao = new MovieDao();
	private static final GenreDao genreDao = new GenreDao();
	private static final LangueDao langueDao = new LangueDao();
	private static final String PERSISTENCE_UNIT_NAME = "jpa_IMD";
	private static EntityManagerFactory entityManagerFactory;
	
	public static CountryDao countryDao() {
		return countryDao;
	}

	public static LieuDao lieuDao() {
		return lieuDao;
	}

	public static ActorDao actorDao() {
		return actorDao;
	}

	public static RealisateurDao realisateurDao() {
		return realisateurDao;
	}

	public static MovieDao movieDao() {
		return movieDao;
	}

	public static GenreDao genreDao() {
		return genreDao;
	}

	public static LangueDao langueDao() {
		return langueDao;
	}


	// Méthode statique pour initialiser l'EntityManagerFactory
	public static void initializeEntityManagerFactory() {
		entityManagerFactory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
	}

	// Méthode statique pour obtenir un EntityManager
	public static EntityManager getEntityManager() {
		if (entityManagerFactory == null) {
			initializeEntityManagerFactory();
		}
		return entityManagerFactory.createEntityManager();
	}

	// Méthode statique pour fermer l'EntityManagerFactory
	public static void closeEntityManagerFactory() {
		if (entityManagerFactory != null && entityManagerFactory.isOpen()) {
			entityManagerFactory.close();
		}
	}

	// Méthode statique pour persister une entité
	public static void persist(Object entity) {
		EntityManager entityManager = getEntityManager();
		try {
			entityManager.getTransaction().begin();
			entityManager.persist(entity);
			entityManager.getTransaction().commit();
		} finally {
			entityManager.close();
		}
	}
}
