package tpfinalpoo2;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class DesconsolidadoTest {
	Desconsolidado suject;
	double precio_fijo = 54d;
	@BeforeEach
	void setUp() throws Exception {
		suject = new Desconsolidado(precio_fijo);
	}

	@Test
	void desconsolidadoCosto() {
		assertEquals(precio_fijo, suject.costo(null));
	}

}
