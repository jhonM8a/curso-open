package co.com.openintl.bank.service;

import static org.junit.jupiter.api.Assertions.*;

import java.math.BigDecimal;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@ContextConfiguration("/applicationContext.xml")
@Rollback(true) // Realiza rollback de todo
class TransaccionServiceTest {

	@Autowired
	TransaccionService transaccionService;

	String cuenId = "4640-0341-9387-5781";
	
	String cuenIdDestino = "1630-2511-2937-7299";

	BigDecimal valor = new BigDecimal(15000);

	String usuUsuario = "lcasbolt8";

	@BeforeEach
	public void setup() {
		assertNotNull(transaccionService);
	}

	@Test
	@DisplayName("Retirar")
	void retirar() {
		try {
			Long numeroTransaccion = transaccionService.retirar(cuenId, valor, usuUsuario);
			assertNotNull(numeroTransaccion);
		} catch (Exception e) {
			assertNull(e, e.getMessage());
		}
	}

	@Test
	@DisplayName("Consignar")
	void Consignar() {
		try {
			Long numeroTransaccion = transaccionService.consignacion(cuenId, valor, usuUsuario);
			assertNotNull(numeroTransaccion);
		} catch (Exception e) {
			assertNull(e, e.getMessage());
		}

	}
	
	@Test
	@DisplayName("trasferir")
	void trasferir() {
		try {
			Long numeroTransaccion = transaccionService.transferencia(cuenId, cuenIdDestino, valor, usuUsuario);
			assertNotNull(numeroTransaccion);
		} catch (Exception e) {
			assertNull(e, e.getMessage());
		}
	}
	

}
