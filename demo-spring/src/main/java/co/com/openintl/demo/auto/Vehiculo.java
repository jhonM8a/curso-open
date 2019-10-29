package co.com.openintl.demo.auto;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Vehiculo {

	private Motor motor;
	private String color;
	private Integer modelo;
	
	private static final Logger LOGGER = LoggerFactory.getLogger(Vehiculo.class);
	
	public Motor getMotor() {
		return motor;
	}
	public void setMotor(Motor motor) {
		this.motor = motor;
	}
	public String getColor() {
		return color;
	}
	public void setColor(String color) {
		this.color = color;
	}
	public Integer getModelo() {
		return modelo;
	}
	public void setModelo(Integer modelo) {
		this.modelo = modelo;
	}
	
	@PostConstruct
	public void init() {
		LOGGER.info("Init Object: [vehiculo]");
	}
	@PreDestroy
	public void destroy() {
		LOGGER.info("Object destroy: [vehiculo]");

	}
}
