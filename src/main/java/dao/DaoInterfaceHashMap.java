package dao;



public interface DaoInterfaceHashMap<T,S> {

	
	void insert(T entity,S key);

	void delete(T entity);

}
