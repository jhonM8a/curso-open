package co.com.openintl.bank.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import co.com.openintl.bank.domain.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, String>{

}
