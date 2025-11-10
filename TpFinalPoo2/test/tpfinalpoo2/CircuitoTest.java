package tpfinalpoo2;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class CircuitoTest {

	private Circuito circuito;
	private Tramo primerTramo;
	private Tramo segundoTramo;
	private Tramo tercerTramo;
	private Tramo cuartoTramo;
	private TerminalGestionada terminalOrigen;
	private TerminalGestionada terminalDestino;

	@BeforeEach
	public void setUp() {
		circuito = new Circuito();
		primerTramo = mock(Tramo.class);
		segundoTramo = mock(Tramo.class);
		tercerTramo = mock(Tramo.class);
		cuartoTramo = mock(Tramo.class);
		terminalOrigen = mock(TerminalGestionada.class);
		terminalDestino = mock(TerminalGestionada.class);
	}

	@Test
	void siguienteTramoTest() {
		circuito.agregarTramo(primerTramo);
		circuito.agregarTramo(segundoTramo);
		circuito.agregarTramo(tercerTramo);

		assertEquals(segundoTramo, circuito.siguienteTramo(primerTramo));
		assertEquals(tercerTramo, circuito.siguienteTramo(segundoTramo));
	}

	@Test
	void siguienteTramoDelUltimoDevuelveElPrimero() {
		circuito.agregarTramo(primerTramo);
		circuito.agregarTramo(segundoTramo);
		circuito.agregarTramo(tercerTramo);

		assertEquals(primerTramo, circuito.siguienteTramo(tercerTramo));
	}

	@Test
	void testUnCircuitoPuedeAgregarUnTramo() {
		Assertions.assertTrue(circuito.getTramos().isEmpty());
		circuito.agregarTramo(primerTramo);
		Assertions.assertEquals(1, circuito.getTramos().size());
		circuito.agregarTramo(segundoTramo);
		Assertions.assertEquals(2, circuito.getTramos().size());
		Assertions.assertFalse(circuito.getTramos().isEmpty());
	}

	@Test
	void testUnCircuitoPuedeCalcularSuCosto() {

		circuito.agregarTramo(primerTramo);
		circuito.agregarTramo(segundoTramo);

		when(primerTramo.getCosto()).thenReturn(10d);
		when(segundoTramo.getCosto()).thenReturn(20d);

		Assertions.assertEquals(30, circuito.costoCircuito());
	}

	@Test
	void testUnCircuitoPuedeCalcularElTiempoDeRecorrido() {

		circuito.agregarTramo(primerTramo);
		circuito.agregarTramo(segundoTramo);

		when(primerTramo.getDuracion()).thenReturn(10d);
		when(segundoTramo.getDuracion()).thenReturn(20d);

		Assertions.assertEquals(30, circuito.tiempoTotal());
	}

	@Test
	void testUnCircuitoPuedeCalcularElCostoTotalDeUnSubtramo() {
		circuito.agregarTramo(primerTramo);
		circuito.agregarTramo(segundoTramo);
		circuito.agregarTramo(tercerTramo);

		when(terminalOrigen.getNombre()).thenReturn("unNombre");
		when(terminalDestino.getNombre()).thenReturn("otroNombre");
		when(primerTramo.tieneDeOrigenA(terminalOrigen)).thenReturn(true);
		when(segundoTramo.tieneDeDestinoA(terminalDestino)).thenReturn(true);
		when(primerTramo.getTerminalOrigen()).thenReturn(terminalOrigen);
		when(segundoTramo.getTerminalDestino()).thenReturn(terminalDestino);
		when(primerTramo.getCosto()).thenReturn(10d);
		when(segundoTramo.getCosto()).thenReturn(10d);

		Assertions.assertEquals(20d, circuito.costoTotalDesdeHasta(terminalOrigen, terminalDestino));
	}

	@Test
	void testUnCircuitoPuedeCalcularElTiempoTotalDeUnSubtramo() {
		circuito.agregarTramo(primerTramo);
		circuito.agregarTramo(segundoTramo);
		circuito.agregarTramo(tercerTramo);

		when(terminalOrigen.getNombre()).thenReturn("unNombre");
		when(terminalDestino.getNombre()).thenReturn("otroNombre");
		when(primerTramo.tieneDeOrigenA(terminalOrigen)).thenReturn(true);
		when(segundoTramo.tieneDeDestinoA(terminalDestino)).thenReturn(true);
		when(primerTramo.getTerminalOrigen()).thenReturn(terminalOrigen);
		when(segundoTramo.getTerminalDestino()).thenReturn(terminalDestino);
		when(primerTramo.getDuracion()).thenReturn(10d);
		when(segundoTramo.getDuracion()).thenReturn(10d);

		Assertions.assertEquals(20d, circuito.tiempoTotalDesdeHasta(terminalOrigen, terminalDestino));
	}

	@Test
	void testUnCircuitoPuedeCalcularTerminalesIntermediasDesdeUnaTerminalAOtra() {
		circuito.agregarTramo(primerTramo);
		circuito.agregarTramo(segundoTramo);
		circuito.agregarTramo(tercerTramo);
		circuito.agregarTramo(cuartoTramo);

		when(terminalOrigen.getNombre()).thenReturn("unNombre");
		when(terminalDestino.getNombre()).thenReturn("otroNombre");
		when(primerTramo.tieneDeOrigenA(terminalOrigen)).thenReturn(true);
		when(tercerTramo.tieneDeDestinoA(terminalDestino)).thenReturn(true);
		when(primerTramo.getTerminalOrigen()).thenReturn(terminalOrigen);
		when(tercerTramo.getTerminalDestino()).thenReturn(terminalDestino);

		Assertions.assertEquals(1, circuito.terminalesIntermediasDesdeHasta(terminalOrigen, terminalDestino));
	}

	@Test
	void testUnCircuitoPuedeInformarSiExisteUnRecorridoEntreTerminales() {
		circuito.agregarTramo(primerTramo);
		circuito.agregarTramo(segundoTramo);
		circuito.agregarTramo(tercerTramo);
		circuito.agregarTramo(cuartoTramo);

		Assertions.assertThrows(Exception.class, () -> {
			circuito.existeRecorridoEntre(terminalOrigen, terminalDestino);
		});

		when(terminalOrigen.getNombre()).thenReturn("unNombre");
		when(terminalDestino.getNombre()).thenReturn("otroNombre");
		when(primerTramo.tieneDeOrigenA(terminalOrigen)).thenReturn(true);
		when(tercerTramo.tieneDeDestinoA(terminalDestino)).thenReturn(true);
		when(primerTramo.getTerminalOrigen()).thenReturn(terminalOrigen);
		when(tercerTramo.getTerminalDestino()).thenReturn(terminalDestino);

		Assertions.assertDoesNotThrow(() -> circuito.existeRecorridoEntre(terminalOrigen, terminalDestino));
	}
	
	@Test
	void testUnCircuitoPuedeCalcularElTiempoDesdeSuTerminalOrigenHastaOtra() {
		circuito.agregarTramo(primerTramo);
		circuito.agregarTramo(segundoTramo);
		circuito.agregarTramo(tercerTramo);

		when(terminalOrigen.getNombre()).thenReturn("unNombre");
		when(terminalDestino.getNombre()).thenReturn("otroNombre");
		when(primerTramo.tieneDeOrigenA(terminalOrigen)).thenReturn(true);
		when(segundoTramo.tieneDeDestinoA(terminalDestino)).thenReturn(true);
		when(primerTramo.getTerminalOrigen()).thenReturn(terminalOrigen);
		when(segundoTramo.getTerminalDestino()).thenReturn(terminalDestino);
		when(primerTramo.getDuracion()).thenReturn(10d);
		when(segundoTramo.getDuracion()).thenReturn(10d);

		Assertions.assertEquals(20l, circuito.tiempoHaciaTerminalDesdeOrigen(terminalDestino));
	}
	
	@Test
	void testUnCircuitoConoceElTiempoQueLeLlevaIrASuSiguienteTramo() {
		circuito.agregarTramo(primerTramo);
		circuito.agregarTramo(segundoTramo);
		circuito.agregarTramo(tercerTramo);

		when(terminalOrigen.getNombre()).thenReturn("unNombre");
		when(terminalDestino.getNombre()).thenReturn("otroNombre");
		when(primerTramo.tieneDeOrigenA(terminalOrigen)).thenReturn(true);
		when(segundoTramo.tieneDeDestinoA(terminalDestino)).thenReturn(true);
		when(primerTramo.getTerminalOrigen()).thenReturn(terminalOrigen);
		when(segundoTramo.getTerminalDestino()).thenReturn(terminalDestino);
		when(primerTramo.getDuracion()).thenReturn(10d);
		when(segundoTramo.getDuracion()).thenReturn(10d);

		Assertions.assertEquals(10d, circuito.tiempoHaciaDestinoActual());
	}
	
	@Test
	void testUnCircuitoConoceSuDestinoActual() {
		circuito.agregarTramo(primerTramo);

		when(terminalOrigen.getNombre()).thenReturn("unNombre");
		when(terminalDestino.getNombre()).thenReturn("otroNombre");
		when(primerTramo.tieneDeOrigenA(terminalOrigen)).thenReturn(true);
		when(primerTramo.tieneDeDestinoA(terminalDestino)).thenReturn(true);
		when(primerTramo.getTerminalDestino()).thenReturn(terminalDestino);

		Assertions.assertEquals(terminalDestino, circuito.destinoActual());
	}
	
	@Test
	void testUnCircuitoConoceSuOrigenActual() {
		circuito.agregarTramo(primerTramo);
		
		when(primerTramo.tieneDeOrigenA(terminalOrigen)).thenReturn(true);
		when(primerTramo.getTerminalOrigen()).thenReturn(terminalOrigen);

		Assertions.assertEquals(terminalOrigen, circuito.origenActual());
	}

}
