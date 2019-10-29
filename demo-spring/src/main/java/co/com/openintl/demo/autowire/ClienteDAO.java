package co.com.openintl.demo.autowire;

import java.util.List;

public interface ClienteDAO {
	
	public Cliente findById(Long id);
	public List<Cliente> findAll();
}
