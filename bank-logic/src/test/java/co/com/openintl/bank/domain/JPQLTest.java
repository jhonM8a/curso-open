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

import co.com.openintl.bank.dto.ResultArimeticasDTO;

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
		
		clientes.forEach(cliente->{
			LOGGER.info("ID: "+cliente.getClieId());
			LOGGER.info("Nombre: "+cliente.getNombre());
		});	
		
	}
	
	@Test
	@DisplayName("Select conditional clients")
	void selectWhereClient() {
		String jpql = "SELECT cli FROM Cliente cli WHERE cli.tipoDocumento.tdocId =: tdocId";
		List<Cliente> clientes = entityManager.createQuery(jpql).
				setParameter("tdocId", 1L).
				getResultList();
		
		clientes.forEach(cliente->{
			LOGGER.info("ID: "+cliente.getClieId());
			LOGGER.info("Nombre: "+cliente.getNombre());
			LOGGER.info("Tipo documento: "+cliente.getTipoDocumento());
		});
			
	}
	@Test
	@DisplayName("Select conditional clients with join")
	void selectWhereClientJoin() {
		String jpql = "SELECT cli FROM Cliente cli WHERE cli.tipoDocumento.nombre =: documentoDesc";
		List<Cliente> clientes = entityManager.createQuery(jpql).
				setParameter("documentoDesc", "CEDULA").
				getResultList();
		clientes.forEach(cliente->{
			LOGGER.info("ID: "+cliente.getClieId());
			LOGGER.info("Nombre: "+cliente.getNombre());
			LOGGER.info("Tipo documento: "+cliente.getTipoDocumento());
		});
							
	}
	
	@Test
	@DisplayName("Select conditional clients with LIKE")
	void selectWhereClientLike() {
		String jpql = "SELECT cli FROM Cliente cli WHERE cli.nombre like : nombre";
		List<Cliente> clientes = entityManager.createQuery(jpql).
				setParameter("nombre", "%j%").
				getResultList();
		clientes.forEach(cliente->{
			LOGGER.info("Nombre: "+cliente.getNombre());
		});	
	
	}
	
	@Test
	@DisplayName("Select conditional clients with more for accounts")
	void selectWhereClientWithMoreForAccounts() {
		String jpql = "SELECT cli FROM Cliente cli WHERE size(cli.cuentas)>4";

		List<Cliente> clientes = entityManager.createQuery(jpql).
				getResultList();
		clientes.forEach(cliente->{
			LOGGER.info("Nombre: "+cliente.getNombre());
		});			
		
	}
	@Test
	@DisplayName("Select artimecthis")
	void selectartimecthiss() {	
		
		String jpql = "SELECT "
				+ "max(cue.saldo), "
				+ "min(cue.saldo), "
				+ "avg(saldo), "
				+ "COUNT(cue.saldo) FROM Cuenta cue";
		
		Object[] object = (Object[])entityManager.createQuery(jpql).getSingleResult();
		LOGGER.info("max: "+object[0]);
		LOGGER.info("min: "+object[1]);
		LOGGER.info("avg: "+object[2]);
		LOGGER.info("COUNT: "+object[3]);


	}
	
	@Test
	@DisplayName("Select artimecthis with DTO")
	void selectartimecdto() {	
		
		String jpql = "SELECT "
				+ "new co.com.openintl.bank.dto.ResultArimeticasDTO("
				+ "max(cue.saldo), "
				+ "min(cue.saldo), "
				+ "avg(saldo), "
				+ "COUNT(cue.saldo)"
				+ ") FROM Cuenta cue";
		
		ResultArimeticasDTO arimeticasDTO = 
				(ResultArimeticasDTO)entityManager.
				createQuery(jpql).
				getSingleResult();
		
		assertNotNull(arimeticasDTO);
		assertNotEquals(0, arimeticasDTO.getMax());


		
	}	
}
