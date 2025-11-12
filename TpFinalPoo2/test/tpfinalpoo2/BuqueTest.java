package tpfinalpoo2;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class BuqueTest {
	Buque suject;
	Tramo tramoActual;
	Viaje viaje;
	Terminal terminalSuject;
	EstadoGPS estadoGPS;
	Outbound estadoOutboundMock;

	@BeforeEach
	void setUp() throws Exception {
		tramoActual = mock(Tramo.class);
		terminalSuject = spy(Terminal.class);
		suject = new Buque();
		
		viaje = mock(Viaje.class);
		when(viaje.tramoInicial()).thenReturn(tramoActual);
		when(viaje.siguienteTramo(tramoActual)).thenReturn(tramoActual);
		suject.asignar(viaje);
		estadoGPS = mock(EstadoGPS.class);
		estadoOutboundMock = mock(Outbound.class);
		
	}


	@Test
	void sePuedeAvisarLlegadaAMenosDe50km() {
		when(tramoActual.distanciaHacia(null)).thenReturn(49d);
		suject.actualizarGPS();
		when(suject.destinoActual()).thenReturn(terminalSuject);
		assertDoesNotThrow(() -> suject.avisarLlegada());

		verify(terminalSuject).notificarClientes(suject);
	}

	@Test
	void sePuedeIniciarTrabajoEnLaTerminal() {
		when(tramoActual.distanciaHacia(null)).thenReturn(0d);
		suject.actualizarGPS();
		suject.actualizarGPS();
		assertDoesNotThrow(() -> suject.empezarTrabajo());
	}

	@Test
	void sePuedeIniciarPartidaEnEstadoWorking() {
		when(tramoActual.distanciaHacia(null)).thenReturn(0d);
		suject.actualizarGPS();
		suject.actualizarGPS();
		suject.empezarTrabajo();
		assertDoesNotThrow(() -> suject.permitirSalida());
	}
	
	@Test 
	void alPasarDeEstadoDepartingAInboundSeNotificaSalida() {
		Terminal terminal = spy(Terminal.class);
		Tramo siguienteTramo = mock(Tramo.class);
		when(viaje.siguienteTramo(tramoActual)).thenReturn(siguienteTramo); 
		when(siguienteTramo.getTerminalDestino()).thenReturn(terminal);
		
		when(tramoActual.distanciaHacia(null)).thenReturn(0d);
		suject.actualizarGPS();
		suject.actualizarGPS();
		suject.empezarTrabajo();
		suject.permitirSalida();
		suject.avisarPartida();
		verify(terminal).avisarPartida(suject);
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
	
	@Test
	void unBuqueConoceSuViaje() {
		suject.asignar(viaje);
		Assertions.assertEquals(viaje, suject.viaje());
	}

}
