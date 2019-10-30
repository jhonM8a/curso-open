package co.com.openintl.bank.domain;

import static org.junit.jupiter.api.Assertions.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ClienteCRUDTest {

	private static Long clieId = 6768L;
	private EntityManagerFactory entityManagerFactory;
	private EntityManager entityManager;

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
	@DisplayName("save")
	void save() {

		Cliente cliente = entityManager.find(Cliente.class, clieId);
		assertNull(cliente);

		cliente = new Cliente();
		cliente.setActivo("S");
		cliente.setClieId(clieId);
		cliente.setDireccion("Cll 48 #94-97");
		cliente.setEmail("jhon.ochoa@unillanos.edu.co");
		cliente.setNombre("Jhon Mario");
		cliente.setTelefono("6666555");

		TipoDocumento tipoDocumento = entityManager.find(TipoDocumento.class, 1L);
		assertNotNull(tipoDocumento);
		cliente.setTipoDocumento(tipoDocumento);

		entityManager.getTransaction().begin();
		entityManager.persist(cliente);
		entityManager.getTransaction().commit();

	}

	@Test
	@DisplayName("findById")
	void consultar_() {

		Cliente cliente = entityManager.find(Cliente.class, clieId);
		assertNotNull(cliente);
	}
	
	@Test
	@DisplayName("updaTE")
	void update() {

		Cliente cliente = entityManager.find(Cliente.class, clieId);
		assertNotNull(cliente);
		
		cliente.setEmail("jhhihih");
		entityManager.getTransaction().begin();
		entityManager.merge(cliente);
		entityManager.getTransaction().commit();

	}
	
	@Test
	@DisplayName("remove")
	void remove() {

		Cliente cliente = entityManager.find(Cliente.class, clieId);
		assertNotNull(cliente);
		
		entityManager.getTransaction().begin();
		entityManager.remove(cliente);
		entityManager.getTransaction().commit();

	}
	
		
	
}
