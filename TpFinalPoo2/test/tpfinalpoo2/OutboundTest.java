package tpfinalpoo2;

import static org.mockito.Mockito.*;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class OutboundTest {
	
	Outbound suject;
	Buque buque;
	Terminal terminal;
	
	@BeforeEach
	public void setUp() {
		suject = new Outbound();
		buque = mock(Buque.class);
		terminal = spy(Terminal.class);
	}
	
	
	@Test 
	void distanciaMenorA50kmCambiaElEstado() {
		when(buque.distanciaHaciaDestino()).thenReturn(50d);
		suject.avanzar(buque);
		verify(buque, never()).cambiarEstado(any());
		
		when(buque.distanciaHaciaDestino()).thenReturn(49d);
		suject.avanzar(buque);
		verify(buque).cambiarEstado(any());
	}
	
	@Test 
	void outboundAvisaPartidaATerminal() {
		when(buque.terminalOrigen()).thenReturn(terminal);
		suject.avisarPartida(buque);
		verify(terminal).avisarPartida(buque);
	}
	
	@Test 
	void seNotificaAlaTerminalOrigenUnicamenteUnaVez() {
		when(buque.distanciaHaciaDestino()).thenReturn(50d);
		when(buque.terminalOrigen()).thenReturn(terminal);
		when(buque.distanciaHaciaOrigen()).thenReturn(2d);
		suject.avanzar(buque);
		suject.avanzar(buque);
		
		verify(buque, times(1)).avisarPartida();
		
		
	}
	
}
