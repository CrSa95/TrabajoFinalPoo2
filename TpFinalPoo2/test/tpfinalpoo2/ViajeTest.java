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
	
	Terminal terminalOrigen = new Terminal(null);
	Terminal terminalIntermedia = new Terminal(null);
	Terminal terminalFinal = new Terminal(null);
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
		
		when(circuito.tiempoTotalDesdeHasta(terminalOrigen, terminalOrigen)).thenReturn(0d);
		assertEquals(fechaSalida, suject.fechaLlegada(terminalOrigen));
		
		
		when(circuito.tiempoTotalDesdeHasta(terminalOrigen, terminalIntermedia)).thenReturn(2d);
		assertEquals(fechaSalida.plusDays(2l), suject.fechaLlegada(terminalIntermedia));
		
		when(circuito.tiempoTotalDesdeHasta(terminalOrigen, terminalFinal)).thenReturn(5d);
		assertEquals(fechaSalida.plusDays(5l), suject.fechaLlegada(terminalFinal));
	}
	

}
