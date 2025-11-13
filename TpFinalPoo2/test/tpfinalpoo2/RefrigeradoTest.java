package tpfinalpoo2;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class RefrigeradoTest {

	Refrigerado refrigerado;

	@BeforeEach
	void setUp() {
		refrigerado = new Refrigerado("Dueño", 1d, 1d, 1d, 1d, 1d, 1d);
	}

	@Test
	void unContainerRefrigeradoConoceSuConsumoYLaTemperaturaRequerida() {
		assertEquals(1d, refrigerado.consumoKwHora());
		assertEquals(1d, refrigerado.temperaturaRequerida());
	}
}
