package co.com.openintl.bank.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import co.com.openintl.bank.domain.Cuenta;

public interface CuentaRepository extends JpaRepository<Cuenta, String>{

}
