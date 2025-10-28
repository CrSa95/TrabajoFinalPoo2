package tpfinalpoo2;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class EstadosBuqueTest {
	Terminal mockTerminal;
	Buque mockBuque;
	EstadoGPS estadoSuject;
	@BeforeEach
	void setUp() throws Exception {
		mockBuque = mock(Buque.class);
		mockTerminal = mock(Terminal.class);
	}

	@Test
	void InboundCambiaAOutbound() {
		estadoSuject = new Inbound();
		when(mockBuque.distanciaAdestino())
		.thenReturn((double)49);
		
		assertInstanceOf(Outbound.class, estadoSuject.siguienteEstado(mockBuque));
	}
	
	@Test 
	void OutboundCambiaAArrived() {
		estadoSuject = new Outbound();
		when(mockBuque.distanciaAdestino())
		.thenReturn((double) 0);
		
		assertInstanceOf(Arrived.class, estadoSuject.siguienteEstado(mockBuque));
		
	}

	
	@Test 
	void ArrivedCambiaAWorking() {
		estadoSuject = new Arrived();
		
		
		assertInstanceOf(Working.class ,estadoSuject.siguienteEstado(mockBuque));

	}
	
	@Test
	void WorkingADeparting() {
		estadoSuject = new Working();
		
		assertInstanceOf(Departing.class, estadoSuject.siguienteEstado(mockBuque));
		
	}
}
