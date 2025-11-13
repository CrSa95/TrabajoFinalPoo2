package tpfinalpoo2;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class CamionTest {

	@Test
	void patenteDevuelveElValorCorrecto() {
		Camion camion = new Camion("AA 123 AA");

		String patente = camion.patente();

		assertEquals("AA 123 AA", patente);
	}
}
