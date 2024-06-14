package dao;

import java.util.List;

public interface DaoInterface<T> {

	

	void insert(T entity);

	void delete(T entity);

}
