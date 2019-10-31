package co.com.openintl.bank.repository;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Date;
import java.util.Random;

import org.springframework.transaction.annotation.Transactional;

import co.com.openintl.bank.domain.Cliente;
import co.com.openintl.bank.domain.TipoDocumento;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Propagation;

@ExtendWith(SpringExtension.class)
@ContextConfiguration("/applicationContext.xml")
@Rollback(false)
class ClienteRepositoryTest {

	@Autowired
	ClienteRepository clienteRepository;
	
	@Autowired
	TipoDocumentoRepository tipoDocumentoRepository;
	
	private Long clientId; 

	@BeforeEach
	void setup() {
		assertNotNull(clienteRepository);
		assertNotNull(tipoDocumentoRepository);
		
		Date date = new Date();

		Long time = date.getTime();

	    Random random = new Random();
		this.clientId = time/random.nextInt(2);
	}

	@Test
	@DisplayName("Save client")
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	void saveTest() {

		assertFalse(clienteRepository.findById(this.clientId).isPresent());

		Cliente cliente = new Cliente();
		cliente.setActivo("S");
		cliente.setClieId(this.clientId);
		cliente.setDireccion("Cll 48 #94-97");
		cliente.setEmail("jhon.ochoa@unillanos.edu.co");
		cliente.setNombre("Jhon Mario");
		cliente.setTelefono("6666555");
		
		assertTrue(tipoDocumentoRepository.findById(1L).isPresent());
		cliente.setTipoDocumento(tipoDocumentoRepository.findById(1L).get());
		
		clienteRepository.save(cliente);
	}

}
