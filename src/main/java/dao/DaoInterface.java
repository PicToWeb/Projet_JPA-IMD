package dao;


public interface DaoInterface<T> {

	void insert(T entity);

	void delete(T entity);

}
