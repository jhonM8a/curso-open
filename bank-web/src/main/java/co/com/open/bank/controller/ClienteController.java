package co.com.open.bank.controller;


import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import co.com.openintl.bank.domain.Cliente;
import co.com.openintl.bank.service.ClienteService;

@RestController
@RequestMapping("/cliente")
public class ClienteController {
	
	@Autowired
	ClienteService clienteService;

	@GetMapping("/findById/{id}")
	public ResponseEntity<?> findByid(@PathVariable("id") Long id) {
		Optional<Cliente> clienteOptional = clienteService.findById(id);
		
		if(!clienteOptional.isPresent()) {
			return ResponseEntity.ok("");
		}
		
		return null;
	}
}
