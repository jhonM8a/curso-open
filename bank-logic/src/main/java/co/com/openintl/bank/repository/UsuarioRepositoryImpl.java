package co.com.openintl.bank.repository;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import co.com.openintl.bank.domain.Cliente;
import co.com.openintl.bank.domain.Usuario;
@Repository
public class UsuarioRepositoryImpl implements UsuarioRepository {
	
	@PersistenceContext
	EntityManager entityManager;
	
	@Override
	public Usuario save(Usuario entitty) {
		// TODO Auto-generated method stub
		if (!entityManager.contains(entitty)) {
			entityManager.persist(entitty);
		}else{
			entityManager.merge(entitty);
		}
		return entitty;
	}

	@Override
	public Optional<Usuario> findById(String id) {
		Usuario usuario = entityManager.find(Usuario.class, id);
		Optional<Usuario> optional = Optional.ofNullable(usuario);
		
		return optional;
	}

	@Override
	public void delete(Usuario entity) {
		// TODO Auto-generated method stub
		entityManager.remove(entity);
	}

	@Override
	public void deleteById(String id) {
		// TODO Auto-generated method stub
		findById(id).ifPresent(cliente->delete(cliente));
	}

	@Override
	public List<Usuario> findAll() {
		// TODO Auto-generated method stub
		return entityManager.createQuery("FROM Usuario").getResultList();
	}

}
