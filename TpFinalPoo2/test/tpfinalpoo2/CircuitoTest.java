package tpfinalpoo2;

import static org.mockito.Mockito.*;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class CircuitoTest {
	
	Circuito circuito = new Circuito();
	Tramo tramo = mock(Tramo.class);
	Tramo otroTramo = mock(Tramo.class);
	
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
}
