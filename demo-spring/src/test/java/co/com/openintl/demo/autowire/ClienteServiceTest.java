package co.com.openintl.demo.autowire;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@ContextConfiguration("/appContextAutowired.xml")
class ClienteServiceTest {

	@Autowired
	ClienteService clienteService;
	
	@Test
	void findById() {
		assertNotNull(clienteService);
		Cliente cliente = clienteService.findById(1L);
		assertNotNull(cliente);
	}

	@Test
	void findAll() {
		List<Cliente> clientes = clienteService.findAll();
		assertNotNull(clientes);
		assertFalse(clientes.isEmpty());
	}
}
