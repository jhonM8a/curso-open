package co.com.openintl.bank.domain;

import static org.junit.jupiter.api.Assertions.*;

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
class ClienteCRUDSpringTest {

	@PersistenceContext
	EntityManager entityManager;
	private static Long clieId = 6769L;

	@Test
	void test() {
		assertNotNull(entityManager);
	}
	@Test
	@DisplayName("save")
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
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

		entityManager.persist(cliente);
	}
	
	@Test
	@DisplayName("findById")
	@Transactional(readOnly = true)
	void consultar_() {
		Cliente cliente = entityManager.find(Cliente.class, 6769L);
		assertNotNull(cliente);
	}	
}
