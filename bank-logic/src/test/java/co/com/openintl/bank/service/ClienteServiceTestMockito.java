package co.com.openintl.bank.service;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

import java.util.Date;
import java.util.Optional;

import javax.validation.Validator;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import co.com.openintl.bank.domain.Cliente;
import co.com.openintl.bank.domain.TipoDocumento;
import co.com.openintl.bank.repository.ClienteRepository;
import co.com.openintl.bank.repository.TipoDocumentoRepository;

@ExtendWith(MockitoExtension.class)
class ClienteServiceTestMockito {
	@InjectMocks
	private ClienteService clienteService = new ClienteServiceImpl();
	@Mock
	private TipoDocumentoRepository tipoDocumentoRepository;

	@Mock
	private ClienteRepository clienteRepository;
	
	@Mock
	private Validator validator;
	
	Cliente cliente;
	
	@BeforeEach
	public void setup() {
		
	
		assertNotNull(clienteService);
		assertNotNull(tipoDocumentoRepository);
		TipoDocumento tipoDocumento = new TipoDocumento();
		tipoDocumento.setActivo("S");
		tipoDocumento.setTdocId(1L);
		tipoDocumento.setNombre("CEDULA");
		when(tipoDocumentoRepository.findById(1L)).thenReturn(Optional.ofNullable(tipoDocumento));
		
		cliente = new Cliente();
		cliente.setActivo("S");
		cliente.setClieId(7889L);
		cliente.setDireccion("Cll 48 #94-97");
		cliente.setEmail("jhon.ochoa@unillanos.edu.co");
		cliente.setNombre("Jhon Mario");
		cliente.setTelefono("6666555");

		cliente.setTipoDocumento(tipoDocumentoRepository.findById(1L).get());
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
		when(clienteRepository.findById(7889L)).thenReturn(Optional.ofNullable(cliente));
		Optional<Cliente> cliente = clienteService.findById(7889L);
		assertTrue(cliente.isPresent());

	}
	
	@Test
	@DisplayName("Update client")
	void update() {
		when(clienteRepository.findById(7889L)).thenReturn(Optional.ofNullable(cliente));
		Optional<Cliente> cliente = clienteService.findById(7889L);
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
		when(clienteRepository.findById(7889L)).thenReturn(Optional.ofNullable(cliente));

		try {
			clienteService.deleteById(7889L);
		} catch (Exception e) {
			assertNotNull(e,e.getMessage());
		}
	}
	

}
