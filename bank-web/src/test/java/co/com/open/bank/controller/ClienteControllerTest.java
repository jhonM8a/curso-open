package co.com.open.bank.controller;

import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.ResponseErrorHandler;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import co.com.openintl.bank.dto.ClienteDTO;

class ClienteControllerTest {

	private final static Long clieId = 4590L;

	private final static String url = "http://localhost:8080/bank-web/api/cliente/";

	private final static Logger log = LoggerFactory.getLogger(ClienteControllerTest.class);

	@Test
	@DisplayName("save")
	void save() {
		try {
			RestTemplate restTemplate = new RestTemplate();
			ClienteDTO clienteDTO = new ClienteDTO();

			clienteDTO.setActivo("S");
			clienteDTO.setClieId(clieId);
			clienteDTO.setDireccion("SN KK");
			clienteDTO.setEmail("jhon.ochoa@openintl.com");
			clienteDTO.setNombre("Jhon Mario");
			clienteDTO.setTdocId(1l);
			clienteDTO.setTelefono("3116192217");

			Object object = restTemplate.postForObject(url + "save", clienteDTO, Object.class);
			assertNotNull(object);
		} catch (HttpStatusCodeException e) {
			// TODO Auto-generated catch block
			log.info(e.getResponseBodyAsString());
			assertNull(e, e.getMessage());
		}

	}

	@Test
	@DisplayName("findById")
	void findById() {
		try {
			RestTemplate restTemplate = new RestTemplate();
			ClienteDTO clienteDTO = restTemplate.getForObject(url + "findById/" + clieId, ClienteDTO.class);
			assertNotNull(clienteDTO);
		} catch (HttpStatusCodeException e) {
			// TODO Auto-generated catch block
			log.info(e.getResponseBodyAsString());
			assertNull(e, e.getMessage());
		}

	}

	@Test
	@DisplayName("update")
	void update() {
		try {
			RestTemplate restTemplate = new RestTemplate();
			ClienteDTO clienteDTO = new ClienteDTO();

			clienteDTO.setActivo("S");
			clienteDTO.setClieId(clieId);
			clienteDTO.setDireccion("SN KK");
			clienteDTO.setEmail("jhon.ochoa@openintl.com");
			clienteDTO.setNombre("Jhon Mario");
			clienteDTO.setTdocId(1l);
			clienteDTO.setTelefono("3116192217");

			restTemplate.put(url + "update", clienteDTO, Object.class);
		
		} catch (HttpStatusCodeException e) {
			// TODO Auto-generated catch block
			log.info(e.getResponseBodyAsString());
			assertNull(e, e.getMessage());
		}

	}
	
	@Test
	@DisplayName("delete")
	void delete() {
		try {
			RestTemplate restTemplate = new RestTemplate();


			restTemplate.delete(url + "delete/"+clieId);
		
		} catch (HttpStatusCodeException e) {
			// TODO Auto-generated catch block
			log.info(e.getResponseBodyAsString());
			assertNull(e, e.getMessage());
		}

	}


	
}
