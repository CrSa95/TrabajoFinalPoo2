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
	Tramo tramo_inicial;
	Viaje viaje;
	Terminal terminal_destino;
	Terminal terminal_origen;

	@BeforeEach
	void setUp() throws Exception{
		suject = new Buque();
		viaje = mock(Viaje.class);
		terminal_destino = mock(Terminal.class);
		terminal_origen = mock(Terminal.class);
		tramo_inicial   = mock(Tramo.class);
		when(viaje.tramoInicial()).thenReturn(tramo_inicial);
		when(tramo_inicial.getTerminalDestino()).thenReturn(terminal_destino);
		when(tramo_inicial.getTerminalOrigen()).thenReturn(terminal_origen);
	}
	
	@Test 
	void iniciarUnViajeAvisaALaTerminalDestino() {
		suject.asignar(viaje);
		suject.iniciarViaje();
	}
	
	
	@Test
	void iniciarTrabajoLanzaExcepcionSiNoEstaEnModoArrived() {
		assertThrows(RuntimeException.class, () -> suject.empezarTrabajo(), "No se puede iniciar trabajo actualmente; muy lejos de la terminal");
	}

	@Test
	void permitirSalidaLanzaExcepcionSiNoEstaEnModoWorking() {
		assertThrows(RuntimeException.class, () -> suject.permitirSalida(), "El buque ya esta en un viaje");
	}

	@Test
	void avisarLLegadaLanzaExcepcionSiNoEstaEnModoOutbound() {
		assertThrows(RuntimeException.class, () -> suject.avisarLlegada(), "El buque no puede avisarLlegada actualmente");
	}
	
	@Test
	void avisarPartidaLanzaExcepcionSiNoEstaEnModoOutbound() {
		assertThrows(RuntimeException.class, () -> suject.avisarPartida(), "El buque no puede avisar su salida actualmente");
	}
	
	@Test
	void salirLanzaExcepcionSiNoEstaEnModoDeparting() {
		assertThrows(RuntimeException.class, () -> suject.salir(), "El buque no puede salir actualmente");
	}
	
	@Test
	void unBuqueConoceSuViaje() {
		suject.asignar(viaje);
		Assertions.assertEquals(viaje, suject.viaje());
	}

}
