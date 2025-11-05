package tpfinalpoo2;

import static org.junit.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class BuqueTest {
	Buque suject;
	Terminal terminalSpy;
	Viaje viaje;
	@BeforeEach
	void setUp() throws Exception {
		suject = new Buque();
		terminalSpy = spy(new Terminal(""));
		viaje = mock(Viaje.class);
		when(viaje.terminalDestino())
		.thenReturn(terminalSpy);
		suject.asignar(viaje);
	}
	

	@Test
	void entrarAfaseInboundNotificaATerminal() {
		Buque sujectSpy = spy(suject);
		when(viaje.distanciaHaciaDestino()).thenReturn(49d);
		sujectSpy.actualizarGPS();
		

		sujectSpy.avisarLlegada();
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
