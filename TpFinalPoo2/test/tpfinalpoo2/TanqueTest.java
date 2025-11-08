package tpfinalpoo2;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class TanqueTest {
	String clienteId = "ABCD";
	Double altura = 50d;
	Double ancho = 50d;
	Double largo = 50d;
	Double peso = 50d;
	Tanque suject;
	Cliente cliente;

	@BeforeEach
	void setUp() throws Exception {
		suject = new Tanque(clienteId, altura, ancho, largo, peso);
	}

	@Test
	void idContainer() {
		assertTrue(suject.id().startsWith(clienteId));
		assertEquals(11, suject.id().length());
	}

	@Test
	void idUnicoConElmismoDueño() {

		Container otroContainer = new Tanque(clienteId, altura, ancho, largo, peso);
		assertNotEquals(suject.id(), otroContainer.id());
	}

	@Test
	void metrosCubicos() {
		assertEquals(altura * ancho * largo, suject.metrosCubicos());
	}

	@Test
	void peso() {
		assertEquals(peso, suject.peso());
	}

}
