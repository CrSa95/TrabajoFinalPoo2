package tpfinalpoo2;

import static org.mockito.Mockito.*;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class CircuitoTest {
	
	private Circuito circuito;
	private Tramo tramo;
	private Tramo otroTramo;
	private Terminal otraTerminal;
	
	@BeforeEach
    public void setUp() {
		circuito = new Circuito();
		tramo = mock(Tramo.class);
		otroTramo = mock(Tramo.class);
		otraTerminal = mock(Terminal.class);
    }
	
	@Test
	void testUnCircuitoPuedeAgregarUnTramo() {
		Assertions.assertTrue(circuito.getTramos().isEmpty());
		circuito.agregarTramo(tramo);
		Assertions.assertEquals(1, circuito.getTramos().size());
		circuito.agregarTramo(otroTramo);
		Assertions.assertEquals(2, circuito.getTramos().size());
		Assertions.assertFalse(circuito.getTramos().isEmpty());
	}
	
	@Test
	void testUnCircuitoPuedeCalcularSuCosto() {
		circuito.agregarTramo(tramo);
		circuito.agregarTramo(otroTramo);
		
		when(tramo.getCosto()).thenReturn(10d);
		when(otroTramo.getCosto()).thenReturn(20d);
		
		Assertions.assertEquals(30, circuito.costoCircuito());
	}
	
	@Test
	void testUnCircuitoPuedeCalcularElTiempoDeRecorrido() {
		circuito.agregarTramo(tramo);
		circuito.agregarTramo(otroTramo);
		
		when(tramo.getDuracion()).thenReturn(10d);
		when(otroTramo.getDuracion()).thenReturn(20d);
		
		Assertions.assertEquals(30, circuito.tiempoTotal());
	}
	
	@Test
	void testUnCircuitoPuedeConocerLosSubtramosHastaUnaTerminal() {
		circuito.agregarTramo(tramo);
		circuito.agregarTramo(otroTramo);
		
		when(otraTerminal.getNombre()).thenReturn("unNombre");
		when(tramo.getTerminalDestino()).thenReturn(otraTerminal);
		
		Assertions.assertTrue(circuito.subTramosHasta(otraTerminal).contains(tramo));
	}
	
	@Test
	void testUnCircuitoPuedeCalcularElCostoTotalDeUnSubtramo() {
		circuito.agregarTramo(tramo);
		circuito.agregarTramo(otroTramo);
		
		when(otraTerminal.getNombre()).thenReturn("unNombre");
		when(tramo.getTerminalDestino()).thenReturn(otraTerminal);
		when(tramo.getCosto()).thenReturn(10d);
		
		Assertions.assertEquals(10d,circuito.costoTotalHasta(otraTerminal));
	}
	
	@Test
	void testUnCircuitoPuedeCalcularElTiempoTotalDeUnSubtramo() {
		circuito.agregarTramo(tramo);
		circuito.agregarTramo(otroTramo);
		
		when(otraTerminal.getNombre()).thenReturn("unNombre");
		when(tramo.getTerminalDestino()).thenReturn(otraTerminal);
		when(tramo.getDuracion()).thenReturn(10d);
		
		Assertions.assertEquals(10d,circuito.tiempoTotalHasta(otraTerminal));
	}
	
	@Test
	void testUnCircuitoPuedeInformarSiParteDeUnaTerminal() {
		circuito.agregarTramo(tramo);
		circuito.agregarTramo(otroTramo);
		
		when(otraTerminal.getNombre()).thenReturn("unNombre");
		when(tramo.getTerminalOrigen()).thenReturn(otraTerminal);
		
		Assertions.assertTrue(circuito.parteDesde(otraTerminal));
	}
}
