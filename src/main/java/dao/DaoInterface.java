package dao;

/**
 * Generic interface for basic data access operations (CRUD).
 * @param <T> Type of entity managed by the DAO.
 */
public interface DaoInterface<T> {

	  /**
     * Inserts an entity into the database.
     *
     * @param entity Entity to insert.
     */
	void insert(T entity);


}
