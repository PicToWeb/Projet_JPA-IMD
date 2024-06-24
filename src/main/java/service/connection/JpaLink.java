package service.connection;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

/**
 * Abstract class representing a link to JPA (Java Persistence API) functionality.
 */
public abstract class JpaLink {

	 /** Name of the persistence unit */
	private static final String PERSISTENCE_UNIT_NAME = "jpa_IMD";
	 /** EntityManagerFactory instance */
	private static EntityManagerFactory entityManagerFactory;

	
	/**
     * Static method to initialize the EntityManagerFactory.
     */
	public static void initializeEntityManagerFactory() {
		entityManagerFactory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
	}

	 /**
     * Static method to obtain an EntityManager.
     *
     * @return EntityManager instance
     */
	public static EntityManager getEntityManager() {
		if (entityManagerFactory == null) {
			initializeEntityManagerFactory();
		}
		return entityManagerFactory.createEntityManager();
	}

	 /**
     * Static method to close the EntityManagerFactory.
     */
	public static void closeEntityManagerFactory() {
		if (entityManagerFactory != null && entityManagerFactory.isOpen()) {
			entityManagerFactory.close();
		}
	}

	 /**
     * Static method to persist an entity.
     *
     * @param entity Entity to persist
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
