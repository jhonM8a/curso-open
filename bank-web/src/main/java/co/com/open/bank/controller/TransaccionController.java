package co.com.open.bank.controller;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import co.com.openintl.bank.dto.RetirarDTO;

@RestController
@RequestMapping("/transaccion")
public class TransaccionController {

	@PostMapping("/retirar")
	public ResponseEntity<?> retirar(@Valid @RequestBody RetirarDTO retirarDTO) {
		return null;
	}

}
