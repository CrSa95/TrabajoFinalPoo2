package tpfinalpoo2;

import static org.junit.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class BuqueTest {
	Buque suject;
	Tramo tramoActual;
	Viaje viaje;
	Terminal terminalSuject;

	@BeforeEach
	void setUp() throws Exception {
		terminalSuject = spy(Terminal.class);
		suject = new Buque();
		tramoActual = mock(Tramo.class);
		viaje = mock(Viaje.class);
		when(viaje.tramoInicial()).thenReturn(tramoActual);
		when(viaje.siguienteTramo(tramoActual)).thenReturn(tramoActual);
		suject.asignar(viaje);
	}

	void setearAOutbound() {
		when(tramoActual.distanciaHacia(null)).thenReturn(49d);
		suject.actualizarGPS();
	}

	void setearAArrived() {
		this.setearAOutbound();
		when(tramoActual.distanciaHacia(null)).thenReturn(0d);
		suject.actualizarGPS();
	}

	void setearAWorking() {
		this.setearAArrived();
		suject.empezarTrabajo();
	}

	void setearDeparting() {
		this.setearAWorking();
		suject.permitirSalida();
	}

	@Test
	void sePuedeAvisarLlegadaAMenosDe50km() {
		this.setearAOutbound();
		when(suject.destinoActual()).thenReturn(terminalSuject);
		assertDoesNotThrow(() -> suject.avisarLlegada());

		verify(terminalSuject).notificarClientes(suject);
	}

	@Test
	void sePuedeIniciarTrabajoEnLaTerminal() {
		this.setearAArrived();
		assertDoesNotThrow(() -> suject.empezarTrabajo());
	}

	@Test
	void sePuedeIniciarPartidaEnEstadoWorking() {
		this.setearAWorking();
		assertDoesNotThrow(() -> suject.permitirSalida());
	}

	@Test
	void iniciarTrabajoLanzaExcepcionSiNoEstaEnModoArrived() {
		assertThrows(RuntimeException.class, () -> suject.empezarTrabajo());
	}

	@Test
	void permitirSalidaLanzaExcepcionSiNoEstaEnModoWorking() {
		assertThrows(RuntimeException.class, () -> suject.permitirSalida());
	}

	@Test
	void avisarLLegadaLanzaExcepcionSiNoEstaEnModoOutbound() {
		assertThrows(RuntimeException.class, () -> suject.avisarLlegada());
	}

	@Test
	void salirLanzaExcepcionSiNoEstaEnModoDeparting() {
		assertThrows(RuntimeException.class, () -> suject.salir());
	}
}
