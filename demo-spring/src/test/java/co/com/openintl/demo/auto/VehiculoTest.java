package co.com.openintl.demo.auto;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class VehiculoTest {

	private final static Logger log = LoggerFactory.getLogger(VehiculoTest.class);
	private ApplicationContext applicationContext;
	
	@BeforeEach
	void setup() {
		applicationContext = new ClassPathXmlApplicationContext("./appContextUno.xml");
	}
	
	@Test
	@DisplayName("Inyección Motor")
	void inyeccionMotor() {
		
		assertNotNull(applicationContext, "El context esta nulo");

		Motor motor = (Motor) applicationContext.getBean("motor");
		assertNotNull(motor);

		assertNotNull(motor.getCilindraje());
		assertNotNull(motor.getElectrico());
		assertNotNull(motor.getSerial());

		log.info("cilindraje: " + motor.getCilindraje());
	}

	@Test
	void inyeccionVehiculo() {
		assertNotNull(applicationContext, "El context esta nulo");

		Vehiculo vehiculo = (Vehiculo) applicationContext.getBean("vehiculo");
		assertNotNull(vehiculo);
		assertNotNull(vehiculo.getMotor());
	
	}
	
	@Test
	void inyeccionVehiculoProyotype() {
		Motor motorUno = (Motor) applicationContext.getBean("motor");
		Motor motorDos = (Motor) applicationContext.getBean("motor");
		
		assertNotEquals(motorUno, motorDos);

	}
	
	@Test
	void inyeccionVehiculoSingleton() {
		Motor motorUno = (Motor) applicationContext.getBean("motorSingleton");
		Motor motorDos = (Motor) applicationContext.getBean("motorSingleton");
		
		assertEquals(motorUno, motorDos);

	
	}
}
