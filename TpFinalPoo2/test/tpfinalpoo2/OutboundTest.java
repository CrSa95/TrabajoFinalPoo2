package tpfinalpoo2;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Assertions;
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
	}
	
	
	@Test 
	void distanciaMayorIgaulA50km() {
		when(buque.distanciaHaciaDestino()).thenReturn(50d);
		assertEquals(suject, suject.actualizarGPS(buque));
		
		when(buque.distanciaHaciaDestino()).thenReturn(51d);
		assertEquals(suject, suject.actualizarGPS(buque));
	}
	
	@Test 
	void distanciaMenorA50km() {
		when(buque.distanciaHaciaDestino()).thenReturn(49d);
		assertEquals(Inbound.class, suject.actualizarGPS(buque).getClass());
	}
	
	@Test 
	void seNotificaLaPartidaDelBuque() {
		when(buque.terminaOrigen()).thenReturn(terminal);
		suject.avisarPartida(buque);
		verify(buque).avisarPartida();
	}
	
}
