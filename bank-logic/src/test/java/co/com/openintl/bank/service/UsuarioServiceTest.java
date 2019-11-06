package co.com.openintl.bank.service;

import static org.junit.jupiter.api.Assertions.*;

import java.math.BigDecimal;
import java.util.Optional;
import java.util.UUID;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import co.com.openintl.bank.domain.Usuario;
import co.com.openintl.bank.repository.TipoUsuarioRepository;
@ExtendWith(SpringExtension.class)
@ContextConfiguration("/applicationContext.xml")
@Rollback(true) // Realiza rollback de todo
class UsuarioServiceTest {

	@Autowired
	private UsuarioService usuarioService;
	
	@Autowired
	private TipoUsuarioRepository tipoUsuarioRepository;
	
	@BeforeEach
	public void setup() {
		assertNotNull(usuarioService);
		assertNotNull(tipoUsuarioRepository);
	}
	@Test
	@DisplayName("Save Usuario")
	void save() {
		String idUsuario =UUID.randomUUID().toString();
		Usuario usuario = new Usuario();
		usuario.setUsuUsuario(idUsuario);
		usuario.setClave("******");
		usuario.setIdentificacion(new BigDecimal(100));
		usuario.setNombre("Jhon");
		usuario.setActivo("S");
		
		assertTrue(tipoUsuarioRepository.findById(1L).isPresent());
		usuario.setTipoUsuario(tipoUsuarioRepository.findById(1L).get());
		
		try {
			usuarioService.save(usuario);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			assertNull(e, e.getMessage());
		}
	}
	
	@Test
	@DisplayName("Update Usuario")
	void update(){
		Optional<Usuario> usuario =  usuarioService.findById("aarnett2");
		assertTrue(usuario.isPresent());
		
		Usuario usuario2 = usuario.get();
		usuario2.setActivo("N");
		
		try {
			usuarioService.update(usuario2);
		} catch (Exception e) {
			assertNull(e, e.getMessage());
		}
	}
	
	@Test
	@DisplayName("Find by Id")
	void findById() {
		Optional<Usuario> usuairo = usuarioService.findById("aarnett2");
		assertTrue(usuairo.isPresent());	
	}
	
	@Test
	@DisplayName("Delete Usuario with transactions")
	void delete() {
		try {
			usuarioService.deleteById("aarnett2");;
		} catch (Exception e) {
			assertNotNull(e, e.getMessage());
		}
	} 	
}
