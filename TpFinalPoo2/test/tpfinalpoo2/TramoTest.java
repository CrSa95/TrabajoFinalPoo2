package tpfinalpoo2;

import static org.mockito.Mockito.*;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TramoTest {
	
	private Terminal origen;
	private OtraTerminal destino;
	private double costo;
	private double duracion;
	private Tramo tramo;
	
	@BeforeEach
    public void setUp() {
		origen = mock(Terminal.class);
		destino = mock(OtraTerminal.class);
		costo = 20d;
		duracion = 10d;
		tramo = new Tramo(duracion, costo, origen, destino);
    }
	
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
		Assertions.assertEquals(tramo.getTerminalOrigen().getNombre(),origen.getNombre());
	}
	
	@Test
	void testUnTramoConoceSuTerminalDeDestino() {
		when(destino.getNombre()).thenReturn("Montevideo");
		Assertions.assertEquals(tramo.getTerminalDestino().getNombre(),destino.getNombre());
	}
}
