package tpfinalpoo2;

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
	void seNotificaLaPartidaDelBuque() {
		when(buque.terminaOrigen()).thenReturn(terminal);
		suject.avisarPartida(buque);
		verify(buque).avisarPartida();
	}
	
}
