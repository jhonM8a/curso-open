package co.com.openintl.bank.repository;

import static org.junit.jupiter.api.Assertions.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import co.com.openintl.bank.domain.Cliente;
import co.com.openintl.bank.domain.Usuario;

@ExtendWith(SpringExtension.class)
@ContextConfiguration("/applicationContext.xml")
@Rollback(true) //Realiza rollback de todo
class UsuairoRepositoryTest {
	
	@Autowired
	UsuarioRepository usuarioRepository;
	
	@Autowired
	TipoUsuarioRepository tipoUsuairo;
	
	String idUsuario;
	@BeforeEach
	void setup() {
		assertNotNull(usuarioRepository);
		assertNotNull(tipoUsuairo);
		

		this.idUsuario =UUID.randomUUID().toString();
		
	}
	
	@Test
	@DisplayName("Save Usuario")
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	void saveTest() {
		
		assertFalse(usuarioRepository.findById(idUsuario).isPresent());
		
		Usuario usuario = new Usuario();
		usuario.setUsuUsuario(idUsuario);
		usuario.setClave("******");
		usuario.setIdentificacion(new BigDecimal(100));
		usuario.setNombre("Jhon");
		usuario.setActivo("S");
		
		assertTrue(tipoUsuairo.findById(1L).isPresent());
		usuario.setTipoUsuario(tipoUsuairo.findById(1L).get());
		
		usuarioRepository.save(usuario);

		
	}
	
	@Test
	@DisplayName("Find Usuario with Id")
	void findById() {
		String usu = "kdaubney1"; //Cliente debe existir
		assertTrue(usuarioRepository.findById(usu).isPresent());
	}
	
	@Test
	@DisplayName("Update Usuario")
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	void update() {
		String usu = "kdaubney1"; //Cliente debe existir
		assertTrue(usuarioRepository.findById(usu).isPresent());
		
		Usuario usuario =  usuarioRepository.findById(usu).get();
		usuario.setActivo("N");
		
		usuarioRepository.save(usuario);
	}
	
	@Test
	@DisplayName("Delete Usuario")
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	void delete() {
		String usu = "kdaubney1"; //Cliente debe existir
		assertTrue(usuarioRepository.findById(usu).isPresent());
		Usuario usuairo = usuarioRepository.findById(usu).get();
		usuarioRepository.delete(usuairo);
	}
	
	
	@Test
	@DisplayName("find all usuarios")
	void findAll() {
		List<Usuario> usuarios = usuarioRepository.findAll();
		assertFalse(usuarios.isEmpty());
	}
}
