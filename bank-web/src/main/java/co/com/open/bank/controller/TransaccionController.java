package co.com.open.bank.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import co.com.openintl.bank.dto.RetirarDTO;
import co.com.openintl.bank.service.TransaccionService;

@RestController
@RequestMapping("/transaccion")
public class TransaccionController {

	@Autowired
	private TransaccionService transaccionService;

	@PostMapping("/retirar")
	public ResponseEntity<?> retirar(@Valid @RequestBody RetirarDTO retirarDTO) {
		try {
			Long idTransaccionLong = transaccionService.retirar(retirarDTO.getCuenId(), retirarDTO.getValor(),
					retirarDTO.getUsuUsuario());
			return ResponseEntity.ok().body(idTransaccionLong);
		} catch (Exception e) {
			return ResponseEntity.badRequest().body(new ResponseError("400", e.getMessage()));

		}

	}

}
