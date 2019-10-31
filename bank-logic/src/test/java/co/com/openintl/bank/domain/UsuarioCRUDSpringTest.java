package co.com.openintl.bank.domain;

import static org.junit.jupiter.api.Assertions.*;

import java.math.BigDecimal;
import java.util.UUID;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;


@ExtendWith(SpringExtension.class)
@ContextConfiguration("/applicationContext.xml")
@Rollback(false)
class UsuarioCRUDSpringTest {
	
	@PersistenceContext
	EntityManager entityManager;
	
	@Test
	@DisplayName("Create User")
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	void insert() {
		Usuario usuario = new Usuario();
		usuario.setUsuUsuario(UUID.randomUUID().toString());
		usuario.setClave("******");
		usuario.setIdentificacion(new BigDecimal(100));
		usuario.setNombre("Jhon");
		usuario.setActivo("S");
		
		
		entityManager.persist(usuario);
		 
		Usuario usuarioFind = entityManager.find(Usuario.class, usuario.getUsuUsuario());
		
		assertNotNull(usuarioFind);
	}

	
	@Test
	@DisplayName("Update User")
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	void update() {
		Usuario usuarioFind = entityManager.find(Usuario.class,"d9ad049b-645e-4e42-a555-b32c2687b219");
		
		usuarioFind.setClave("nueva clave");
		entityManager.merge(usuarioFind);
	}

	@Test
	@DisplayName("remove User")
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	void remove() {
		Usuario usuarioFind = entityManager.find(Usuario.class,"d9ad049b-645e-4e42-a555-b32c2687b219");
		
		entityManager.remove(usuarioFind);
	}	
	
}
