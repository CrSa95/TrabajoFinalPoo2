package tpfinalpoo2;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class CoordenadaTest {
	Coordenadas coord1;
	Coordenadas coord2;
	
	@BeforeEach
	void setUp() throws Exception {
		coord1 = new Coordenadas(60d, 20d);
		coord2 = new Coordenadas(10d, 20d);
	}

	@Test
	void distanciaHacia() {
		assertEquals(50d, coord1.distanciaHacia(coord2));
	}



	@Test
	void unaCoordenadaSabeAvanzarEnXeY() {
		assertEquals(60d, coord1.coordX());
		assertEquals(20d, coord1.coordY());
		coord1.avanzarHacia(coord2);
		assertEquals(10d, coord1.coordX());
		assertEquals(20d, coord1.coordY());
	}

}
