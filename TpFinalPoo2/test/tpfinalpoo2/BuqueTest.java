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
	
	void setearAOutbound() {
		when(viaje.distanciaHaciaDestino()).thenReturn(49d);
		suject.actualizarGPS();
	}
	
	void setearAArrived() {
		this.setearAOutbound();
		when(viaje.distanciaHaciaDestino()).thenReturn(0d);
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
		assertDoesNotThrow(() -> suject.avisarLlegada());
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
		assertThrows(RuntimeException.class, ()->suject.empezarTrabajo());
	}
	
	@Test 
	void permitirSalidaLanzaExcepcionSiNoEstaEnModoWorking() {
		assertThrows(RuntimeException.class, ()->suject.permitirSalida());
	}
	
	@Test 
	void avisarLLegadaLanzaExcepcionSiNoEstaEnModoOutbound(){
		assertThrows(RuntimeException.class, ()->suject.avisarLlegada());
	}
	@Test 
	void salirLanzaExcepcionSiNoEstaEnModoDeparting() {
		assertThrows(RuntimeException.class, ()->suject.salir());
	}
}
