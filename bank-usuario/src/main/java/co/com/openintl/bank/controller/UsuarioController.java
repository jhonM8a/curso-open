package co.com.openintl.bank.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import co.com.openintl.bank.domain.Usuario;
import co.com.openintl.bank.dto.UsuarioDTO;
import co.com.openintl.bank.mapper.UsuarioMapper;
import co.com.openintl.bank.service.UsuarioService;

@RestController
@RequestMapping("/usuario")
public class UsuarioController {

	@Autowired
	private  UsuarioService usuarioService;

	@Autowired
	private UsuarioMapper usuarioMapper;
	
	
	
	
	@GetMapping("/findById/{id}")
	public ResponseEntity<?> findByid(@PathVariable("id") String id) {
		Optional<Usuario> usuarioOptional = usuarioService.findById(id);

		if (!usuarioOptional.isPresent()) {
			return ResponseEntity.badRequest().body("");
		}

		Usuario usuario = usuarioOptional.get();
		UsuarioDTO usuarioDTO = usuarioMapper.enityToDTO(usuario);

		return ResponseEntity.ok().body(usuarioDTO);
	}
	
	
	
	@GetMapping("/findAll")
	public ResponseEntity<?> findAll(){
		List<Usuario> usuarios = usuarioService.findAll();
		List<UsuarioDTO> usuariosDTO = usuarioMapper.toUsuariosDTO(usuarios);
		
		
		return ResponseEntity.ok().body(usuariosDTO);
	}
	
	@PostMapping("/save")
	public ResponseEntity<?> save(@Valid @RequestBody UsuarioDTO usuarioDTO){
		
		try {
			Usuario usuario = usuarioMapper.dtoToEntity(usuarioDTO);
			usuario=usuarioService.save(usuario);
			usuarioDTO=usuarioMapper.enityToDTO(usuario);
			return ResponseEntity.ok().body(usuarioDTO);
		}catch (Exception e) {
			return ResponseEntity.badRequest().body(new ResponseError("400", e.getMessage()));
		}
	}
	
	
	@PutMapping("/update")
	public ResponseEntity<?> update(@Valid @RequestBody UsuarioDTO usuarioDTO){
		
		try {
			Usuario usuario = usuarioMapper.dtoToEntity(usuarioDTO);
			usuario=usuarioService.update(usuario);
			usuarioDTO=usuarioMapper.enityToDTO(usuario);
			return ResponseEntity.ok().body(usuarioDTO);
		}catch (Exception e) {
			return ResponseEntity.badRequest().body(new ResponseError("400", e.getMessage()));
		}
	}	
	
	
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<?> delete(@PathVariable("id") String id){
		
		try {
			usuarioService.deleteById(id);
			return ResponseEntity.ok("");
		}catch (Exception e) {
			return ResponseEntity.badRequest().body(new ResponseError("400", e.getMessage()));
		}
	}	
	
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public Map<String, String> handleValidationExceptions(
	  MethodArgumentNotValidException ex) {
	    Map<String, String> errors = new HashMap<>();
	    ex.getBindingResult().getAllErrors().forEach((error) -> {
	        String fieldName = ((FieldError) error).getField();
	        String errorMessage = error.getDefaultMessage();
	        errors.put(fieldName, errorMessage);
	    });
	    return errors;
	}
	
}
