package co.com.openintl.bank.repository;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import co.com.openintl.bank.domain.Cuenta;

@Repository
public class CuentaRepositoryImpl implements CuentaRepository {

	@PersistenceContext
	private EntityManager entityManager;
	
	@Override
	public Cuenta save(Cuenta entitty) {
		if (!entityManager.contains(entitty) && !findById(entitty.getCuenId()).isPresent()) {
			entityManager.persist(entitty);
		}else{
			entityManager.merge(entitty);
		}
		return entitty;
	}

	@Override
	public Optional<Cuenta> findById(String id) {
		Optional<Cuenta> cuenta = Optional.ofNullable(entityManager.find(Cuenta.class, id));
		return cuenta;
	}

	@Override
	public void delete(Cuenta entity) {
		// TODO Auto-generated method stub

	}

	@Override
	public void deleteById(String id) {
		// TODO Auto-generated method stub

	}

	@Override
	public List<Cuenta> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

}
