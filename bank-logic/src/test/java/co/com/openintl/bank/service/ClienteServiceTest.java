package co.com.openintl.bank.service;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Date;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import co.com.openintl.bank.domain.Cliente;
import co.com.openintl.bank.repository.TipoDocumentoRepository;

@ExtendWith(SpringExtension.class)
@ContextConfiguration("/applicationContext.xml")
@Rollback(true) // Realiza rollback de todo
class ClienteServiceTest {
	@Autowired
	private ClienteService clienteService;
	@Autowired
	private TipoDocumentoRepository tipoDocumentoRepository;

	@BeforeEach
	public void setup() {
		assertNotNull(clienteService);
		assertNotNull(tipoDocumentoRepository);

	}

	@Test
	@DisplayName("Save client")
	void save() {
		Date date = new Date();
		Long time = date.getTime();
		Long clieId = time / 100000;

		Cliente cliente = new Cliente();
		cliente.setActivo("S");
		cliente.setClieId(clieId);
		cliente.setDireccion("Cll 48 #94-97");
		cliente.setEmail("jhon.ochoa@unillanos.edu.co");
		cliente.setNombre("Jhon Mario");
		cliente.setTelefono("6666555");

		assertTrue(tipoDocumentoRepository.findById(1L).isPresent());
		cliente.setTipoDocumento(tipoDocumentoRepository.findById(1L).get());

		try {
			clienteService.save(cliente);
		} catch (Exception e) {
			assertNull(e, e.getMessage());
		}
	}

	@Test
	@DisplayName("Find client")
	void findById() {
		Optional<Cliente> cliente = clienteService.findById(1L);
		assertTrue(cliente.isPresent());

	}
	
	@Test
	@DisplayName("Update client")
	void update() {
		Optional<Cliente> cliente = clienteService.findById(2L);
		assertTrue(cliente.isPresent());
		
		Cliente cliente2 = cliente.get();
		cliente2.setActivo("N");
		cliente2.setEmail("jhon.ochoa2@openintl.com");
		
		try {
			clienteService.update(cliente2);
		} catch (Exception e) {
			// TODO: handle exception
			assertNull(e,  e.getMessage());
		}
	}
	
	@Test
	@DisplayName("Delete client with accounts associated")
	void delete() {
		try {
			clienteService.deleteById(1L);
		} catch (Exception e) {
			assertNotNull(e,e.getMessage());
		}
	}
	

}
