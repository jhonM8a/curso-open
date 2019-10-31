package co.com.openintl.bank.repository;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import co.com.openintl.bank.domain.Cliente;
@Repository //Por defecto es singleton
public class ClienteRepositoryImpl implements ClienteRepository {

	@PersistenceContext
	EntityManager entityManager;
	
	
	@Override
	public Cliente save(Cliente entitty) {
		// TODO Auto-generated method stub
		if (!entityManager.contains(entitty)) {
			entityManager.persist(entitty);
		}else{
			entityManager.merge(entitty);
		}
		return entitty;
	}

	@Override
	public Optional<Cliente> findById(Long id) {
		// TODO Auto-generated method stub
		Cliente cliente = entityManager.find(Cliente.class, id);
		Optional<Cliente> optional = Optional.ofNullable(cliente);
		
		return optional;
	}

	@Override
	public void delete(Cliente entity) {
		// TODO Auto-generated method stub
		entityManager.remove(entity);
	}

	@Override
	public void deleteById(Long id) {
		// TODO Auto-generated method stub
		findById(id).ifPresent(cliente->delete(cliente));
	}

	@Override
	public List<Cliente> findAll() {
		// TODO Auto-generated method stub
		return entityManager.createQuery("FROM Cliente").getResultList();
	}

}
