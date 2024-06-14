package utils;

import dao.CountryDao;
import dao.LieuDao;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;

public class JpaConnection {
	
	 public static CountryDao countryDao() {
		 return new CountryDao();
	 }
	 public static LieuDao lieuDao() {
		 return new LieuDao();
	 }
	
	private static final String PERSISTENCE_UNIT_NAME = "jpa_IMD";
	private static EntityManagerFactory entityManagerFactory;

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
