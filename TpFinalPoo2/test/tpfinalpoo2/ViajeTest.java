package tpfinalpoo2;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.time.LocalDateTime;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ViajeTest {
	Viaje suject;
	Circuito circuito;
	LocalDateTime fechaSalida = LocalDateTime.now();

	Tramo tramoInicial;
	Tramo siguienteTramo;
	Terminal terminalOrigen = new TerminalGestionada(null, null);
	Terminal terminalIntermedia = new TerminalGestionada(null, null);
	Terminal terminalFinal = new TerminalGestionada(null, null);

	@BeforeEach
	void setUp() throws Exception {
		circuito = mock(Circuito.class);
		suject = new Viaje(circuito, fechaSalida);
		when(circuito.terminalOrigen()).thenReturn(terminalOrigen);
	}

	@Test
	void proximaFechaTest() {
		Long dias_esperados = 5l;
		when(circuito.tiempoTotalDesdeHasta(terminalOrigen, terminalFinal)).thenReturn((double) dias_esperados);
		assertEquals(fechaSalida.plusDays(dias_esperados), suject.proximaFecha(terminalOrigen, terminalFinal));
	}

	@Test
	void proximaFechaLanzaErrorSiNoExistenLasTerminalesEnElCircuito() {

		when(circuito.tiempoTotalDesdeHasta(any(Terminal.class), any(Terminal.class)))
				.thenThrow(new IllegalArgumentException("Origen y destino inexistentes en el circuito"));
		assertThrows(IllegalArgumentException.class, () -> suject.proximaFecha(terminalOrigen, terminalFinal),
				"Origen y destino inexistentes en el circuito");
	}

	@Test
	void costoViaje() {
		assertEquals(0d, suject.costo());
		when(circuito.costoCircuito()).thenReturn(5d);
		assertEquals(5d, suject.costo());
	}

	@Test
	void fechaLlegadaViaje() {
		when(circuito.tiempoTotal()).thenReturn(0d);
		assertEquals(fechaSalida, suject.getFechaLlegada());
		when(circuito.tiempoTotal()).thenReturn(5d);
		assertEquals(fechaSalida.plusDays(5), suject.getFechaLlegada());
	}

	@Test
	void fechaInicio() {
		assertEquals(fechaSalida, suject.getFechaSalida());
	}

	@Test
	void fechaLlegadaHaciaTerminal() {

		when(circuito.tiempoHaciaTerminalDesdeOrigen(terminalOrigen)).thenReturn(0l);
		assertEquals(fechaSalida, suject.fechaLlegada(terminalOrigen));

		when(circuito.tiempoHaciaTerminalDesdeOrigen(terminalIntermedia)).thenReturn(2l);
		assertEquals(fechaSalida.plusDays(2l), suject.fechaLlegada(terminalIntermedia));

		when(circuito.tiempoHaciaTerminalDesdeOrigen(terminalFinal)).thenReturn(5l);
		assertEquals(fechaSalida.plusDays(5l), suject.fechaLlegada(terminalFinal));
	}

	@Test
	void viajeConoceLaTerminalDeDestino() {
		when(circuito.destinoActual()).thenReturn(terminalFinal);
		assertEquals(terminalFinal, suject.getTerminalDestino());
	}

	@Test
	void viajeConoceElSiguienteTramo() {
		when(circuito.siguienteTramo(tramoInicial)).thenReturn(siguienteTramo);
		assertEquals(siguienteTramo, suject.siguienteTramo(siguienteTramo));
	}

	@Test
	void viajeConoceSuTramoInicial() {
		when(circuito.tramoInicial()).thenReturn(tramoInicial);
		assertEquals(tramoInicial, suject.tramoInicial());
	}

}
