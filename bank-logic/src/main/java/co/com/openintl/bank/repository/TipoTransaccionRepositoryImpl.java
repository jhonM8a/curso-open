package co.com.openintl.bank.repository;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import co.com.openintl.bank.domain.TipoTransaccion;
@Repository
public class TipoTransaccionRepositoryImpl implements TipoTransaccionRepository {

	@PersistenceContext
	private EntityManager entityManager;
	
	@Override
	public TipoTransaccion save(TipoTransaccion entitty) {
		// TODO Auto-generated method stub		
		return null;
	}

	@Override
	public Optional<TipoTransaccion> findById(Long id) {
		Optional<TipoTransaccion> tipoTransaccion = Optional.ofNullable(entityManager.find(TipoTransaccion.class, id));
		return tipoTransaccion;
	}

	@Override
	public void delete(TipoTransaccion entity) {
		// TODO Auto-generated method stub

	}

	@Override
	public void deleteById(Long id) {
		// TODO Auto-generated method stub

	}

	@Override
	public List<TipoTransaccion> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

}
