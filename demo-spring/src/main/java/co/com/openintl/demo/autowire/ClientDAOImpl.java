package co.com.openintl.demo.autowire;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

@Repository
@Scope("singleton")
public class ClientDAOImpl implements ClienteDAO{

	@Override
	public Cliente findById(Long id) {
		// TODO Auto-generated method stub
		Cliente cliente = new Cliente();
		cliente.setId(1L);
		cliente.setEmail("jhon.ochoa@openintl.com");
		cliente.setNombre("Jhon Mario Ochoa");
		return cliente;
	}

	@Override
	public List<Cliente> findAll() {
		// TODO Auto-generated method stub
		List<Cliente> clientes = new ArrayList<>();
		//Cliente 1
		Cliente cliente = new Cliente();
		cliente.setId(1L);
		cliente.setEmail("jhon.ochoa@openintl.com");
		cliente.setNombre("Jhon Mario Ochoa");
		//Cliente 2
		Cliente cliente2 = new Cliente();
		cliente.setId(2L);
		cliente.setEmail("jhon.ocho2a@openintl.com");
		cliente.setNombre("Jho2n Mario Ochoa");
		
		clientes.add(cliente);
		clientes.add(cliente2);
		return clientes;
	}

}
