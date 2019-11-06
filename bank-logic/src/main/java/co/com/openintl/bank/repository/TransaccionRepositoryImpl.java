package co.com.openintl.bank.repository;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import co.com.openintl.bank.domain.Transaccion;
@Repository
public class TransaccionRepositoryImpl implements TransaccionRepository {

	@PersistenceContext
	private EntityManager entityManager;
	
	@Override
	public Transaccion save(Transaccion entitty) {
		entityManager.persist(entitty);
		return entitty;
	}

	@Override
	public Optional<Transaccion> findById(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(Transaccion entity) {
		// TODO Auto-generated method stub

	}

	@Override
	public void deleteById(Long id) {
		// TODO Auto-generated method stub

	}

	@Override
	public List<Transaccion> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

}
