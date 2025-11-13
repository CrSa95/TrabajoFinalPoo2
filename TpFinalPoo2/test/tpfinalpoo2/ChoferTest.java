package tpfinalpoo2;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class ChoferTest {

	@Test
	void dniDevuelveElValorCorrecto() {
		Chofer chofer = new Chofer("123456788");

		String dni = chofer.dni();

		assertEquals("123456788", dni);
	}
}
