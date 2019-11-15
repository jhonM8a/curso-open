package co.com.openintl.bank.service;

import java.util.List;
import java.util.Optional;

public interface GenericService<T, ID> {

	T save(T entity) throws Exception;
	
	T update(T entity) throws Exception;
	
	Optional<T> findById(ID id);
	
	void delete(T entity) throws Exception;
	
	void deleteById(ID id) throws Exception;
	
	List<T> findAll();
	
	
}
