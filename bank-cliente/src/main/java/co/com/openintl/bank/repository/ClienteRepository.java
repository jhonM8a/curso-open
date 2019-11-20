package co.com.openintl.bank.repository;



import org.springframework.data.jpa.repository.JpaRepository;

import co.com.openintl.bank.domain.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, Long>{

}
