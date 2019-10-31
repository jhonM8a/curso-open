package co.com.openintl.bank.repository;

import java.util.List;
import java.util.Optional;

public interface JPAGenericRepository<T, ID> {

	T save(T entitty);
	
	Optional<T> findById(ID id);
	
	void delete(T entity);
	
	void deleteById(ID id);
	
	List<T> findAll();
}
