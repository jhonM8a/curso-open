package co.com.openintl.bank.repository;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import co.com.openintl.bank.domain.TipoUsuario;
@Repository
public class TipoUsuarioRepositoryImpl implements TipoUsuarioRepository {
	@PersistenceContext
	private EntityManager entityManager;
	
	@Override
	public TipoUsuario save(TipoUsuario entitty) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Optional<TipoUsuario> findById(Long id) {
		Optional<TipoUsuario> optional = Optional.ofNullable(entityManager.find(TipoUsuario.class, id));
		return optional;
	}

	@Override
	public void delete(TipoUsuario entity) {
		// TODO Auto-generated method stub

	}

	@Override
	public void deleteById(Long id) {
		// TODO Auto-generated method stub

	}

	@Override
	public List<TipoUsuario> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

}
