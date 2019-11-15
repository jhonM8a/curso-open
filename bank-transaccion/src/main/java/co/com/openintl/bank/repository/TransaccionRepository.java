package co.com.openintl.bank.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import co.com.openintl.bank.domain.Transaccion;

public interface TransaccionRepository extends JpaRepository<Transaccion, Long>{

}
