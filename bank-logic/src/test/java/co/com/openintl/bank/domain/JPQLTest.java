package co.com.openintl.bank.domain;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

class JPQLTest {
	private static final Logger LOGGER = LoggerFactory.getLogger(JPQLTest.class);
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
	/**
	 * Init case Test
	 * */
	@Test
	@DisplayName("Select all clienta using JPQL")
	void selectAllClients() {
		
		String jpql = "FROM Cliente";
		
		Query query = entityManager.createQuery(jpql);
		List<Cliente> clientes = query.getResultList();
		
		for (Cliente cliente : clientes) {
			LOGGER.info("ID: "+cliente.getClieId());
			LOGGER.info("Nombre: "+cliente.getNombre());
		}
		
		
	}

}
