package service.connection;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

/**
 * 
 */
public abstract class JpaLink {

	/** PERSISTENCE_UNIT_NAME */
	private static final String PERSISTENCE_UNIT_NAME = "jpa_IMD";
	/** entityManagerFactory */
	private static EntityManagerFactory entityManagerFactory;

	
	/**
	 * Méthode statique pour initialiser l'EntityManagerFactory
	 * 
	 */
	public static void initializeEntityManagerFactory() {
		entityManagerFactory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
	}

	/**
	 * Méthode statique pour obtenir un EntityManager
	 * @return
	 */
	public static EntityManager getEntityManager() {
		if (entityManagerFactory == null) {
			initializeEntityManagerFactory();
		}
		return entityManagerFactory.createEntityManager();
	}

	/**
	 * Méthode statique pour fermer l'EntityManagerFactory
	 * 
	 */
	public static void closeEntityManagerFactory() {
		if (entityManagerFactory != null && entityManagerFactory.isOpen()) {
			entityManagerFactory.close();
		}
	}

	/** 
	 * Méthode statique pour persister une entité
	 * @param entity
	 */
	public static void persist(Object entity) {
		EntityManager entityManager = getEntityManager();
		try {
			entityManager.getTransaction().begin();
			entityManager.persist(entity);
			entityManager.getTransaction().commit();
		} catch (NullPointerException e) {
			System.err.println(e);
		} finally {
			entityManager.close();
		}
	}
}
