package co.com.open.bank.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/operacion/")
public class OperacionesMatematicas {

	@GetMapping("sumar/{n1}/{n2}")
	public Integer sumar(@PathVariable("n1") Integer numeroUno, @PathVariable("n2") Integer numeroDos) {
		return numeroUno + numeroDos;
	}

}
