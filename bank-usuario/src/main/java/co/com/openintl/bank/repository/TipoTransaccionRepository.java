package co.com.openintl.bank.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import co.com.openintl.bank.domain.TipoTransaccion;

public interface TipoTransaccionRepository extends JpaRepository<TipoTransaccion, Long>{

}
