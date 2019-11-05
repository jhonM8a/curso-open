package co.com.openintl.bank.repository;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import co.com.openintl.bank.domain.TipoDocumento;
@Repository
public class TipoDocumentoRepositoryImpl implements TipoDocumentoRepository {

	@PersistenceContext
	private EntityManager entityManager;
	
	@Override
	public TipoDocumento save(TipoDocumento entitty) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Optional<TipoDocumento> findById(Long id) {
		Optional<TipoDocumento> optional = Optional.ofNullable(entityManager.find(TipoDocumento.class, id));
		return optional;
	}

	@Override
	public void delete(TipoDocumento entity) {
		// TODO Auto-generated method stub

	}

	@Override
	public void deleteById(Long id) {
		// TODO Auto-generated method stub

	}

	@Override
	public List<TipoDocumento> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

}
