package co.com.openintl.bank.domain;

import static org.junit.jupiter.api.Assertions.*;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.UUID;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class UsuarioCRUDTest {
	private EntityManagerFactory entityManagerFactory;
	private EntityManager entityManager;
	private Usuario usuario;
	

	@BeforeEach
	void setup() {
		entityManagerFactory = Persistence.createEntityManagerFactory("bank-logic");
		entityManager = entityManagerFactory.createEntityManager();
	}

	@AfterEach
	void clean() {
		entityManager.close();
		entityManagerFactory.close();
	}
	@Test
	@DisplayName("Create User")
	void insert() {
		Usuario usuario = new Usuario();
		usuario.setUsuUsuario(UUID.randomUUID().toString());
		usuario.setClave("******");
		usuario.setIdentificacion(new BigDecimal(100));
		usuario.setNombre("Jhon");
		usuario.setActivo("S");
		
		
		entityManager.getTransaction().begin();
		entityManager.persist(usuario);
		entityManager.getTransaction().commit();
		 
		Usuario usuarioFind = entityManager.find(Usuario.class, usuario.getUsuUsuario());
		
		assertNotNull(usuarioFind);
	}
	
	@Test
	@DisplayName("Update User")
	void update() {
		Usuario usuarioFind = entityManager.find(Usuario.class,"50155f88-cd04-47a6-9f85-aede7c0ac8f2");
		
		usuarioFind.setClave("nueva clave");
		entityManager.getTransaction().begin();
		entityManager.merge(usuarioFind);
		entityManager.getTransaction().commit();
	}

	@Test
	@DisplayName("remove User")
	void remove() {
		Usuario usuarioFind = entityManager.find(Usuario.class,"50155f88-cd04-47a6-9f85-aede7c0ac8f2");
		
		
		entityManager.getTransaction().begin();
		entityManager.remove(usuarioFind);
		entityManager.getTransaction().commit();
	}
	
}
