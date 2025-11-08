package tpfinalpoo2;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.time.LocalDate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ViajeTest {
	Viaje suject;
	Circuito circuito;
	LocalDate fechaSalida = LocalDate.now();

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
	void terminalDestino() {
		when(circuito.destinoActual()).thenReturn(terminalOrigen);
		assertEquals(terminalOrigen, suject.getTerminalDestino());

		when(circuito.destinoActual()).thenReturn(terminalIntermedia);
		assertEquals(terminalIntermedia, suject.getTerminalDestino());

		when(circuito.destinoActual()).thenReturn(terminalFinal);
		assertEquals(terminalFinal, suject.getTerminalDestino());
	}

	@Test
	void siguienteTramo() {
		when(circuito.tramoInicial()).thenReturn(tramoInicial);

		assertEquals(tramoInicial, suject.tramoInicial());

		when(circuito.siguienteTramo(tramoInicial)).thenReturn(siguienteTramo);

		assertEquals(siguienteTramo, suject.siguienteTramo(tramoInicial));

	}
}
