package tpfinalpoo2;

import static org.junit.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class BuqueTest {
	Buque suject;
	Terminal terminalSpy;
	Terminal nuevoDestino;
	Viaje viaje;
	@BeforeEach
	void setUp() throws Exception {
		suject = new Buque();
		terminalSpy = spy(new Terminal(""));
		nuevoDestino = mock(Terminal.class);
		viaje = mock(Viaje.class);
		when(viaje.terminalDestino())
		.thenReturn(terminalSpy);
		suject.asignar(viaje);
	}
	
	

	@Test
	void sePuedeAvisarLlegadaAMenosDe50km() {
		when(viaje.distanciaHaciaDestino()).thenReturn(49d);
		suject.actualizarGPS();
		
		
		assertDoesNotThrow(() -> suject.avisarLlegada());
	}
	@Test 
	void sePuedeIniciarTrabajoEnLaTerminal() {
		// Pasa de Outbound a Arrived
		when(viaje.distanciaHaciaDestino()).thenReturn(0d);
		suject.actualizarGPS();  
		suject.actualizarGPS(); 
		
		// Empieza el trabajo
		assertDoesNotThrow(() -> suject.empezarTrabajo());
	}
	
	@Test 
	void sePuedeIniciarPartidaEnEstadoWorking() {
		// Pasa de Outbound a working
		when(viaje.distanciaHaciaDestino()).thenReturn(0d);
		suject.actualizarGPS();  
		suject.actualizarGPS(); 
		suject.empezarTrabajo();
		
		assertDoesNotThrow(() -> suject.permitirSalida());
	}
	
	
	@Test 
	void iniciarTrabajoLanzaExcepcionSiNoEstaEnModoArrived() {
		assertThrows(RuntimeException.class, ()->suject.empezarTrabajo());
	}
	
	@Test 
	void permitirSalidaLanzaExcepcionSiNoEstaEnModoWorking() {
		assertThrows(RuntimeException.class, ()->suject.permitirSalida());
	}
	
	@Test 
	void avisarLLegadaLanzaExcepcionSiNoEstaeEnModoOutbound(){
		assertThrows(RuntimeException.class, ()->suject.avisarLlegada());
	}
}
