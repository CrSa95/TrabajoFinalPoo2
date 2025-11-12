package tpfinalpoo2;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class BuqueTest {
	Buque suject;
	Tramo tramo_inicial;
	
	Viaje viaje;
	Terminal terminal_destino;
	Terminal terminal_origen;
	Coordenadas coordenadas_origen;
	Coordenadas coordenadas_destino;
	
	@BeforeEach
	void setUp() throws Exception{
		coordenadas_origen = mock(Coordenadas.class);
	    coordenadas_destino = mock(Coordenadas.class);
		suject = new Buque("Buque Marzo");
		viaje = mock(Viaje.class);
		terminal_destino = mock(Terminal.class);
		when(terminal_destino.coordenadas()).thenReturn(coordenadas_destino);
		terminal_origen = mock(Terminal.class);
		when(terminal_origen.coordenadas()).thenReturn(coordenadas_origen);
		tramo_inicial   = mock(Tramo.class);
		when(viaje.tramoInicial()).thenReturn(tramo_inicial);
		when(tramo_inicial.getTerminalDestino()).thenReturn(terminal_destino);
		when(tramo_inicial.getTerminalOrigen()).thenReturn(terminal_origen);
	}
	
	@Test 
	void proximaFechaTest() {
		LocalDateTime fecha_esperada = LocalDateTime.now();
		when(viaje.proximaFecha(terminal_destino, terminal_origen)).thenReturn(fecha_esperada);
		suject.asignar(viaje);
		assertEquals(fecha_esperada, suject.proximaFecha(terminal_destino, terminal_origen));
	}
	
	@Test 
	void proximaFechaTestDevuelveLaMasAltaEnCasoDeNoTenerDestino() {
		LocalDateTime fecha_esperada = LocalDateTime.MAX;
		suject.asignar(viaje);
		when(viaje.proximaFecha(terminal_destino, terminal_origen)).thenReturn(fecha_esperada);
		assertEquals(fecha_esperada, suject.proximaFecha(terminal_destino, terminal_origen));
	}
	
	@Test 
	void proximaFechaDeBuqueSinViajeRegresaFechaMaxima() {
		LocalDateTime fecha_esperada = LocalDateTime.MAX;
		when(viaje.proximaFecha(terminal_destino, terminal_origen)).thenReturn(fecha_esperada);
		assertEquals(fecha_esperada, suject.proximaFecha(terminal_destino, terminal_origen));
	}
	
	@Test 
	void iniciarUnViajeAvisaALaTerminalDestino() {
		suject.asignar(viaje);
		suject.iniciarViaje();
		verify(terminal_origen).avisarPartida(suject);
	}
	
	@Test 
	void conDistanciaADestinoMenorA50kmNotificaATerminal() {
		suject.asignar(viaje);
		suject.iniciarViaje();
		
		when(tramo_inicial.distanciaHacia(null)).thenReturn(49d);
		suject.avanzar();
		verify(terminal_destino).avisarLlegada(suject);
	}
	
	@Test 
	void conDistanciaADestinoMenorIgualA0SePuedeIniciarTrabajo() {
		suject.asignar(viaje);
		suject.iniciarViaje();
		
		when(tramo_inicial.distanciaHacia(null)).thenReturn(49d);
		suject.avanzar();
		
		when(tramo_inicial.distanciaHacia(null)).thenReturn(0d);
		suject.avanzar();
		
		assertDoesNotThrow(()-> suject.empezarTrabajo());
	}
	
	@Test 
	void sePuedeIniciarPartida() {
		suject.asignar(viaje);
		suject.iniciarViaje();
		when(tramo_inicial.distanciaHacia(null)).thenReturn(49d);
		suject.avanzar();
		when(tramo_inicial.distanciaHacia(null)).thenReturn(0d);
		suject.avanzar();
		suject.empezarTrabajo();
		
		
	}
	
	@Test 
	void sePuedeSalirDeLaTerminal() {
		Tramo tramo_siguiente = mock(Tramo.class);
		when(viaje.siguienteTramo(tramo_inicial)).thenReturn(tramo_siguiente);
		suject.asignar(viaje);
		suject.iniciarViaje();
		when(tramo_inicial.distanciaHacia(null)).thenReturn(49d);
		suject.avanzar();
		when(tramo_inicial.distanciaHacia(null)).thenReturn(0d);
		suject.avanzar();
		suject.empezarTrabajo();
		suject.permitirSalida();
		assertDoesNotThrow(()->suject.salir());
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
