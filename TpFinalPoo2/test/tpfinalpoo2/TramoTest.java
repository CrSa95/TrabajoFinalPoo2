package tpfinalpoo2;

import static org.mockito.Mockito.*;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class TramoTest {
	
	Terminal origen = mock(Terminal.class);
	OtraTerminal destino = mock(OtraTerminal.class);
	double costo = 20d;
	double duracion = 10d;
	Tramo tramo = new Tramo(duracion, costo, origen, destino);
	
	@Test
	void testUnTramoConoceSuCosto() {
		Assertions.assertEquals(20d,tramo.getCosto());
	}
	
	@Test
	void testUnTramoConoceSuDuracion() {
		Assertions.assertEquals(10d,tramo.getDuracion());
	}
	
	@Test
	void testUnTramoConoceSuTerminalDeOrigen() {
		when(origen.getNombre()).thenReturn("Buenos Aires");
		Assertions.assertEquals("Buenos Aires",origen.getNombre());
	}
	
	@Test
	void testUnTramoConoceSuTerminalDeDestino() {
		when(origen.getNombre()).thenReturn("Montevideo");
		Assertions.assertEquals("Montevideo",destino.getNombre());
	}
}
