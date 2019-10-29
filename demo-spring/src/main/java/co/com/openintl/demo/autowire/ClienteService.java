package co.com.openintl.demo.autowire;

import java.util.List;

public interface ClienteService {
	
	public Cliente findById(Long id);

	public List<Cliente> findAll();
}
