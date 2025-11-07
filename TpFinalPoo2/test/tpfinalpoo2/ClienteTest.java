package tpfinalpoo2;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ClienteTest {
	
	private Cliente cliente;
	private static final String NOMBRE = "Facundo Pérez";
	private static final String DNI = "12345678";
	
	@BeforeEach
	void setUp() {
		this.cliente = new Cliente(NOMBRE, DNI);
	}
	
	@Test
	void DevuelveElDniCorrecto() {
		assertEquals(DNI, this.cliente.dni());
	}
	
	@Test
	void DevuelveElNombreCorrecto() {
		assertEquals(NOMBRE, this.cliente.nombre());
	}
}