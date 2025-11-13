package tpfinalpoo2;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TramoTest {

	private Terminal origen;
	private Terminal destino;
	private double costo;
	private double duracion;
	private Tramo tramo;
	private Coordenadas coordenadasMock;

	@BeforeEach
	public void setUp() {
		origen = mock(Terminal.class);
		destino = mock(Terminal.class);
		costo = 20d;
		duracion = 10d;
		tramo = new Tramo(duracion, costo, origen, destino);
		coordenadasMock = mock(Coordenadas.class);
	}

	@Test
	void testUnTramoConoceSuCosto() {
		Assertions.assertEquals(20d, tramo.getCosto());
	}

	@Test
	void testUnTramoConoceSuDuracion() {
		Assertions.assertEquals(10d, tramo.getDuracion());
	}

	@Test
	void testUnTramoConoceSuTerminalDeOrigen() {
		when(origen.getNombre()).thenReturn("Buenos Aires");
		Assertions.assertTrue(tramo.tieneDeOrigenA(origen));
	}

	@Test
	void testUnTramoConoceSuTerminalDeDestino() {
		when(destino.getNombre()).thenReturn("Montevideo");
		Assertions.assertTrue(tramo.tieneDeDestinoA(destino));
	}

	@Test
	void testUnTramoConoceLasCoordenadasDeSuTerminalDestino() {

		when(destino.coordenadas()).thenReturn(coordenadasMock);

		Assertions.assertEquals(coordenadasMock, tramo.getTerminalDestinoCoordenadas());
	}
}
