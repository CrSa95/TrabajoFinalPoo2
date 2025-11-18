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
		coord2 = mock(Coordenadas.class);
	}

	@Test
	void distanciaHacia() {
		when(coord2.coordX()).thenReturn(10d);
		when(coord2.coordY()).thenReturn(20d);
		assertEquals(50d, coord1.distanciaHacia(coord2));
	}

	@Test
	void unaCoordenadaSabeAvanzarEnXeY() {
		coord1.avanzarHacia(10d, 10d);
		when(coord2.coordX()).thenReturn(70d);
		when(coord2.coordY()).thenReturn(30d);
		assertEquals(0d, coord1.distanciaHacia(coord2));
	}

}
